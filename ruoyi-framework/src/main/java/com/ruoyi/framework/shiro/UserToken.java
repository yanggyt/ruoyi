package com.ruoyi.framework.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Description
 * @Author 胡浩
 * @Date 2019/6/3
 **/
public class UserToken extends UsernamePasswordToken {
    /**
     * 登录方式
     **/
    private LoginType loginType;
    /**
     * 微信code
     **/
    private String code;

    public UserToken(final String username, final String password, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    /**
     * 记住我
     **/
    public UserToken(String username, String password, boolean rememberMe, LoginType loginType) {
        super(username, password, rememberMe);
        this.loginType = loginType;
    }

    // TODO 由于是demo方法，此处微信只传一个code参数，其他参数根据实际情况添加
    public UserToken(String username, String password, String code, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
        this.code = code;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}