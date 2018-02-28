package com.ruoyi.common.exception;

import com.ruoyi.common.exception.user.UserException;

/**
 * 验证码错误异常类
 * 
 * @author yangzz
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
