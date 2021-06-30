package com.ruoyi.bps.service;

import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.domain.ExpSubscribe;

import java.util.List;

/**
 * 快递订阅Service接口
 * 
 * @author box
 * @date 2021-05-20
 */
public interface IExpSubscribeService 
{
    /**
     * 查询快递订阅
     * 
     * @param sid 快递订阅ID
     * @return 快递订阅
     */
    public ExpSubscribe selectExpSubscribeById(Long sid);

    /**
     * 查询快递订阅列表
     * 
     * @param expSubscribe 快递订阅
     * @return 快递订阅集合
     */
    public List<ExpSubscribe> selectExpSubscribeList(ExpSubscribe expSubscribe);

    /**
     * 新增快递订阅
     * 
     * @param expSubscribe 快递订阅
     * @return 结果
     */
    public int insertExpSubscribe(ExpSubscribe expSubscribe);

    /**
     * 修改快递订阅
     * 
     * @param expSubscribe 快递订阅
     * @return 结果
     */
    public int updateExpSubscribe(ExpSubscribe expSubscribe);

    /**
     * 批量删除快递订阅
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpSubscribeByIds(String ids);

    /**
     * 删除快递订阅信息
     * 
     * @param sid 快递订阅ID
     * @return 结果
     */
    public int deleteExpSubscribeById(Long sid);

    /**
     * 根据快递单号查询快递订阅推送信息
     *
     * @param number 快递单号List
     * @return 快递订阅推送信息
     */
    public List<ExpSubscribe> selectExpSubsPushRespByNumber(List<String> number);
}
