package com.ruoyi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RuoYiApplication.class);
    }

    @Bean
    public CommandLineRunner startupCommandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                        " .-------.       ____     __        \n" +
                        " |  _ _   \\      \\   \\   /  /    \n" +
                        " | ( ' )  |       \\  _. /  '       \n" +
                        " |(_ o _) /        _( )_ .'         \n" +
                        " | (_,_).' __  ___(_ o _)'          \n" +
                        " |  |\\ \\  |  ||   |(_,_)'         \n" +
                        " |  | \\ `'   /|   `-'  /           \n" +
                        " |  |  \\    /  \\      /           \n" +
                        " ''-'   `'-'    `-..-'              ");
            }
        };
    }
}