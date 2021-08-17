package com.ruoyi.kettle.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;


/**
 * @author zh
 * @date 2020/12/1 16:49
 */
@Configuration
public class RedisConfig {
    /** redis 服务器地址 */
    @Value("${spring.redis.host}")
    private String host;

    /** redis 端口号 */
    @Value("${spring.redis.port}")
    private int port;

    /** redis 服务器密码 */
    @Value("${spring.redis.password}")
    private String password;

    /** redis 连接池最大连接数(使用负值无限制) */
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;

    /** redis 连接池最大空闲数 */
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    /** redis 连接池小空闲数 */
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    /** redis 连接池最大阻塞等待时间(负值无限制) */
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;

    /** redis 数据库索引(默认0) */
    @Value("${spring.redis.database}")
    private int database;

    /** redis 超时时间 */
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Autowired
    private RedisProperties redisProperties;


    //这是固定的模板
    //自己定义了一个RedisTemplate
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("lettuceConnectionFactoryUvPv") RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //Json序列化配置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(om.getPolymorphicTypeValidator());
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //解决序列化问题
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);

        //hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 为RedisTemplate配置Redis连接工厂实现
     * LettuceConnectionFactory实现了RedisConnectionFactory接口
     * UVPV用Redis
     *
     * @return 返回LettuceConnectionFactory
     */
    @Bean(destroyMethod = "destroy")
    //这里要注意的是，在构建LettuceConnectionFactory 时，如果不使用内置的destroyMethod，可能会导致Redis连接早于其它Bean被销毁
    public LettuceConnectionFactory lettuceConnectionFactoryUvPv() throws Exception {

//        List<String> clusterNodes = redisProperties.getCluster().getNodes();
//        Set<RedisNode> nodes = new HashSet<>();
//        clusterNodes.forEach(address -> nodes.add(new RedisNode(address.split(":")[0].trim(), Integer.parseInt(address.split(":")[1]))));
//        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
//        clusterConfiguration.setClusterNodes(nodes);
//        clusterConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
//        clusterConfiguration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());

        //我使用的是单机redis，集群使用上面注释的代码
        Set<RedisNode> nodes = new HashSet<>();
        nodes.add(new RedisNode(redisProperties.getHost(), redisProperties.getPort()));


        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());

        return new LettuceConnectionFactory(redisStandaloneConfiguration, getLettuceClientConfiguration(poolConfig));
    }

    /**
     * 配置LettuceClientConfiguration 包括线程池配置和安全项配置
     *
     * @param genericObjectPoolConfig common-pool2线程池
     * @return lettuceClientConfiguration
     */
    private LettuceClientConfiguration getLettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
        /*
        ClusterTopologyRefreshOptions配置用于开启自适应刷新和定时刷新。如自适应刷新不开启，Redis集群变更时将会导致连接异常！
         */
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                //开启自适应刷新
                //.enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS)
                //开启所有自适应刷新，MOVED，ASK，PERSISTENT都会触发
                .enableAllAdaptiveRefreshTriggers()
                // 自适应刷新超时时间(默认30秒)
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(25)) //默认关闭开启后时间为30秒
                // 开周期刷新
                .enablePeriodicRefresh(Duration.ofSeconds(20))  // 默认关闭开启后时间为60秒 ClusterTopologyRefreshOptions.DEFAULT_REFRESH_PERIOD 60  .enablePeriodicRefresh(Duration.ofSeconds(2)) = .enablePeriodicRefresh().refreshPeriod(Duration.ofSeconds(2))
                .build();
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig)
                .clientOptions(ClusterClientOptions.builder().topologyRefreshOptions(topologyRefreshOptions).build())
                //将appID传入连接，方便Redis监控中查看
                //.clientName(appName + "_lettuce")
                .build();
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(getRedisConfig(), host, port, timeout,password);
        return jedisPool;
    }
    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

}
