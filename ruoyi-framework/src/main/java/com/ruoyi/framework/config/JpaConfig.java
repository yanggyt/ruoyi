package com.ruoyi.framework.config;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableJpaRepositories(basePackages = "com.ruoyi")
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () ->{
            SysUser sysUser = ShiroUtils.getSysUser();
            if(sysUser != null){
                return Optional.of(sysUser.getUserId().toString());
            }
            return Optional.empty();
        };
    }
}