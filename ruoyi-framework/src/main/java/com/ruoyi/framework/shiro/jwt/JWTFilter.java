package com.ruoyi.framework.shiro.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ruoyi.common.json.JSON;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求头是否带上“Token”
        if(isLoginAttempt(request, response)){
            //如果存在，则执行executeLogin方法登入，检查token是否正确
            try {
                HttpServletRequest r = (HttpServletRequest) request;
                String token = r.getHeader("token");
                String username = r.getParameter("loginName");
                JWTUtil.verify(token,username);
                return true;
            }catch (TokenExpiredException t) {
                responseError(response,"token过期,重新登录");
            }catch (Exception e) {
                responseError(response,e.getMessage());
            }
        }else{
            //如果没有token，则可能是执行登录操作或者是游客状态访问，无需检查token，直接返回true
            responseError(response,"无权访问");
        }
        return false;
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     * @param response
     * @param message
     */
    private void responseError(ServletResponse response, String message) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            Map<String,Object> r = new HashMap<>();
            //message = URLEncoder.encode(message,"UTF-8");
            r.put("msg",message);
            r.put("code",401);
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.getWriter().write(JSON.marshal(r));
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断用户是否想要登入
     * 检测header里面是否包含Token字段
     * @param request request
     * @param response response
     * @return boolean
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        //String username = JWTUtil.getUsername(token);
        String username = request.getParameter("loginName");
        try{
            JWTUtil.verify(token,username);
        }catch (TokenExpiredException t){
            return false;
        }catch (Exception e){
            return false;
        }
        return true;

        //JWTToken jwtToken = new JWTToken(token);
        //提交给realm进行登入，如果错误就会抛出异常并被捕获
        //getSubject(request, response).login(jwtToken);
        //return true;

    }

    /**
     * 对跨域访问提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}