package com.ruoyi.business.ajax;

import com.ruoyi.business.domain.BizCashInfo;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.domain.BizMemberAddress;
import com.ruoyi.business.service.IBizMemberAddressService;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.annotation.AjaxLogin;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@AjaxLogin
@RestController
@RequestMapping("/ajax/member")
public class AjaxMemberController extends AuthController {

    @Resource
    private IBizMemberService bizMemberService;

    @Resource
    private IBizMemberAddressService bizMemberAddressService;

    //个人中心
    @PostMapping("/center")
    public AjaxResult center()
    {
        Long userID = getUserID();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        BizMember member = bizMemberService.selectBizMemberById(userID);
        resultMap.put("name", member.getMemberName());
        resultMap.put("mobile", member.getMobile());
        resultMap.put("douBalance", member.getDouBalance());
        resultMap.put("douPerson", member.getDouPerson());
        resultMap.put("douTeam", member.getDouTeam());
        resultMap.put("douSpecial", member.getDouSpecial());
        resultMap.put("douField", member.getDouField());
        return AjaxResult.success(resultMap);
    }

    //读取我的地址列表
    @PostMapping("/addressList")
    public AjaxResult addressList()
    {
        Long userID = getUserID();
        return AjaxResult.success(bizMemberAddressService.selectBizMemberAddressList(userID));
    }

    //我的地址详细
    @PostMapping("/addressDetail")
    public AjaxResult addressDetail(Long addressID)
    {
        Long userID = getUserID();
        BizMemberAddress address = bizMemberAddressService.selectBizMemberAddressById(addressID);
        if (address == null || address.getMemberID() != userID) {
            return AjaxResult.error("操作有误请重试");
        }
        return AjaxResult.success(address);
    }

    //编辑我的地址
    @PostMapping("/addressEdit")
    public AjaxResult addressDetail(BizMemberAddress bizMemberAddress)
    {
        Long userID = getUserID();
        BizMemberAddress address = bizMemberAddressService.selectBizMemberAddressById(bizMemberAddress.getId());
        if (address == null || address.getMemberID() != userID) {
            return AjaxResult.error("操作有误请重试");
        }
        return AjaxResult.success(bizMemberAddressService.updateBizMemberAddress(bizMemberAddress));
    }

    //删除我的地址
    @PostMapping("/addressDelete")
    public AjaxResult addressDelete(Long addressID)
    {
        Long userID = getUserID();
        BizMemberAddress address = bizMemberAddressService.selectBizMemberAddressById(addressID);
        if (address == null || address.getMemberID() != userID) {
            return AjaxResult.error("操作有误请重试");
        }
        if (bizMemberAddressService.selectBizMemberAddressList(userID).size() <= 1) {
            return AjaxResult.error("不能删除默认收货地址");
        }
        return AjaxResult.success(bizMemberAddressService.deleteBizMemberAddressById(addressID));
    }

    //提现信息
    @PostMapping("/getCashInfo")
    public AjaxResult getCashInfo(int type)
    {
        return null;
    }

    //提现申请
    @PostMapping("/addCashInfo")
    public AjaxResult addCashInfo(BizCashInfo bizCashInfo)
    {

        return null;
    }

    //我的提现信息列表
    @PostMapping("/listCashInfo")
    public AjaxResult listCashInfo()
    {

        return null;
    }
}
