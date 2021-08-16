package com.ruoyi.bps.service;

import com.ruoyi.bps.domain.ExpSubsPushResp;

import java.util.List;

/**
 * 快递订阅推送信息Service接口
 * 
 * @author box
 * @date 2021-05-13
 */
public interface IExpSubsPushRespService 
{
    /**
     * 查询快递订阅推送信息
     * 
     * @param sid 快递订阅推送信息ID
     * @return 快递订阅推送信息
     */
    public ExpSubsPushResp selectExpSubsPushRespById(Long sid);

    /**
     * 查询快递订阅推送信息列表
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 快递订阅推送信息集合
     */
    public List<ExpSubsPushResp> selectExpSubsPushRespList(ExpSubsPushResp expSubsPushResp);

    /**
     * 新增快递订阅推送信息
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 结果
     */
    public int insertExpSubsPushResp(ExpSubsPushResp expSubsPushResp);

    /**
     * 修改快递订阅推送信息
     * 
     * @param expSubsPushResp 快递订阅推送信息
     * @return 结果
     */
    public int updateExpSubsPushResp(ExpSubsPushResp expSubsPushResp);

    /**
     * 批量删除快递订阅推送信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpSubsPushRespByIds(String ids);

    /**
     * 删除快递订阅推送信息信息
     * 
     * @param sid 快递订阅推送信息ID
     * @return 结果
     */
    public int deleteExpSubsPushRespById(Long sid);

}
