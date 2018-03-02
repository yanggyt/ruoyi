package com.ruoyi.project.monitor.operlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.monitor.operlog.dao.IOperLogDao;
import com.ruoyi.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志 服务层处理
 * 
 * @author ruoyi
 */
@Service("operLogService")
public class OperLogServiceImpl implements IOperLogService
{
    @Autowired
    private IOperLogDao operLogDao;

    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog)
    {
        operLogDao.insertOperlog(operLog);
    }
}
