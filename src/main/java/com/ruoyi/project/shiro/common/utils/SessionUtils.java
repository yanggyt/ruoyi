package com.ruoyi.project.shiro.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ruoyi.project.shiro.ShiroConstants;
import com.ruoyi.project.system.user.domain.User;

/**
 * 通用Session处理方法
 * 
 * @author y
 */
public class SessionUtils
{
    /**
     * 得到session用户对象
     */
    public static User getCurrentUser()
    {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        User user = (User) session.getAttribute(ShiroConstants.CURRENT_USER);
        return user;
    }

    /**
     * 获取request信息
     */
    public static Object getRequestAttribute(String key)
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request.getAttribute(key);
    }

    /**
     * 得到session obj对象
     */
    public static Session getSession()
    {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.getSession();
    }
}
