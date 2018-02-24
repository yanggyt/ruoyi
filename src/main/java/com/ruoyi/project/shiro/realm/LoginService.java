package com.ruoyi.project.shiro.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ruoyi.framework.constant.CommonConstant;
import com.ruoyi.project.shiro.common.Constants;
import com.ruoyi.project.shiro.common.utils.MessageUtils;
import com.ruoyi.project.shiro.exception.user.RoleBlockedException;
import com.ruoyi.project.shiro.exception.user.UserBlockedException;
import com.ruoyi.project.shiro.exception.user.UserNotExistsException;
import com.ruoyi.project.shiro.exception.user.UserPasswordNotMatchException;
import com.ruoyi.project.shiro.service.PasswordService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.project.util.SystemLogUtils;

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
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < Constants.PASSWORD_MIN_LENGTH
                || password.length() > Constants.PASSWORD_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < Constants.USERNAME_MIN_LENGTH
                || username.length() > Constants.USERNAME_MAX_LENGTH)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectByUserName(username);

        if (user == null)
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (Constants.USER_BLOCKED.equals(user.getStatus()))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRefuseDes()));
            throw new UserBlockedException(user.getRefuseDes());
        }
        
        if (Constants.ROLE_BLOCKED.equals(user.getRole().getStatus()))
        {
            SystemLogUtils.log(username, CommonConstant.LOGIN_FAIL, MessageUtils.message("role.blocked", user.getRole().getRemark()));
            throw new RoleBlockedException(user.getRole().getRemark());
        }
        
        SystemLogUtils.log(username, CommonConstant.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        return user;
    }

}
