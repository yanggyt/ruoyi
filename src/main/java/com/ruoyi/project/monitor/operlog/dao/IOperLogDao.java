package com.ruoyi.project.monitor.operlog.dao;

import com.ruoyi.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志 数据层
 * 
 * @author ruoyi
 */
public interface IOperLogDao
{
    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    public void insertOperlog(OperLog operLog);
}
