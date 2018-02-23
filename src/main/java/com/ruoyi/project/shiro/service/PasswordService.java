package com.ruoyi.project.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ruoyi.project.shiro.common.utils.Md5Utils;
import com.ruoyi.project.shiro.exception.UserPasswordNotMatchException;
import com.ruoyi.project.shiro.exception.UserPasswordRetryLimitExceedException;
import com.ruoyi.project.system.user.domain.User;

/**
 * 登录密码方法
 * 
 * @author yangzz
 */
@Component
public class PasswordService
{

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
    }

    public void validate(User user, String password)
    {
        String username = user.getUserName();

        AtomicInteger retryCount = loginRecordCache.get(username);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            loginRecordCache.put(username, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(User user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }

    public String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }

    public static void main(String[] args)
    {
        System.out.println(new PasswordService().encryptPassword("admin", "admin123", ""));
        System.out.println(new PasswordService().encryptPassword("ry", "admin123", ""));
    }
}
