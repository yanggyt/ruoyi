package com.ruoyi.project.shiro.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.project.shiro.common.UserConstants;
import com.ruoyi.project.shiro.exception.UserBlockedException;
import com.ruoyi.project.shiro.exception.UserNotExistsException;
import com.ruoyi.project.shiro.exception.UserPasswordNotMatchException;
import com.ruoyi.project.shiro.service.PasswordService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author yangzz
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.findByUserName(username);

        if (user == null)
        {
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (UserConstants.blocked.equals(user.getStatus()))
        {
            throw new UserBlockedException(user.getRefuseDes());
        }
        return user;
    }

}
