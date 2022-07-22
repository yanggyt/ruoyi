package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("  \n" +
                " ////////////////////////////////////////\n" +
                " .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. |\n" +
                "| |  _______     | || |  _________   | |\n" +
                "| | |_   __ \\    | || | |  _   _  |  | |\n" +
                "| |   | |__) |   | || | |_/ | | \\_|  | |\n" +
                "| |   |  __ /    | || |     | |      | |\n" +
                "| |  _| |  \\ \\_  | || |    _| |_     | |\n" +
                "| | |____| |___| | || |   |_____|    | |\n" +
                "| |              | || |              | |\n" +
                "| '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'\n" +
                "//////////////////////////////////////// ");
    }
}