package com.ruoyi.project.monitor.operlog.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
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

    /**
     * 查询系统操作日志集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 操作日志集合
     */
    public List<OperLog> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<OperLog> logininforList = null;
        try
        {
            logininforList = this.findForList("SystemOperLogMapper.pageInfoQueryOperLog", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return logininforList;
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int batchDeleteOperLog(Long[] ids)
    {
        int rows = 0;
        try
        {
            rows = this.batchDelete("SystemOperLogMapper.batchDeleteOperLog", ids);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rows;
    }
    
    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public OperLog selectOperLogById(Long operId)
    {
        return this.findForObject("SystemOperLogMapper.selectOperLogById", operId);
    }
}
