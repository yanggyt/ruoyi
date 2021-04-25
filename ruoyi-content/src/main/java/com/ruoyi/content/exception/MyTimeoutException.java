package com.ruoyi.content.exception;

/**
 * 自定义请求返回超时异常
 *
 * @author zhanghe
 */
public class MyTimeoutException extends RuntimeException {

    private static final long serialVersionUID = -4725428316233745222L;

    public MyTimeoutException() {
        super("读取请求返回信息超时");
    }

    public MyTimeoutException(String message) {
        super(message);
    }

    public MyTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

}