package com.ruoyi.project.monitor.logininfor.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
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
    public List<Logininfor> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<Logininfor> logininforList = null;
        try
        {
            logininforList = this.findForList("SystemLogininforMapper.pageInfoQueryLogininfor", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return logininforList;
    }

}
