package com.ruoyi.business.sync;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.common.utils.DateUtils;

import java.math.BigDecimal;

public class UserDataListener extends AnalysisEventListener<UserData> {

    private BizMemberMapper memberMapper;

    private BizAccountMapper accountMapper;

    public UserDataListener(BizMemberMapper memberMapper, BizAccountMapper accountMapper) {
        this.memberMapper = memberMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        BizMember member = new BizMember();
        member.setMemberName(userData.getName());
        member.setPassword(userData.getPassword());
        member.setIsDelete(0);
        member.setIsEnable("Y".equals(userData.getEnable()) ? 1 : 0);
        member.setCreateTime(DateUtils.parseDate(userData.getAddtim()));
        member.setUpdateTime(DateUtils.parseDate(userData.getAddtim()));
        member.setMobile(userData.getReferrerTelephone());
        member.setRecommendMobile(userData.getReferrerTelephone());
        member.setRecommendName(userData.getReferrer());
        member.setId(Long.valueOf(userData.getId()));
        memberMapper.insertBizMember(member);

        // 创建专项福豆账户
        BizAccount account = new BizAccount();
        account.setMemberId(member.getId());
        account.setAccountType(3);
        account.setAmount(new BigDecimal(userData.getTourismPorints()));
        account.setCreateBy("admin");
        account.setCreateTime(member.getCreateTime());
        account.setUpdateTime(member.getUpdateTime());
        accountMapper.insertBizAccount(account);

        // 创建福豆余额账户
        BizAccount account1 = new BizAccount();
        account1.setMemberId(member.getId());
        account1.setAccountType(0);
        account1.setAmount(new BigDecimal(userData.getPorintsSurplus()));
        account1.setCreateBy("admin");
        account1.setCreateTime(member.getCreateTime());
        account1.setUpdateTime(member.getUpdateTime());
        accountMapper.insertBizAccount(account1);

        // 创建个人（可用）福豆账户
        BizAccount account2 = new BizAccount();
        account2.setMemberId(member.getId());
        account2.setAccountType(1);
        account2.setAmount(new BigDecimal(userData.getPecialItemPorints()));
        account2.setCreateBy("admin");
        account2.setCreateTime(member.getCreateTime());
        account2.setUpdateTime(member.getUpdateTime());
        accountMapper.insertBizAccount(account2);

        // 创建团队福豆账户
        BizAccount account3 = new BizAccount();
        account3.setMemberId(member.getId());
        account3.setAccountType(2);
        account3.setAmount(new BigDecimal(userData.getTreamPorints()));
        account3.setCreateBy("admin");
        account3.setCreateTime(member.getCreateTime());
        account3.setUpdateTime(member.getUpdateTime());
        accountMapper.insertBizAccount(account3);

        // 创建福豆田账户
        BizAccount account4 = new BizAccount();
        account4.setMemberId(member.getId());
        account4.setAccountType(4);
        account4.setAmount(new BigDecimal(userData.getFieldsPorint()));
        account4.setCreateBy("admin");
        account4.setCreateTime(member.getCreateTime());
        account4.setUpdateTime(member.getUpdateTime());
        accountMapper.insertBizAccount(account4);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
