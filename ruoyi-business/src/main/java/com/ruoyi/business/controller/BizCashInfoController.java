package com.ruoyi.business.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BizCashInfo;
import com.ruoyi.business.service.IBizCashInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 兑现申请记录Controller
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
@Controller
@RequestMapping("/business/cash")
public class BizCashInfoController extends BaseController
{
    private String prefix = "business/cash";

    @Resource
    private IBizCashInfoService bizCashInfoService;

    @RequiresPermissions("business:cash:view")
    @GetMapping()
    public String cash()
    {
        return prefix + "/cash";
    }

    /**
     * 查询兑现申请记录列表
     */
    @RequiresPermissions("business:cash:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizCashInfo bizCashInfo)
    {
        startPage();
        List<BizCashInfo> list = bizCashInfoService.selectBizCashInfoList(bizCashInfo);
        return getDataTable(list);
    }

    /**
     * 导出兑现申请记录列表
     */
    @RequiresPermissions("business:cash:export")
    @Log(title = "兑现申请记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizCashInfo bizCashInfo)
    {
        List<BizCashInfo> list = bizCashInfoService.selectBizCashInfoList(bizCashInfo);
        ExcelUtil<BizCashInfo> util = new ExcelUtil<BizCashInfo>(BizCashInfo.class);
        return util.exportExcel(list, "cash");
    }

    /**
     * 批准兑现申请记录
     */
    @RequiresPermissions("business:cash:edit")
    @Log(title = "兑现申请记录", businessType = BusinessType.UPDATE)
    @PostMapping("/agree")
    @ResponseBody
    public AjaxResult agreeInfo(Long cashInfoID)
    {
        BizCashInfo cashInfo = bizCashInfoService.selectBizCashInfoById(cashInfoID);
        if (cashInfo.getStatus() == BizCashInfo.CASH_INFO_STATUS_INITIAL) {
            cashInfo.setStatus(BizCashInfo.CASH_INFO_STATUS_AGREE);
            bizCashInfoService.updateBizCashInfo(cashInfo);
        }
        return AjaxResult.success();
    }


}
