package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisPayAccount;

import java.util.List;

/**
 * 支付账户Mapper接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface HisPayAccountMapper extends RuoYiBaseMapper<HisPayAccount>
{

    /**
     * 查询支付账户
     *
     * @param id 支付账户ID
     * @return 支付账户
     */
    public HisPayAccount selectHisPayAccountById(Long id);

    /**
     * 查询支付账户列表
     * 
     * @param hisPayAccount 支付账户
     * @return 支付账户集合
     */
    public List<HisPayAccount> selectHisPayAccountList(HisPayAccount hisPayAccount);


}
