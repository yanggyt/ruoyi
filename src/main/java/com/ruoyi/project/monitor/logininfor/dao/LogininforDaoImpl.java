package com.ruoyi.project.monitor.logininfor.dao;

import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.logininfor.domain.Logininfor;

/**
 * 登录日志记录 数据层
 * 
 * @author ruoyi
 */
@Repository("logininforDao")
public class LogininforDaoImpl extends DynamicObjectBaseDao implements ILogininforDao
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(Logininfor logininfor)
    {
        this.save("SystemLogininforMapper.insertLogininfor", logininfor);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 登录记录集合
     */
    @Override
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemLogininforMapper.pageInfoQueryLogininfor", pageUtilEntity);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int batchDeleteLogininfor(Long[] ids)
    {
        int rows = 0;
        try
        {
            rows = this.batchDelete("SystemLogininforMapper.batchDeleteLogininfor", ids);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rows;
    }
}
