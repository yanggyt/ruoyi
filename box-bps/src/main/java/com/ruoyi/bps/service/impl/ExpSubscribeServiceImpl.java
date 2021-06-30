package com.ruoyi.bps.service.impl;

import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.mapper.ExpSubscribeMapper;
import com.ruoyi.bps.service.IExpSubscribeService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快递订阅Service业务层处理
 * 
 * @author box
 * @date 2021-05-20
 */
@Service
public class ExpSubscribeServiceImpl implements IExpSubscribeService
{
    @Autowired
    private ExpSubscribeMapper expSubscribeMapper;

    /**
     * 查询快递订阅
     * 
     * @param sid 快递订阅ID
     * @return 快递订阅
     */
    @Override
    public ExpSubscribe selectExpSubscribeById(Long sid)
    {
        return expSubscribeMapper.selectExpSubscribeById(sid);
    }

    /**
     * 查询快递订阅列表
     * 
     * @param expSubscribe 快递订阅
     * @return 快递订阅
     */
    @Override
    public List<ExpSubscribe> selectExpSubscribeList(ExpSubscribe expSubscribe)
    {
        return expSubscribeMapper.selectExpSubscribeList(expSubscribe);
    }

    /**
     * 新增快递订阅
     * 
     * @param expSubscribe 快递订阅
     * @return 结果
     */
    @Override
    public int insertExpSubscribe(ExpSubscribe expSubscribe)
    {
        return expSubscribeMapper.insertExpSubscribe(expSubscribe);
    }

    /**
     * 修改快递订阅
     * 
     * @param expSubscribe 快递订阅
     * @return 结果
     */
    @Override
    public int updateExpSubscribe(ExpSubscribe expSubscribe)
    {
        return expSubscribeMapper.updateExpSubscribe(expSubscribe);
    }

    /**
     * 删除快递订阅对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpSubscribeByIds(String ids)
    {
        return expSubscribeMapper.deleteExpSubscribeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除快递订阅信息
     * 
     * @param sid 快递订阅ID
     * @return 结果
     */
    @Override
    public int deleteExpSubscribeById(Long sid)
    {
        return expSubscribeMapper.deleteExpSubscribeById(sid);
    }


    /**
     * 根据快递单号查询快递订阅推送信息
     *
     * @param number 快递单号List
     * @return 快递订阅推送信息
     */
    @Override
    public List<ExpSubscribe> selectExpSubsPushRespByNumber(List<String> number){
        return expSubscribeMapper.selectExpSubscribeByNumber(number);
    }
}
