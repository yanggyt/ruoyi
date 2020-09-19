package com.ruoyi.framework.interceptor;

import com.ruoyi.common.annotation.AjaxLogin;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.JWTUtil;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * /ajax/**
 * 所有接口身份认证拦截器
 * @author bei.wu
 */
@Component
public class AjaxAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AjaxLogin classAnnotation = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(AjaxLogin.class);
            AjaxLogin annotation = handlerMethod.getMethod().getAnnotation(AjaxLogin.class);
            if (ObjectUtils.anyNotNull(classAnnotation, annotation)) {
                String token = request.getHeader("Authorization");
                if (StringUtils.isBlank(token)) {
                    AjaxResult ajaxResult = AjaxResult.error("请登录后操作");
                    ServletUtils.renderString(response, JSON.marshal(ajaxResult));
                    return false;
                }
                try {
                    Claims claims = JWTUtil.parseJWT(token);
                    request.setAttribute("member", claims.getSubject());
                } catch (Exception e) {
                    AjaxResult ajaxResult = AjaxResult.error("没有权限");
                    ServletUtils.renderString(response, JSON.marshal(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        return super.preHandle(request, response, handler);
    }
}
