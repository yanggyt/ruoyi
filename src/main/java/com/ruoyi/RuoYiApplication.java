package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{

    public static void main(String[] args)
    {
        // http://localhost/system/user/getUserlist.action
        // http://localhost/system/user/login
        SpringApplication.run(RuoYiApplication.class, args);
    }

}