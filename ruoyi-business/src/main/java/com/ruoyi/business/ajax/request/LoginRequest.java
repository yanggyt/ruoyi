package com.ruoyi.business.ajax.request;

public class LoginRequest {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
