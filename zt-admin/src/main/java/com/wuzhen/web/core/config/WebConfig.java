package com.wuzhen.web.core.config;


import com.wuzhen.common.config.RuoYiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Value("${fp.upload.path}")
//    private String fpUploadPath;
//
//    @Value("${lp.upload.path}")
//    private String lpUploadPath;
//
//    @Value("${root.upload.path}")
//    private String rootUploadPath;
//

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
