package com.ruoyi.framework.shiro.realm;

import com.ruoyi.common.exception.user.*;
import com.ruoyi.framework.shiro.LoginType;
import com.ruoyi.framework.shiro.UserToken;
import com.ruoyi.framework.shiro.service.SysLoginService;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author 胡浩
 * @Date 2019/6/4
 **/
public class UserPhoneRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserPhoneRealm.class);


    @Autowired
    private SysLoginService loginService;

    @Override
    public String getName() {
        return LoginType.USER_PHONE.getType();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof UserToken) {
            return ((UserToken) token).getLoginType() == LoginType.USER_PHONE;
        } else {
            return false;
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserToken upToken = (UserToken) token;
//        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String phone = upToken.getUsername();
        String validCode = "";
        if (upToken.getPassword() != null) {
            validCode = new String(upToken.getPassword());
        }

        SysUser user = null;
        try {
            user = loginService.phoneLogin(phone, validCode);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + phone + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, validCode, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}