package com.ruoyi.business.ajax;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.ruoyi.business.domain.BizAccount;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.mapper.*;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.business.sync.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ajax/data")
public class SyncDataController extends BaseController {

    @Resource
    private BizMemberMapper memberMapper;
    @Resource
    private BizAccountMapper accountMapper;
    @Resource
    private BizProductMapper productMapper;
    @Resource
    private BizProductTypeMapper productTypeMapper;
    @Resource
    private BizMemberAddressMapper memberAddressMapper;
    @Resource
    private BizOrderMapper orderMapper;

    @PostMapping("/user")
    public AjaxResult user(@RequestParam("file") MultipartFile file) {
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(file.getInputStream(), UserData.class, new UserDataListener(memberMapper, accountMapper)).build();
            reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.finish();
        }
        return AjaxResult.success();
    }

    @PostMapping("/goods")
    public AjaxResult goods(@RequestParam("file") MultipartFile file) {
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(file.getInputStream(), GoodsData.class, new GoodsDataListener(productMapper, productTypeMapper)).build();
            reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.finish();
        }
        return AjaxResult.success();
    }

    @PostMapping("/memberAddress")
    public AjaxResult memberAddress(@RequestParam("file") MultipartFile file) {
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(file.getInputStream(), UserAddressData.class, new UserAddressDataListener(memberAddressMapper, memberMapper)).build();
            reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.finish();
        }
        return AjaxResult.success();
    }

    @PostMapping("/order")
    public AjaxResult order(@RequestParam("file") MultipartFile file) {
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(file.getInputStream(), OrderData.class, new OrderDataListener(orderMapper, memberMapper, productMapper)).build();
            reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert reader != null;
            reader.finish();
        }
        return AjaxResult.success();
    }

    @PostMapping("/initUserTree")
    public AjaxResult initUserTree() {
        List<BizMember> memberList = memberMapper.selectBizMemberAll();
        BizMember member = memberMapper.selectBizMemberByMobile("13971055153");
        getChild(member, memberList);
        return AjaxResult.success();
    }

    private void getChild(BizMember member, List<BizMember> memberList) {
        for (BizMember m : memberList) {
            if (member.getMobile().equals(m.getRecommendMobile())) {
                m.setRecommendId(member.getId());
                memberMapper.updateBizMember(m);
                getChild(m, memberList);
            }
        }
    }

    @PostMapping("/initRecommendIds")
    public AjaxResult initRecommendIds() {
        List<BizMember> memberList = memberMapper.selectBizMemberAll();
        for (BizMember member : memberList) {
            String id = getChildIds(member, memberList, new StringBuffer());
            member.setRecommendAllId(id.substring(0, id.length() - 1));
            memberMapper.updateBizMember(member);
        }

        return AjaxResult.success();
    }

    private String getChildIds(BizMember member, List<BizMember> memberList, StringBuffer sb) {
        for (BizMember m : memberList) {
            if (member.getRecommendId().equals(m.getId())) {
                String id = m.getId() + ",";
                sb.insert(0,  id);
                getChildIds(m, memberList, sb);
            }
        }
        return sb.toString();
    }
}
