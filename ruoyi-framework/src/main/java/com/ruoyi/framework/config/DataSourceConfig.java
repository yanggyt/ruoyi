package com.ruoyi.framework.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * druid 配置多数据源
 *
 * @author ruoyi
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DataSourceProperties masterDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource masterDataSource(){
        return masterDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.datasource.slave.enabled")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.datasource.slave.enabled")
    public DataSource slaveDataSource() {
        return slaveDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
