package com.ruoyi.project.monitor.operlog.dao;

import org.springframework.stereotype.Repository;

import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志记录 数据层
 * 
 * @author ruoyi
 */
@Repository("operLogDao")
public class OperLogDaoImpl extends DynamicObjectBaseDao implements IOperLogDao
{
    /**
     * 新增操作日志
     * 
     * @param operLog 系统日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog)
    {
        this.save("SystemOperLogMapper.insertOperlog", operLog);
    }
}
