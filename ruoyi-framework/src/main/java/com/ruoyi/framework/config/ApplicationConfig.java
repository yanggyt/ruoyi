package com.ruoyi.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 程序注解配置
 *
 * @author ruoyi
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.ruoyi.**.mapper")
public class ApplicationConfig
{
    /** 服务器的HTTP端口 */
    @Value("${server.port}")
    public String port;

    /** 应用的访问路径 */
    @Value("${server.servlet.context-path}")
    public String path;

    /**
     * 设置系统配置
     */
    @Bean
    public void setSystemProperty()
    {
        System.setProperty("server.port", port);
        System.setProperty("server.servlet.context-path", path);
    }
}
