package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisPayAccount;

import java.util.List;

/**
 * 支付账户Service接口
 * 
 * @author bend
 * @date 2020-07-14
 */
public interface IHisPayAccountService
{
    /**
     * 查询支付账户
     * 
     * @param id 支付账户ID
     * @return 支付账户
     */
    public HisPayAccount selectHisPayAccountById(Long id);

    /**
     * 查询支付账户
     *
     * @param hisPayAccount 支付账户
     * @return 支付账户
     */
    public HisPayAccount selectHisPayAccount(HisPayAccount hisPayAccount);

    /**
     * 查询支付账户列表
     * 
     * @param hisPayAccount 支付账户
     * @return 支付账户集合
     */
    public List<HisPayAccount> selectHisPayAccountList(HisPayAccount hisPayAccount);

    /**
     * 新增支付账户
     * 
     * @param hisPayAccount 支付账户
     * @return 结果
     */
    public int insertHisPayAccount(HisPayAccount hisPayAccount);

    /**
     * 批量新增支付账户
     *
     * @param hisPayAccountList 支付账户列表
     * @return 结果
     */
    public int insertHisPayAccountBatch(List<HisPayAccount> hisPayAccountList);

    /**
     * 修改支付账户
     * 
     * @param hisPayAccount 支付账户
     * @return 结果
     */
    public int updateHisPayAccount(HisPayAccount hisPayAccount);

    /**
     * 批量删除支付账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisPayAccountByIds(String ids);

    /**
     * 删除支付账户信息
     * 
     * @param id 支付账户ID
     * @return 结果
     */
    public int deleteHisPayAccountById(Long id);

    public int changeStatus(HisPayAccount hisPayAccount);
}
