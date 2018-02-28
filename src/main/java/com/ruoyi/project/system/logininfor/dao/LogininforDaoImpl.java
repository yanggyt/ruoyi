package com.ruoyi.project.system.logininfor.dao;

import org.springframework.stereotype.Repository;

import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.logininfor.domain.Logininfor;

/**
 * 登录日志记录 数据层
 * 
 * @author yangzz
 */
@Repository("logininforDao")
public class LogininforDaoImpl extends DynamicObjectBaseDao implements ILogininforDao
{

    @Override
    public void insertLogininfor(Logininfor logininfor)
    {
        this.save("SystemLogininforMapper.insertLogininfor", logininfor);
    }

}
