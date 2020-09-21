package com.ruoyi.business.ajax;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.ruoyi.business.domain.BizAccount;
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
            reader = EasyExcel.read(file.getInputStream(), OrderData.class, new OrderDataListener(orderMapper, memberMapper)).build();
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
        return AjaxResult.success();
    }
}
