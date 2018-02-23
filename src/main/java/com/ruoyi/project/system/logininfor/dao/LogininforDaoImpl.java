package com.ruoyi.project.system.logininfor.dao;

import org.springframework.stereotype.Repository;

import com.ruoyi.framework.core.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.logininfor.domain.Logininfor;

@Repository("logininforDao")
public class LogininforDaoImpl extends DynamicObjectBaseDao implements ILogininforDao
{

    @Override
    public void insertLogininfor(Logininfor logininfor)
    {
        this.save("SystemLogininforMapper.insertLogininfor", logininfor);
    }

}
