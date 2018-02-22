package com.ruoyi.project.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.project.shiro.exception.JCaptchaException;
import com.ruoyi.project.shiro.exception.RoleBlockedException;
import com.ruoyi.project.shiro.exception.UserBlockedException;
import com.ruoyi.project.shiro.exception.UserNotExistsException;
import com.ruoyi.project.shiro.exception.UserPasswordNotMatchException;
import com.ruoyi.project.shiro.exception.UserPasswordRetryLimitExceedException;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author yangzz
 */
public class UserRealm extends AuthorizingRealm
{

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        // Long userId = ShiroUtils.getUserId();
        // MenuService menuService = SpringUtils.getBean(MenuService.class);
        // Set<String> perms = menuService.listPerms(userId);
        // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // info.setStringPermissions(perms);
        // return info;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try
        {
            user = loginService.login(username, password);
        }

        catch (JCaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (Exception e)
        {
            log.error("login error", e);
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
