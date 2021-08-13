package com.ruoyi.bps.service;

import java.util.List;
import com.ruoyi.bps.domain.ExpTopgpLog;

/**
 * ERP订阅推送日志Service接口
 * 
 * @author Bo
 * @date 2021-08-11
 */
public interface IExpTopgpLogService 
{
    /**
     * 查询ERP订阅推送日志
     * 
     * @param sid ERP订阅推送日志主键
     * @return ERP订阅推送日志
     */
    public ExpTopgpLog selectExpTopgpLogBySid(Long sid);

    /**
     * 查询ERP订阅推送日志列表
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return ERP订阅推送日志集合
     */
    public List<ExpTopgpLog> selectExpTopgpLogList(ExpTopgpLog expTopgpLog);

    /**
     * 新增ERP订阅推送日志
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return 结果
     */
    public int insertExpTopgpLog(ExpTopgpLog expTopgpLog);

    /**
     * 修改ERP订阅推送日志
     * 
     * @param expTopgpLog ERP订阅推送日志
     * @return 结果
     */
    public int updateExpTopgpLog(ExpTopgpLog expTopgpLog);

    /**
     * 批量删除ERP订阅推送日志
     * 
     * @param sids 需要删除的ERP订阅推送日志主键集合
     * @return 结果
     */
    public int deleteExpTopgpLogBySids(String sids);

    /**
     * 删除ERP订阅推送日志信息
     * 
     * @param sid ERP订阅推送日志主键
     * @return 结果
     */
    public int deleteExpTopgpLogBySid(Long sid);
}
