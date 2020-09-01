package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisPayAccountMapper;
import com.ruoyi.his.domain.HisPayAccount;
import com.ruoyi.his.service.IHisPayAccountService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 支付账户Service业务层处理
 * 
 * @author bend
 * @date 2020-07-14
 */
@Service
public class HisPayAccountServiceImpl implements IHisPayAccountService
{
    @Resource
    private HisPayAccountMapper hisPayAccountMapper;

    /**
     * 查询支付账户
     * 
     * @param id 支付账户ID
     * @return 支付账户
     */
    @Override
    public HisPayAccount selectHisPayAccountById(Long id)
    {
        return hisPayAccountMapper.selectHisPayAccountById(id);
    }

    /**
     * 查询支付账户
     *
     * @param hisPayAccount 支付账户ID
     * @return 支付账户
     */
    @Override
    public HisPayAccount selectHisPayAccount(HisPayAccount hisPayAccount)
    {
        return hisPayAccountMapper.selectOne(hisPayAccount);
    }

    /**
     * 查询支付账户列表
     * 
     * @param hisPayAccount 支付账户
     * @return 支付账户
     */
    @Override
    public List<HisPayAccount> selectHisPayAccountList(HisPayAccount hisPayAccount)
    {
        return hisPayAccountMapper.selectHisPayAccountList(hisPayAccount);
    }

    /**
     * 新增支付账户
     * 
     * @param hisPayAccount 支付账户
     * @return 结果
     */
    @Override
    public int insertHisPayAccount(HisPayAccount hisPayAccount)
    {
        return hisPayAccountMapper.insertSelective(hisPayAccount);
    }

    /**
     * 批量新增支付账户
     *
     * @param hisPayAccountList 支付账户列表
     * @return 结果
     */
    @Override
    public int insertHisPayAccountBatch(List<HisPayAccount> hisPayAccountList)
    {
        return hisPayAccountMapper.insertList(hisPayAccountList);
    }

    /**
     * 修改支付账户
     * 
     * @param hisPayAccount 支付账户
     * @return 结果
     */
    @Override
    public int updateHisPayAccount(HisPayAccount hisPayAccount)
    {
        return hisPayAccountMapper.updateByPrimaryKeySelective(hisPayAccount);
    }

    /**
     * 删除支付账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisPayAccountByIds(String ids)
    {
        return hisPayAccountMapper.deleteByIds(ids);
    }

    /**
     * 删除支付账户信息
     * 
     * @param id 支付账户ID
     * @return 结果
     */
    @Override
    public int deleteHisPayAccountById(Long id)
    {
        return hisPayAccountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changeStatus(HisPayAccount hisPayAccount)
    {
        return hisPayAccountMapper.updateByPrimaryKeySelective(hisPayAccount);
    }
}
