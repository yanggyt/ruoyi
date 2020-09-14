package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.BizAccount;

import java.util.List;

/**
 * 会员账户Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface BizAccountMapper 
{
    /**
     * 查询会员账户
     * 
     * @param id 会员账户ID
     * @return 会员账户
     */
    public BizAccount selectBizAccountById(Long id);

    /**
     * 查询会员账户列表
     * 
     * @param bizAccount 会员账户
     * @return 会员账户集合
     */
    public List<BizAccount> selectBizAccountList(BizAccount bizAccount);

    /**
     * 新增会员账户
     * 
     * @param bizAccount 会员账户
     * @return 结果
     */
    public int insertBizAccount(BizAccount bizAccount);

    /**
     * 修改会员账户
     * 
     * @param bizAccount 会员账户
     * @return 结果
     */
    public int updateBizAccount(BizAccount bizAccount);

    /**
     * 删除会员账户
     * 
     * @param id 会员账户ID
     * @return 结果
     */
    public int deleteBizAccountById(Long id);

    /**
     * 批量删除会员账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAccountByIds(String[] ids);
}
