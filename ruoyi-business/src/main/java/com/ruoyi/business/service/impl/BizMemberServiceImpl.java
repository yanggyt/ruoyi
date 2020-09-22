package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizAccountDetail;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.service.IBizAccountService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IBizAccountService bizAccountService;

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
                //account.setAmount(new BigDecimal(newAmount));
                //bizAccountMapper.updateBizAccount(account);
                bizAccountService.accountChange(memberId, account.getAccountType(), BizAccountDetail.DOU_DETAIL_TYPE_SYSTEM, newAmount - oldAmount, "", BizAccountDetail.DOU_DESC_SYSTEM);
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

    /**
     * 取出会员等级明细及商品购买数
     *
     * @param paramMap
     * @return 结果
     */
    @Override
    public List<Map> selectTeamData(Map paramMap)
    {
        return bizMemberMapper.selectTeamData(paramMap);
    }

    /**
     * 执行专项划拨每日任务
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public int doSpecialTask()
    {
        //划拨金额
        int dailyAmount = Integer.parseInt(DictUtils.getDictLabel("busi_award_set", "2"));
        //出局次数
        int maxLevel = Integer.parseInt(DictUtils.getDictLabel("busi_award_set", "5"));
        //团队盒数标准
        int teamCountLimit = Integer.parseInt(DictUtils.getDictLabel("busi_award_set", "3"));

        List<BizMember> memberList = bizMemberMapper.selectSpecialMember(dailyAmount);
        int accessCount = 0;
        for (BizMember member : memberList) {
            Long memberID = member.getId();
            Long douSpecial = member.getDouSpecial();
            //先扣款
            boolean result = bizAccountService.accountChange(memberID, BizAccount.DOU_SPECIAL, BizAccountDetail.DOU_DETAIL_TYPE_EXCHANGE, (long) -dailyAmount, "", BizAccountDetail.DOU_DESC_SPECIAL2);
            if (result) {
                //加入个人账户
                result = bizAccountService.accountChange(memberID, BizAccount.DOU_PERSON, BizAccountDetail.DOU_DETAIL_TYPE_CHARGE, (long) dailyAmount, "", BizAccountDetail.DOU_DESC_SPECIAL2);
                if (result) {
                    accessCount ++;
                    if (douSpecial <= dailyAmount) {
                        //已经划拨完余额为0,更新用户出局情况
                        long teamCount = bizMemberMapper.getMemberTeamCount(memberID);
                        int specialLevel = member.getSpecialLevel();
                        if (teamCount < teamCountLimit) {   //团队盒数不足设定值盒则更新level
                            //达到最大等级则出局
                            specialLevel = specialLevel == maxLevel ? 0 : (specialLevel + 1);
                            member.setSpecialLevel(specialLevel);
                            bizMemberMapper.updateMemberLevel(member);
                        }
                    }
                }
            }
        }
        return accessCount;
    }
}
