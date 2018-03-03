package com.ruoyi.framework.web.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ruoyi.framework.web.domain.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理器
 * 
 * @author ruoyi
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler
{

    @ExceptionHandler(AuthorizationException.class)
    public JSON handleAuthorizationException(AuthorizationException e)
    {
        log.error(e.getMessage(), e);
        return JSON.error("您没有数据的权限，请联系管理员添加");
    }

    @ExceptionHandler(Exception.class)
    public JSON handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return JSON.error("服务器错误，" + e.getMessage());
    }

}
