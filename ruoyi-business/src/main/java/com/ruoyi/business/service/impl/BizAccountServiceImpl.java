package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizAccountDetail;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.service.IBizAccountService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 会员账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-14
 */
@Service
public class BizAccountServiceImpl implements IBizAccountService
{
    @Resource
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


    /**
     * 查询会员账户明细列表
     *
     * @param bizAccountDetail 会员账户明细
     * @return 会员账户明细集合
     */
    public List<BizAccountDetail> selectBizAccountDetailList(BizAccountDetail bizAccountDetail)
    {
        return bizAccountMapper.selectBizAccountDetailList(bizAccountDetail);
    }

    /**
     * 会员福豆变动明细
     *
     * @param memberID accountType detailType money businessInfo desc
     * @return boolean
     */
    @Override
    public boolean accountChange(Long memberID, int accountType, int detailType, Long money, String businessInfo, String desc)
    {
        //取出用户账户
        BizAccount bizAccount = new BizAccount();
        bizAccount.setMemberId(memberID);
        bizAccount.setAccountType(accountType);
        List<BizAccount> accountList = bizAccountMapper.selectBizAccountList(bizAccount);
        if (accountList.size() == 0) {
            return false;
        }
        bizAccount = accountList.get(0);
        //减去的话判断金额
        int changeType = money >= 0 ? BizAccountDetail.DOU_CHANGE_TYPE_ADD : BizAccountDetail.DOU_CHANGE_TYPE_REDUSE;
        Long beforeMoney = bizAccount.getAmount().longValue();
        if (changeType == BizAccountDetail.DOU_CHANGE_TYPE_REDUSE && beforeMoney < -money) {
            return false;
        }
        //增加余额
        Long afterMoney = beforeMoney + money;
        bizAccount.setAmount(new BigDecimal(afterMoney));
        bizAccountMapper.updateBizAccount(bizAccount);

        //详细记录
        BizAccountDetail detail = new BizAccountDetail();
        detail.setMemberId(memberID);
        detail.setAccountId(bizAccount.getId());
        detail.setAccountType(accountType);
        detail.setBusinessNo(businessInfo);
        detail.setChangeType(changeType);
        detail.setTypeDetail(detailType);
        detail.setAmount(money);
        detail.setBeforeAmount(beforeMoney);
        detail.setAfterAmount(afterMoney);
        detail.setChangeDesc(desc);
        detail.setCreateTime(new Date());
        bizAccountMapper.insertBizAccountDetail(detail);
        return true;
    }

    /**
     * 清空福豆田
     *
     * @param
     * @return
     */
    @Override
    @Transactional
    public void clearAllDouField()
    {

        bizAccountMapper.clearAllDouField();
    }
}
