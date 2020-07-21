package com.ruoyi.web.core.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.Map;

@EnableCaching
@Configuration
public class CacheConfig {
    @Autowired(required = false)
    RedisConnectionFactory redisConnectionFactory;
    //默认超时设置1小时
    @Value("${spring.redis.defaultExpiration:3600}")
    Duration defaultExpiration;
    //自定义部分缓存的超时
    @Value("${spring.redis.expires}")
    String expires = "{}";

    @Bean
    public CacheManager cacheManager() {
        if (redisConnectionFactory != null) {
            try {
                //配置有redis standalone或cluster，并且能正常连接时才启用redis缓存
                redisConnectionFactory.getConnection().ping();
                System.err.println("using redis cache manager");
                RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults
                        (RedisCacheConfiguration.defaultCacheConfig().entryTtl(defaultExpiration))
                        .withInitialCacheConfigurations(Maps.transformValues(JSON.parseObject(expires, Map.class), d ->
                                RedisCacheConfiguration
                                        .defaultCacheConfig().entryTtl(Duration.ofSeconds(Long.valueOf(d.toString())))))
                        .transactionAware().build();
                return redisCacheManager;
            } catch (Exception e) {
                System.err.println("fail to connect to redis");
            }
        }
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("classpath:ehcache/ehcache-shiro.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject()) {
            @Override
            protected Cache getMissingCache(String name) {
                Cache cache = super.getMissingCache(name);
                if (cache == null) {
                    //使用default配置克隆缓存
                    getCacheManager().addCacheIfAbsent(name);
                    cache = super.getCache(name);
                }
                return cache;
            }
        };
    }
}
