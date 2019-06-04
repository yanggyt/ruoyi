package com.ruoyi.framework.config;

import com.ruoyi.framework.shiro.config.DefaultPasswordEncoder;
import com.ruoyi.framework.shiro.config.RyPasswordEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 */
@Configuration
public class AuthenticationBeanConfig {
    /**
     * 默认密码处理器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(RyPasswordEncoder.class)
    public RyPasswordEncoder passwordEncoder() {
        return new DefaultPasswordEncoder();
    }
}
