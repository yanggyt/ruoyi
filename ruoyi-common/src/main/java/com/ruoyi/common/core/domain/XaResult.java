package com.ruoyi.common.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * @author eason.zt
 * @version V1.0
 * @Title: XaResult.java
 * @Package com.web.hhrz.base.kuadi100
 * @Description: Service返回结果统一对象
 * @date 2014年8月13日 下午7:46:23
 * @update 2021年7月19号
 * @updateBy 哦是吗
 */
@ApiModel
@SuppressWarnings("unchecked")
public class XaResult<T> {

    @ApiModelProperty(value = "code : 返回代码，0表示OK，其它的都有对应问题",position = 1)
    private int code = 0;

    @ApiModelProperty(value = "msg : 如果code!=0,错误信息",position = 2)
    private String msg;

    @ApiModelProperty(value = "如果code!=0,message的补充信息",position = 3)
    private Object exception;

    @ApiModelProperty(value = "code为0时返回结果集",position = 4)
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }


    public XaResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public XaResult(String errorMsg) {
        this.msg = errorMsg;
        this.code = 500;
        this.data = (T) new Object();
    }

    public XaResult(String errorMsg, int code) {
        this.msg = errorMsg;
        this.code = code;
        this.data = (T) new Object();
    }

    public XaResult(T data) {
        this.data = data;
    }

    public XaResult() {
        this.data = (T) new Object();
    }

    public static <T> XaResult<T> success(T object) {
        XaResult<T> xaResult = new XaResult<>();
        xaResult.setCode(0);
        xaResult.setMsg("success");
        xaResult.setData(object);
        return xaResult;
    }

    public static <T> XaResult<T> success() {
        XaResult<T> xaResult = new XaResult<>();
        xaResult.setCode(0);
        xaResult.setMsg("success");
        xaResult.setData(null);
        return xaResult;
    }

    public static <T> XaResult<T> error(String msg, T object) {
        XaResult<T> xaResult = new XaResult<>();
        xaResult.setCode(500);
        xaResult.setMsg(msg);
        xaResult.setData(object);
        return xaResult;
    }


    public static <T> XaResult<T> error(String msg) {
        XaResult<T> xaResult = new XaResult<>();
        xaResult.setCode(500);
        xaResult.setMsg(msg);
        xaResult.setData(null);
        return xaResult;
    }


    public static <T> XaResult<T> error() {
        XaResult<T> xaResult = new XaResult<>();
        xaResult.setCode(500);
        xaResult.setMsg("未知异常，请联系管理员");
        xaResult.setData(null);
        return xaResult;
    }
}