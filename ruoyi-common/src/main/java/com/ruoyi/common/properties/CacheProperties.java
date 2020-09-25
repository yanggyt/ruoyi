package com.ruoyi.common.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private Map<String, Config> names = new HashMap<>();

    private Config defaultConfig = new Config(Duration.ofMinutes(1));

    @ToString
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config{
        private Duration ttl;
    }
}
