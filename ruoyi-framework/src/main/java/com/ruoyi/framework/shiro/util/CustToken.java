package com.ruoyi.framework.shiro.util;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustToken extends UsernamePasswordToken {
    private String loginType;// 企业微信：wechat

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public CustToken(String username, String password, Boolean rememberMe, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }
}
