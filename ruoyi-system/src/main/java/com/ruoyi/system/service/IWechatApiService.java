package com.ruoyi.system.service;

public interface IWechatApiService {
    //获取Access Token
    public String GetAccessToken();

    //根据企业微信登录身份获取本地LoginName
    public String GetLoginNameWithWechatCode(String code);
}
