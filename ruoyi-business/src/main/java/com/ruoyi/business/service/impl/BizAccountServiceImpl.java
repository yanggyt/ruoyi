package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.service.IBizAccountService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 会员账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class BizAccountServiceImpl implements IBizAccountService
{
    @Autowired
    private BizAccountMapper bizAccountMapper;

    /**
     * 查询会员账户
     * 
     * @param id 会员账户ID
     * @return 会员账户
     */
    @Override
    public BizAccount selectBizAccountById(Long id)
    {
        return bizAccountMapper.selectBizAccountById(id);
    }

    /**
     * 查询会员账户列表
     * 
     * @param bizAccount 会员账户
     * @return 会员账户
     */
    @Override
    public List<BizAccount> selectBizAccountList(BizAccount bizAccount)
    {
        return bizAccountMapper.selectBizAccountList(bizAccount);
    }

    /**
     * 新增会员账户
     * 
     * @param bizAccount 会员账户
     * @return 结果
     */
    @Override
    public int insertBizAccount(BizAccount bizAccount)
    {
        bizAccount.setCreateTime(DateUtils.getNowDate());
        return bizAccountMapper.insertBizAccount(bizAccount);
    }

    /**
     * 修改会员账户
     * 
     * @param bizAccount 会员账户
     * @return 结果
     */
    @Override
    public int updateBizAccount(BizAccount bizAccount)
    {
        bizAccount.setUpdateTime(DateUtils.getNowDate());
        return bizAccountMapper.updateBizAccount(bizAccount);
    }

    /**
     * 删除会员账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizAccountByIds(String ids)
    {
        return bizAccountMapper.deleteBizAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员账户信息
     * 
     * @param id 会员账户ID
     * @return 结果
     */
    @Override
    public int deleteBizAccountById(Long id)
    {
        return bizAccountMapper.deleteBizAccountById(id);
    }
}
