package com.ruoyi.business.service;

import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizAccountDetail;

import java.util.List;

/**
 * 会员账户Service接口
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
public interface IBizAccountService 
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
     * 批量删除会员账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizAccountByIds(String ids);

    /**
     * 删除会员账户信息
     * 
     * @param id 会员账户ID
     * @return 结果
     */
    public int deleteBizAccountById(Long id);

    /**
     * 查询会员账户明细列表
     *
     * @param bizAccountDetail 会员账户明细
     * @return 会员账户明细集合
     */
    public List<BizAccountDetail> selectBizAccountDetailList(BizAccountDetail bizAccountDetail);

    /**
     * 会员福豆变动明细
     *
     * @param memberID accountType detailType money businessInfo desc
     * @return boolean
     */
    public boolean accountChange(Long memberID, int accountType, int detailType, Long money, String businessInfo, String desc);
}
