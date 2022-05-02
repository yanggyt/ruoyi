package com.wuzhen;

import com.wuzhen.common.config.RuoYiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 启动程序
 * 
 * @author zhengzheng
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ZtApplication  implements WebMvcConfigurer
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ZtApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  展厅后台管理服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 访问路径：http://localhost:93/facereport/static/captured/daping_report/captured_images/face/788001414206074880/2021/1/27/cj_142207194912440320.png
         * "/facereport/static/captured/**" 为前端URL访问路径
         * "file:" + uploadPath 是本地磁盘映射
         */
        registry.addResourceHandler("/fp/**").addResourceLocations("file:" + RuoYiConfig.getProfile()+"/fp/");
        registry.addResourceHandler("/lp/**").addResourceLocations("file:" +  RuoYiConfig.getLPUploadPath()+"/");
        System.out.println( RuoYiConfig.getLPUploadPath()+"/");
//        registry.addResourceHandler("/**").addResourceLocations("file:" + RuoYiConfig.getProfile());
    }

}