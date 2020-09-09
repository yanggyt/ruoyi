package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisMerchantWechat;

import java.util.List;

/**
 * 微信商户Service接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface IHisMerchantWechatService
{
    /**
     * 查询微信商户
     * 
     * @param id 微信商户ID
     * @return 微信商户
     */
    public HisMerchantWechat selectHisMerchantWechatById(Long id);

    /**
     * 查询微信商户
     *
     * @param hisMerchantWechat 微信商户
     * @return 微信商户
     */
    public HisMerchantWechat selectHisMerchantWechat(HisMerchantWechat hisMerchantWechat);

    /**
     * 查询微信商户列表
     * 
     * @param hisMerchantWechat 微信商户
     * @return 微信商户集合
     */
    public List<HisMerchantWechat> selectHisMerchantWechatList(HisMerchantWechat hisMerchantWechat);

    /**
     * 新增微信商户
     * 
     * @param hisMerchantWechat 微信商户
     * @return 结果
     */
    public int insertHisMerchantWechat(HisMerchantWechat hisMerchantWechat);

    /**
     * 批量新增微信商户
     *
     * @param hisMerchantWechatList 微信商户列表
     * @return 结果
     */
    public int insertHisMerchantWechatBatch(List<HisMerchantWechat> hisMerchantWechatList);

    /**
     * 修改微信商户
     * 
     * @param hisMerchantWechat 微信商户
     * @return 结果
     */
    public int updateHisMerchantWechat(HisMerchantWechat hisMerchantWechat);

    /**
     * 批量删除微信商户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisMerchantWechatByIds(String ids);

    /**
     * 删除微信商户信息
     * 
     * @param id 微信商户ID
     * @return 结果
     */
    public int deleteHisMerchantWechatById(Long id);
}
