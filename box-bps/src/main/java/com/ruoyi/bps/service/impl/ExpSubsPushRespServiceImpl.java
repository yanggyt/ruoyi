package com.ruoyi.bps.service.impl;

import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.mapper.ExpSubsPushRespMapper;
import com.ruoyi.bps.service.IExpSubsPushRespService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快递订阅推送信息Service业务层处理
 * 
 * @author box
 * @date 2021-05-13
 */
@Service
public class ExpSubsPushRespServiceImpl implements IExpSubsPushRespService
{
    @Autowired
    private ExpSubsPushRespMapper expSubsPushRespMapper;

    /**
     * 查询快递订阅推送信息
     * 
     * @param sid 快递订阅推送信息ID
     * @return 快递订阅推送信息
     */
    @Override
    public ExpSubsPushResp selectExpSubsPushRespById(Long sid)
    {
        return expSubsPushRespMapper.selectExpSubsPushRespById(sid);
    }

    /**
     * 查询快递订阅推送信息列表
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 快递订阅推送信息
     */
    @Override
    public List<ExpSubsPushResp> selectExpSubsPushRespList(ExpSubsPushResp expSubsPushResp)
    {
        return expSubsPushRespMapper.selectExpSubsPushRespList(expSubsPushResp);
    }

    /**
     * 新增快递订阅推送信息
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 结果
     */
    @Override
    public int insertExpSubsPushResp(ExpSubsPushResp expSubsPushResp)
    {
        return expSubsPushRespMapper.insertExpSubsPushResp(expSubsPushResp);
    }

    /**
     * 修改快递订阅推送信息
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 结果
     */
    @Override
    public int updateExpSubsPushResp(ExpSubsPushResp expSubsPushResp)
    {
        return expSubsPushRespMapper.updateExpSubsPushResp(expSubsPushResp);
    }

    /**
     * 删除快递订阅推送信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpSubsPushRespByIds(String ids)
    {
        return expSubsPushRespMapper.deleteExpSubsPushRespByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除快递订阅推送信息信息
     * 
     * @param sid 快递订阅推送信息ID
     * @return 结果
     */
    @Override
    public int deleteExpSubsPushRespById(Long sid)
    {
        return expSubsPushRespMapper.deleteExpSubsPushRespById(sid);
    }
}
