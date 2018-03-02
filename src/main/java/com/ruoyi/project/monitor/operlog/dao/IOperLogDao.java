package com.ruoyi.project.monitor.operlog.dao;

import java.util.List;
import com.ruoyi.framework.web.page.PageUtilEntity;
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

    /**
     * 查询系统操作日志集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 操作日志集合
     */
    public List<OperLog> pageInfoQuery(PageUtilEntity pageUtilEntity);
    
    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int batchDeleteOperLog(Long[] ids);
}
