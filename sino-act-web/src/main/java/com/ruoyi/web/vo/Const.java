package com.ruoyi.web.vo;

/**
 *
 * @author Huayue
 * @version 1658
 */
public interface Const {

    String CHANNEL_APP = "App";
    String CHANNEL_WECHAT = "WX";

    String RES_SUCC = "1";
    String RES_MSG_SUCC = "操作成功";
    String RES_ERR = "-1";
    String RES_MSG_ERR = "系统异常，请稍后再试";
    String RES_SESSION_TIMEOUT = "-2";
    String RES_MSG_SESSION_TIMEOUT = "会话失效，请重新登录";
    String RES_MOBILE_NULL = "-3";
    String RES_MSG_MOBILE_NULL = "请先完善手机号";
    String RES_NOT_AUTH = "-4";
    String RES_MSG_NOT_AUTH = "请勾选协议";
    String RES_ACCOUNT_EXCEPT = "-5";
    String RES_MSG_ACCOUNT_EXCEPT = "账户异常，请联系在线客服";
    String RES_PARAM_ERR = "-7";
    String RES_MSG_PARAM_ERR = "参数错误";
    String RES_ERR_SHARE = "-8";
    String RES_ERR_LIMIT = "-9";

    String STATUS_VALID = "1";
    String STATUS_INVALID = "0";
}
