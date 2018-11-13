package com.ruoyi.framework.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Charles
 * @date 2018/11/13
 * @desciption
 */
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    private User user;

    private Cookie cookie;

    private Session session;

    private List<String> anon;


    @Data
    public static class User{
        // 登录地址
        private String loginUrl;

        // 权限认证失败地址
        private String unauthorizedUrl;

        // 首页地址
        private String indexUrl;

        // 验证码开关
        private boolean captchaEnabled;

        // 验证码类型 math 数组计算 char 字符
        private String captchaType;

    }

    @Data
    public static class Cookie{

        // 设置Cookie的域名 默认空，即当前访问的域名
        private String domain;

        // 设置cookie的有效访问路径
        private String path;

        // 设置HttpOnly属性
        private boolean httpOnly;

        // 设置Cookie的过期时间，天为单位
        private int maxAge;
    }

    @Data
    public static class Session {
        // Session超时时间（默认30分钟）
        private int expireTime;

        // 同步session到数据库的周期（默认1分钟）
        private int dbSyncPeriod;

        // 相隔多久检查一次session的有效性，默认就是10分钟
        private int validationInterval;
    }


}