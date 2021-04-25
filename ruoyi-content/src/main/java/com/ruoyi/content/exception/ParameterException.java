package com.ruoyi.content.exception;

/**
 * 参数异常
 * 
 * @author zhanghe
 *
 */
public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = -796008340044578794L;

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
