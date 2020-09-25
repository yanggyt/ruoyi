package com.ruoyi.business.sync;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.domain.BizMemberAddress;
import com.ruoyi.business.mapper.BizMemberAddressMapper;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.common.utils.DateUtils;

import java.util.Objects;

public class UserAddressDataListener extends AnalysisEventListener<UserAddressData> {

    private BizMemberAddressMapper memberAddressMapper;

    private BizMemberMapper memberMapper;

    public UserAddressDataListener(BizMemberAddressMapper memberAddressMapper, BizMemberMapper memberMapper) {
        this.memberAddressMapper = memberAddressMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public void invoke(UserAddressData userAddressData, AnalysisContext analysisContext) {
        BizMemberAddress memberAddress = new BizMemberAddress();
        memberAddress.setId(Long.valueOf(userAddressData.getId()));
        memberAddress.setAddress(userAddressData.getDetailsAddress());
        memberAddress.setProvinceCode(userAddressData.getReceiveProvinceCode());
        memberAddress.setProvinceName(userAddressData.getReceiveProvinceName());
        memberAddress.setCityCode(userAddressData.getReceiveCityCode());
        memberAddress.setCityName(userAddressData.getReceiveCityName());
        memberAddress.setAreaCode(userAddressData.getReceiveAreaCode());
        memberAddress.setAreaName(userAddressData.getReceiveAreaName());
        memberAddress.setIsDefault("Y".equals(userAddressData.getIsDefalut()) ? 1 : 0);
        memberAddress.setIsDelete(0);
        memberAddress.setMobile(userAddressData.getReceiveTelephone());
        memberAddress.setMemberName(userAddressData.getReceiveUser());
        memberAddress.setCreateBy("admin");
        memberAddress.setCreateTime(DateUtils.getNowDate());

        BizMember member = memberMapper.selectBizMemberByMobile(userAddressData.getReceiveTelephone());
        if (!Objects.isNull(member)) {
            memberAddress.setMemberID(member.getId());
        } else {
            memberAddress.setMemberID(0L);
        }
        memberAddressMapper.insertBizMemberAddress(memberAddress);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
