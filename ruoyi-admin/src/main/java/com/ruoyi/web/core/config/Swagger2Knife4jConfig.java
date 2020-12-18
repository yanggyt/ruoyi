package com.ruoyi.web.core.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.ruoyi.common.config.RuoYiConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description swagger2Knife4j
 * @Author liuwy
 * @Date 2020/12/18
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Knife4jConfig {

    @Bean(value = "adminApi")
    public Docket createRestApi() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("若依")
                .select()
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("标题：若依管理系统_接口文档")
                //描述
                .description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
                //作者
                .contact(new Contact(RuoYiConfig.getName(), null, null))
                //服务url
                .termsOfServiceUrl("http://ruoyi.vip")
                //版本号
                .version("版本号:" + RuoYiConfig.getVersion())
                .build();
    }

}
