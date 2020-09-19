package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizAccountDetail;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.text.Convert;

import javax.annotation.Resource;

/**
 * 会员Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
@Service
public class BizMemberServiceImpl implements IBizMemberService 
{
    @Resource
    private BizMemberMapper bizMemberMapper;

    @Resource
    private BizAccountMapper bizAccountMapper;

    /**
     * 查询会员
     * 
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public BizMember selectBizMemberById(Long id)
    {
        return bizMemberMapper.selectBizMemberById(id);
    }

    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public BizMember selectBizMemberSimple(Long id)
    {
        return bizMemberMapper.selectBizMemberSimple(id);
    }

    @Override
    public BizMember selectBizMemberByMobile(String mobile) {
        return bizMemberMapper.selectBizMemberByMobile(mobile);
    }

    /**
     * 查询会员列表
     * 
     * @param bizMember 会员
     * @return 会员
     */
    @Override
    public List<BizMember> selectBizMemberList(BizMember bizMember)
    {
        return bizMemberMapper.selectBizMemberList(bizMember);
    }

    /**
     * 会员福豆余额
     *
     * @param memberID type
     * @return 结果
     */
    @Override
    public Long selectBizMemberDou(Long memberID, int type)
    {
        Map map = new HashMap<>();
        map.put("memberID", memberID);
        map.put("type", type);
        return bizMemberMapper.selectBizMemberDou(map);
    }

    /**
     * 新增会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    @Override
    public int insertBizMember(BizMember bizMember)
    {
        bizMember.setCreateTime(DateUtils.getNowDate());
        return bizMemberMapper.insertBizMember(bizMember);
    }

    /**
     * 修改会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    @Override
    public int updateBizMember(BizMember bizMember)
    {
        bizMember.setUpdateTime(DateUtils.getNowDate());
        return bizMemberMapper.updateBizMember(bizMember);
    }

    /**
     * 修改会员
     *
     * @param bizMember 会员
     * @return 结果
     */
    @Override
    public int updateBizMemberAndDou(BizMember bizMember)
    {
        Long memberId = bizMember.getId();
        //修改姓名和手机号
        BizMember oldBizMember = selectBizMemberSimple(memberId);
        oldBizMember.setMobile(bizMember.getMobile());
        oldBizMember.setMemberName(bizMember.getMemberName());
        updateBizMember(oldBizMember);
        //修改五项福豆
        BizAccount bizAccount = new BizAccount();
        bizAccount.setMemberId(memberId);
        List<BizAccount> accountList = bizAccountMapper.selectBizAccountList(bizAccount);
        for (BizAccount account : accountList) {
            Long oldAmount = account.getAmount().longValue();
            Long newAmount = 0L;
            switch (account.getAccountType()) {
                case BizAccount.DOU_BALANCE:
                    newAmount = bizMember.getDouBalance();
                    break;
                case BizAccount.DOU_PERSON:
                    newAmount = bizMember.getDouPerson();
                    break;
                case BizAccount.DOU_TEAM:
                    newAmount = bizMember.getDouTeam();
                    break;
                case BizAccount.DOU_SPECIAL:
                    newAmount = bizMember.getDouSpecial();
                    break;
                case BizAccount.DOU_FIELD:
                    newAmount = bizMember.getDouField();
                    break;
            }
            //数据不一致则更新最新账户余额
            if (!newAmount.equals(oldAmount)) {
                account.setAmount(new BigDecimal(newAmount));
                bizAccountMapper.updateBizAccount(account);
            }
        }

        return 1;
    }

    /**
     * 删除会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizMemberByIds(String ids)
    {
        return bizMemberMapper.deleteBizMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员信息
     * 
     * @param id 会员ID
     * @return 结果
     */
    @Override
    public int deleteBizMemberById(Long id)
    {
        return bizMemberMapper.deleteBizMemberById(id);
    }

}
