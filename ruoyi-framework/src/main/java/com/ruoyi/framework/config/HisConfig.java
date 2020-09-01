package com.ruoyi.framework.config;

import com.bending.core.props.HisClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * HIS客户端配置
 */
@Configuration
@EnableConfigurationProperties({HisClientProperties.class})
@ComponentScan(basePackages = "com.bending.core")
public class HisConfig {
}
