package com.ruoyi.system.domain;

public class WechatSendMessage {
    public String touser;
    public String toparty;
    public String totag;
    public String msgtype;
    public Integer agentid;
    public String text;
    public Integer safe;
    public Integer enable_id_trans;
    public Integer enable_duplicate_check;
    public Integer duplicate_check_interval;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Integer getEnable_id_trans() {
        return enable_id_trans;
    }

    public void setEnable_id_trans(Integer enable_id_trans) {
        this.enable_id_trans = enable_id_trans;
    }

    public Integer getEnable_duplicate_check() {
        return enable_duplicate_check;
    }

    public void setEnable_duplicate_check(Integer enable_duplicate_check) {
        this.enable_duplicate_check = enable_duplicate_check;
    }

    public Integer getDuplicate_check_interval() {
        return duplicate_check_interval;
    }

    public void setDuplicate_check_interval(Integer duplicate_check_interval) {
        this.duplicate_check_interval = duplicate_check_interval;
    }

    public String getMsgtype() {
        return msgtype;
    }

    @Override
    public String toString() {
        return "WechatSendMessage{" +
                "touser='" + touser + '\'' +
                ", toparty='" + toparty + '\'' +
                ", totag='" + totag + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", agentid=" + agentid +
                ", text='" + text + '\'' +
                ", safe=" + safe +
                ", enable_id_trans=" + enable_id_trans +
                ", enable_duplicate_check=" + enable_duplicate_check +
                ", duplicate_check_interval=" + duplicate_check_interval +
                '}';
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

}