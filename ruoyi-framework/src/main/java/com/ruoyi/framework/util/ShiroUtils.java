package com.ruoyi.framework.util;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.shiro.realm.UserRealm;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 * Subject:主体.每个用户登录后都会对应一个Subject对象,所有用户信息都存储到Subject中
 * Security Manager:Shiro最大的容器.Shiro所有功能都封装在Security Manager中  1：编写代码时,想要使用Shiro第一个步骤获取到Security Manager 2：在整个程序中保证Security Manager有且只有一个
 * Authenticator: 认证器.执行认证过程调用的组件.
 * Authorizer:授权器.执行授权时调用的组件.
 * Session Manager: Session管理器.
 * Cache Manager: 缓存管理器.Shiro支持很多第三方缓存工具.例如Ehcache等.
 * SessionDao: 操作Session内容的组件
 * Realm:该组件的作用负责获取到数据库中数据并根据用户自定义逻辑进行授权和认证等操作. 1：Shiro 框架和数据库没有关系.没有对数据库设计有认证强制要求 2：如果Shiro想要访问数据库都是通过Realm组件.  3：如果Shiro数据不是来源于数据库,可以使用.ini文件设置静态数据.
 *
 * @author ruoyi
 */
public class ShiroUtils {
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubject().logout();
    }

    /**
     * 获取到用户
     *
     * @return
     */
    public static SysUser getSysUser() {
        SysUser user = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj)) {
            user = new SysUser();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }

    /**
     * 设置用户名
     *
     * @param user
     */
    public static void setSysUser(SysUser user) {
        Subject subject = getSubject();
        //获取当事人的信息，身份.代表用户名,邮箱,手机等能够唯一确认身份的信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    /***
     * 清除缓存授权信息
     */
    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static Long getUserId() {
        return getSysUser().getUserId().longValue();
    }

    /**
     * 获取登录名
     *
     * @return
     */
    public static String getLoginName() {
        return getSysUser().getLoginName();
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String getIp() {
        return getSubject().getSession().getHost();
    }

    /**
     * 获取sessionId
     *
     * @return
     */
    public static String getSessionId() {
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
