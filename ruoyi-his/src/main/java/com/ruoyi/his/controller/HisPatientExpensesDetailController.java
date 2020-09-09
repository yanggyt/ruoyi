package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisPatientExpensesDetail;
import com.ruoyi.his.service.IHisPatientExpensesDetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 费用详情Controller
 * 
 * @author bend
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/his/patientExpensesDetail")
public class HisPatientExpensesDetailController extends BaseController
{
    private String prefix = "his/patientExpensesDetail";

    @Autowired
    private IHisPatientExpensesDetailService hisPatientExpensesDetailService;

    @RequiresPermissions("his:patientExpensesDetail:view")
    @GetMapping()
    public String patientExpensesDetail()
    {
        return prefix + "/patientExpensesDetail";
    }

    /**
     * 查询费用详情列表
     */
    @RequiresPermissions("his:patientExpensesDetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisPatientExpensesDetail hisPatientExpensesDetail)
    {
        startPage();
        List<HisPatientExpensesDetail> list = hisPatientExpensesDetailService.selectHisPatientExpensesDetailList(hisPatientExpensesDetail);
        return getDataTable(list);
    }

    /**
     * 导出费用详情列表
     */
    @RequiresPermissions("his:patientExpensesDetail:export")
    @Log(title = "费用详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisPatientExpensesDetail hisPatientExpensesDetail)
    {
        List<HisPatientExpensesDetail> list = hisPatientExpensesDetailService.selectHisPatientExpensesDetailList(hisPatientExpensesDetail);
        ExcelUtil<HisPatientExpensesDetail> util = new ExcelUtil<HisPatientExpensesDetail>(HisPatientExpensesDetail.class);
        return util.exportExcel(list, "patientExpensesDetail");
    }
}
