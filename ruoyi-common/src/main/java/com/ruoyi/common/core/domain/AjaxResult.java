package com.ruoyi.common.core.domain;

import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author ruoyi
 */
public class AjaxResult<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "code : 返回代码，0表示OK，其它的都有对应问题")
    public int code;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "msg : 如果code!=0,错误信息")
    public String msg;

    @ApiModelProperty(value = "如果code!=0,message的补充信息")
    private Object exception;

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "code为0时返回结果集")
    public T data = (T) new Object();

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

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public AjaxResult(Type type, String msg) {
        super.put(String.valueOf(code), type.value);
        super.put(msg, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, Object data) {
        super.put(String.valueOf(code), type.value);
        super.put(msg, msg);
        if (StringUtils.isNotNull(data)) {
            super.put((String) data, data);
        }
    }

    public AjaxResult(String errorMsg) {
        this.msg = errorMsg;
        this.code = 500;
        this.data = (T) new Object();
    }

    public AjaxResult(String errorMsg, int code) {
        this.msg = errorMsg;
        this.code = code;
        this.data = (T) new Object();
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        AjaxResult result = new AjaxResult<>();
        result.setCode(Type.SUCCESS.value);
        result.setMsg("操作成功");
        return result;
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        AjaxResult result = AjaxResult.success();
        result.setData(data);
        return result;
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        AjaxResult result = AjaxResult.success();
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        AjaxResult result = AjaxResult.success();
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult warn(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(Type.WARN.value);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult warn(String msg, Object data) {
        AjaxResult result = AjaxResult.warn(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        AjaxResult result = new AjaxResult<>();
        result.setCode(Type.ERROR.value);
        result.setMsg("操作失败");
        return result;
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        AjaxResult result = AjaxResult.error();
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        AjaxResult result = AjaxResult.error();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
