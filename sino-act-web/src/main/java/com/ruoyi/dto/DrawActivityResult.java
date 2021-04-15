package com.ruoyi.dto;

import java.util.HashMap;
import java.util.Map;

public class DrawActivityResult {
    /**成功**/
    public static final String SUCCESS = "0000";
    /**系统繁忙，请稍后重试**/
    public final static String  EXCEPTION = "GT00000";
    /**参数错误**/
    public static final String EXCEPTION_000001="GT00001";
    /**抽奖失败[{0}]**/
    public static final String EXCEPTION_000002="GT00002";
    /**奖品信息**/
    public static final String EXCEPTION_000003="GT00003";
    /**查询中奖纪录**/
    public static final String EXCEPTION_000004="GT00004";
    /**奖品发放通知**/
    public static final String EXCEPTION_000005="GT00005";
    /**增加抽奖机会失败**/
    public static final String EXCEPTION_000006="GT00006";
    /**可用抽奖次数不足**/
    public static final String EXCEPTION_000007="GT00007";
    /**查询抽奖可用次数失败*/
    public static final String EXCEPTION_000008="GT00008";
    /**积分账户不存在**/
    public static final String EXCEPTION_000009="GT00009";
    /**积分账户余额不足**/
    public static final String EXCEPTION_000010="GT00010";
    public static final String EXCEPTION_000011="GT00011";
    public static final String EXCEPTION_000012="GT00012";
    public static final Map<String, String> ERROR = new HashMap<String, String>();
    static {
        ERROR.put(SUCCESS, "成功");
        ERROR.put(EXCEPTION, "系统繁忙，请稍后重试");
        ERROR.put(EXCEPTION_000001, "参数错误[{0}]");
        ERROR.put(EXCEPTION_000002, "抽奖失败[{0}]");
        ERROR.put(EXCEPTION_000003, "查询奖品信息失败[{0}]");
        ERROR.put(EXCEPTION_000004, "查询中奖纪录失败[{0}]");
        ERROR.put(EXCEPTION_000005, "奖品发放通知[{0}]");
        ERROR.put(EXCEPTION_000006, "增加抽奖机会失败[{0}]");
        ERROR.put(EXCEPTION_000007, "可用抽奖次数不足");
        ERROR.put(EXCEPTION_000008, "查询抽奖可用次数失败[{0}]");
        ERROR.put(EXCEPTION_000009, "积分账户不存在");
        ERROR.put(EXCEPTION_000010, "积分账户余额不足");
        ERROR.put(EXCEPTION_000011, "活动未开启");
    }

    public static String getMsg(String code, Object... params) {
        String message = ERROR.get(code);
        if (message == null) {
            return null;
        }
        for (int i = 0; i < params.length; i++) {
            message = message.replaceAll("\\{" + i + "\\}", String.valueOf(params[i]));
        }

        return message;
    }

}
