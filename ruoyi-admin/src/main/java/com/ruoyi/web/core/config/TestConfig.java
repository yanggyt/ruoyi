package com.ruoyi.web.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wanghy08
 * @date 2020-07-12 23:43
 */
@ConfigurationProperties(prefix = "spring.thymeleaf")
@Data
public class TestConfig {

    private String mode;
    private String encoding;
    private String cache;
}
