package com.ruoyi.web.vo;

public class Result {
    private String respCode = Const.RES_SUCC;
    private String respMsg = Const.RES_MSG_SUCC;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
