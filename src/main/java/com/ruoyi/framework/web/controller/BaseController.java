package com.ruoyi.framework.web.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.support.TableSupport;
import com.ruoyi.project.system.user.domain.User;

/**
 * web层通用数据处理
 * 
 * @author yangzz
 */
public class BaseController
{
    /**
     * 获取请求分页数据
     */
    public PageUtilEntity getPageUtilEntity()
    {
        PageUtilEntity pageUtilEntity = TableSupport.buildPageRequest();
        return pageUtilEntity;
    }

    public User getUser()
    {
        return ShiroUtils.getUser();
    }

    public Long getUserId()
    {
        return getUser().getUserId();
    }

    public String getLoginName()
    {
        return getUser().getLoginName();
    }
}
