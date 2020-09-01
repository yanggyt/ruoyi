package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisWechatProvider;

import java.util.List;

/**
 * 微信服务商Service接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface IHisWechatProviderService
{
    /**
     * 查询微信服务商
     * 
     * @param id 微信服务商ID
     * @return 微信服务商
     */
    public HisWechatProvider selectHisWechatProviderById(Long id);

    /**
     * 查询微信服务商
     *
     * @param hisWechatProvider 微信服务商
     * @return 微信服务商
     */
    public HisWechatProvider selectHisWechatProvider(HisWechatProvider hisWechatProvider);

    /**
     * 查询微信服务商列表
     * 
     * @param hisWechatProvider 微信服务商
     * @return 微信服务商集合
     */
    public List<HisWechatProvider> selectHisWechatProviderList(HisWechatProvider hisWechatProvider);

    /**
     * 新增微信服务商
     * 
     * @param hisWechatProvider 微信服务商
     * @return 结果
     */
    public int insertHisWechatProvider(HisWechatProvider hisWechatProvider);

    /**
     * 批量新增微信服务商
     *
     * @param hisWechatProviderList 微信服务商列表
     * @return 结果
     */
    public int insertHisWechatProviderBatch(List<HisWechatProvider> hisWechatProviderList);

    /**
     * 修改微信服务商
     * 
     * @param hisWechatProvider 微信服务商
     * @return 结果
     */
    public int updateHisWechatProvider(HisWechatProvider hisWechatProvider);

    /**
     * 批量删除微信服务商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisWechatProviderByIds(String ids);

    /**
     * 删除微信服务商信息
     * 
     * @param id 微信服务商ID
     * @return 结果
     */
    public int deleteHisWechatProviderById(Long id);
}
