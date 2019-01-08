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
        //获取当事人的信息
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
