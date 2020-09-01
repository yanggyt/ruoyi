package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisWechatProvider;
import com.ruoyi.his.domain.HisMerchantWechat;

import java.util.List;

/**
 * 微信服务商Mapper接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface HisWechatProviderMapper extends RuoYiBaseMapper<HisWechatProvider>
{

    /**
     * 查询微信服务商
     *
     * @param id 微信服务商ID
     * @return 微信服务商
     */
    public HisWechatProvider selectHisWechatProviderById(Long id);

    /**
     * 查询微信服务商列表
     * 
     * @param hisWechatProvider 微信服务商
     * @return 微信服务商集合
     */
    public List<HisWechatProvider> selectHisWechatProviderList(HisWechatProvider hisWechatProvider);


    /**
     * 批量删除特约商户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisMerchantWechatByProviderIds(String[] ids);

    /**
     * 批量新增特约商户
     *
     * @param hisMerchantWechatList 特约商户列表
     * @return 结果
     */
    public int batchHisMerchantWechat(List<HisMerchantWechat> hisMerchantWechatList);


    /**
     * 通过微信服务商ID删除特约商户信息
     *
     * @param providerId ID
     * @return 结果
     */
    public int deleteHisMerchantWechatByProviderId(Long providerId);

}
