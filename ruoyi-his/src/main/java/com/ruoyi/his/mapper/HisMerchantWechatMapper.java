package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisMerchantWechat;

import java.util.List;

/**
 * 特约商户Mapper接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface HisMerchantWechatMapper extends RuoYiBaseMapper<HisMerchantWechat>
{

    /**
     * 查询特约商户
     *
     * @param id 特约商户ID
     * @return 特约商户
     */
    public HisMerchantWechat selectHisMerchantWechatById(Long id);

    /**
     * 查询特约商户列表
     * 
     * @param hisMerchantWechat 特约商户
     * @return 特约商户集合
     */
    public List<HisMerchantWechat> selectHisMerchantWechatList(HisMerchantWechat hisMerchantWechat);


}
