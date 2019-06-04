package com.ruoyi.framework.shiro;

/**
 * @Description 登录方式的枚举类，存放我们所有的登录方式
 * https://blog.csdn.net/zhourenfei17/article/details/88826911
 * @Author 胡浩
 * @Date 2019/6/3
 **/
public enum LoginType {
    /** 通用 **/
    COMMON("common_realm"),
    /** 用户密码登录 **/
    USER_PASSWORD("user_password_realm"),
    /** 手机验证码登录 **/
    USER_PHONE("user_phone_realm"),
    /** 二维码登录 **/
    QRCODE_LOGIN("qrcode_login_realm"),
    /** 第三方登录(微信登录) **/
    WECHAT_LOGIN("wechat_login_realm");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

}
