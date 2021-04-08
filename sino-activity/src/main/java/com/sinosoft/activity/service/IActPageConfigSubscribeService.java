package com.sinosoft.activity.service;

import java.util.List;
import com.sinosoft.activity.domain.ActPageConfigSubscribe;

/**
 * 活动收集配置Service接口
 * 
 * @author dy
 * @date 2021-04-08
 */
public interface IActPageConfigSubscribeService 
{
    /**
     * 查询活动收集配置
     * 
     * @param id 活动收集配置ID
     * @return 活动收集配置
     */
    public ActPageConfigSubscribe selectActPageConfigSubscribeById(Integer id);

    /**
     * 查询活动收集配置列表
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 活动收集配置集合
     */
    public List<ActPageConfigSubscribe> selectActPageConfigSubscribeList(ActPageConfigSubscribe actPageConfigSubscribe);

    /**
     * 新增活动收集配置
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 结果
     */
    public int insertActPageConfigSubscribe(ActPageConfigSubscribe actPageConfigSubscribe);

    /**
     * 修改活动收集配置
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 结果
     */
    public int updateActPageConfigSubscribe(ActPageConfigSubscribe actPageConfigSubscribe);

    /**
     * 批量删除活动收集配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActPageConfigSubscribeByIds(String ids);

    /**
     * 删除活动收集配置信息
     * 
     * @param id 活动收集配置ID
     * @return 结果
     */
    public int deleteActPageConfigSubscribeById(Integer id);
}
