package com.ruoyi.bps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bps.mapper.ExpTopgpLogMapper;
import com.ruoyi.bps.domain.ExpTopgpLog;
import com.ruoyi.bps.service.IExpTopgpLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * ERP订阅推送日志Service业务层处理
 * 
 * @author Bo
 * @date 2021-08-11
 */
@Service
public class ExpTopgpLogServiceImpl implements IExpTopgpLogService 
{
    @Autowired
    private ExpTopgpLogMapper expTopgpLogMapper;

    /**
     * 查询ERP订阅推送日志
     * 
     * @param sid ERP订阅推送日志主键
     * @return ERP订阅推送日志
     */
    @Override
    public ExpTopgpLog selectExpTopgpLogBySid(Long sid)
    {
        return expTopgpLogMapper.selectExpTopgpLogBySid(sid);
    }

    /**
     * 查询ERP订阅推送日志列表
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return ERP订阅推送日志
     */
    @Override
    public List<ExpTopgpLog> selectExpTopgpLogList(ExpTopgpLog expTopgpLog)
    {
        return expTopgpLogMapper.selectExpTopgpLogList(expTopgpLog);
    }

    /**
     * 新增ERP订阅推送日志
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return 结果
     */
    @Override
    public int insertExpTopgpLog(ExpTopgpLog expTopgpLog)
    {
        return expTopgpLogMapper.insertExpTopgpLog(expTopgpLog);
    }

    /**
     * 修改ERP订阅推送日志
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return 结果
     */
    @Override
    public int updateExpTopgpLog(ExpTopgpLog expTopgpLog)
    {
        return expTopgpLogMapper.updateExpTopgpLog(expTopgpLog);
    }

    /**
     * 批量删除ERP订阅推送日志
     * 
     * @param sids 需要删除的ERP订阅推送日志主键
     * @return 结果
     */
    @Override
    public int deleteExpTopgpLogBySids(String sids)
    {
        return expTopgpLogMapper.deleteExpTopgpLogBySids(Convert.toStrArray(sids));
    }

    /**
     * 删除ERP订阅推送日志信息
     * 
     * @param sid ERP订阅推送日志主键
     * @return 结果
     */
    @Override
    public int deleteExpTopgpLogBySid(Long sid)
    {
        return expTopgpLogMapper.deleteExpTopgpLogBySid(sid);
    }
}
