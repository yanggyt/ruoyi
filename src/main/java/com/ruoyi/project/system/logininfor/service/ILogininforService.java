package com.ruoyi.project.system.logininfor.service;

import com.ruoyi.project.system.logininfor.domain.Logininfor;

/**
 * 系统访问日志情况信息 服务层
 * 
 * @author yangzz
 */
public interface ILogininforService
{

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);
}
