package com.ruoyi.content.exception;

/**
 * 业务异常
 *
 * @author zhanghe
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -717870860504034613L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
