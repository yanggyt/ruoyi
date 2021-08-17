package com.ruoyi.framework.jwt.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.service.IJwtTokenService;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements IJwtTokenService {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;
    /**
     * 获取AjaxResult格式的jwt token
     *
     * @param username 用户名
     * @param password 密码
     * @return jwtToken
     */
    @Override
    public AjaxResult AjaxResultJwtToken(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return AjaxResult.error("账号和密码不能为空!");
        }

        SysUser user = userService.selectUserByLoginName(username);
        if (user == null)
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            return AjaxResult.error("对不起，您的账号已被删除!");
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            return AjaxResult.error("用户已封禁，请联系管理员!");
        }

        if (!passwordService.matches(user, password))
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        String token = JwtUtils.createToken(username, user.getPassword());
        return AjaxResult.success("登录成功,请妥善保管您的token信息").put("token", token);
    }
}
