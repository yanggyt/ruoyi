package com.ruoyi.web.core.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * nacos分布式外部配置
 *
 *  <pre>
 *  1，nacos路径默认127.0.0.1:8848，可以写配置文件nacos.config.server-addr，也可以用环境变量 env 或系统属性 -D
 *  2，nacos添加配置ruoyi.yml，内容格式yml，可访问toyaml.com转换properties格式，与type=ConfigType.YAML格式一致
 *  3，DruidProperties使用@Value加载nacos配置，没有nacos则使用配置文件信息，数据库配置通常不必动态刷新
 *  4，nacos支持动态更新配置，使用@NacosValue注解成员field字段或set方法即可
 *  5，可以使用{@link com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources NacosPropertySources
 *  }配置多个{@link NacosPropertySource}，例如dataId=ruoyi.yml用于覆盖数据库配置等，dataId=ruoyi-dynamic.yml用于动态配置
 *  </pre>
 *
 * @author xlongwei
 */
@Configuration
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.config.server-addr:127.0.0.1:8848}"))
@NacosPropertySource(dataId = "ruoyi.yml", type = ConfigType.YAML, autoRefreshed = true, first = true)
public class NacosConfig {

}
