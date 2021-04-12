package com.ruoyi.web.vo;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

public class UserInfo extends WxOAuth2UserInfo {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
