package com.ruoyi.framework.web.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ruoyi.framework.web.domain.R;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理器
 * 
 * @author yangzz
 * 
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler
{

    @ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException(AuthorizationException e)
    {
        log.error(e.getMessage(), e);
        return R.error("未授权");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return R.error("服务器错误，" + e.getMessage());
    }

}
