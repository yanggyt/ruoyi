package com.ruoyi.project.system.logininfor.dao;

import com.ruoyi.project.system.logininfor.domain.Logininfor;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author y
 */
public interface ILogininforDao
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);
}
