package com.ruoyi.system.domain;

import java.util.Date;

public class WechatAccessToken {
    private Long sid;
    private String corpId;
    private String agentId;
    private String secret;
    private String errcode;
    private String errmsg;
    private String access_token;  //access_token
    private String expires_in;
    private Date getTokenTime;
    private String aesKey;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public Date getGetTokenTime() {
        return getTokenTime;
    }

    public void setGetTokenTime(Date getTokenTime) {
        this.getTokenTime = getTokenTime;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public String toString() {
        return "WechatAccessToken{" +
                "sid='" + sid + '\'' +
                ", corpId='" + corpId + '\'' +
                ", agentId='" + agentId + '\'' +
                ", secrect='" + secret + '\'' +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", getTokenTime=" + getTokenTime +
                ", aesKey='" + aesKey + '\'' +
                '}';
    }
}
