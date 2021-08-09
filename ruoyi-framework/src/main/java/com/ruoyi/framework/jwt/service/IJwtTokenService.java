package com.ruoyi.framework.jwt.service;

import com.ruoyi.common.core.domain.AjaxResult;

public interface IJwtTokenService {
    /**
     * 获取AjaxResult格式的jwt token
     * @param  username 用户名
     * @param  password 密码
     * @return jwtToken
     */
    public AjaxResult AjaxResultJwtToken(String username, String password);
}
