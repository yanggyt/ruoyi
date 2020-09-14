package com.ruoyi.business.sync;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.utils.DateUtils;

public class UserDataListener extends AnalysisEventListener<UserData> {

    private IBizMemberService bizMemberService;

    public UserDataListener(IBizMemberService bizMemberService) {
        this.bizMemberService = bizMemberService;
    }

    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        BizMember member = new BizMember();
        member.setMemberName(userData.getName());
        member.setPassword(userData.getPassword());
        member.setIsDelete(0);
        member.setIsEnable(Integer.parseInt(userData.getEnable()));
        member.setCreateTime(DateUtils.parseDate(userData.getAddtim()));
        member.setMobile(userData.getReferrerTelephone());
        member.setRecommendMobile(userData.getReferrerTelephone());
        member.setRecommendName(userData.getReferrer());
        System.out.println(userData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
