package com.ruoyi.common.utils.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.ruoyi.project.system.user.domain.User;

/**
 * shiro 工具类
 * 
 * @author yangzz
 */
public class ShiroUtils
{
    public static Subject getSubjct()
    {
        return SecurityUtils.getSubject();
    }

    public static void logout()
    {
        getSubjct().logout();
    }

    public static User getUser()
    {
        return (User) getSubjct().getPrincipal();
    }

    public static Long getUserId()
    {
        return getUser().getUserId().longValue();
    }
}
