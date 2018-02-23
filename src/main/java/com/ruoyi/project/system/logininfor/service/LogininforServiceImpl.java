package com.ruoyi.project.system.logininfor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.logininfor.dao.ILogininforDao;
import com.ruoyi.project.system.logininfor.domain.Logininfor;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author yangzz
 */
@Service("logininforService")
public class LogininforServiceImpl implements ILogininforService
{

    @Autowired
    private ILogininforDao logininforDao;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor)
    {
        logininforDao.insertLogininfor(logininfor);
    }
}
