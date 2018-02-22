package com.ruoyi.framework.core.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.user.domain.User;

public class BaseController
{
    public User getUser()
    {
        return ShiroUtils.getUser();
    }

    public Integer getUserId()
    {
        return getUser().getUserId();
    }

    public String getLoginName()
    {
        return getUser().getLoginName();
    }
}
