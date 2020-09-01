package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisInspectionReportItem;
import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisInspectionReport;
import com.ruoyi.his.service.IHisInspectionReportService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检查检验Controller
 * 
 * @author bend
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/his/inspectionReport")
public class HisInspectionReportController extends BaseController
{
    private String prefix = "his/inspectionReport";

    @Autowired
    private IHisInspectionReportService hisInspectionReportService;

    @RequiresPermissions("his:inspectionReport:view")
    @GetMapping()
    public String inspectionReport()
    {
        return prefix + "/inspectionReport";
    }

    /**
     * 查询检查检验列表
     */
    @RequiresPermissions("his:inspectionReport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisInspectionReport hisInspectionReport)
    {
        startPage();
        List<HisInspectionReport> list = hisInspectionReportService.selectHisInspectionReportList(hisInspectionReport);
        return getDataTable(list);
    }

    /**
     * 导出检查检验列表
     */
    @RequiresPermissions("his:inspectionReport:export")
    @Log(title = "检查检验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisInspectionReport hisInspectionReport)
    {
        List<HisInspectionReport> list = hisInspectionReportService.selectHisInspectionReportList(hisInspectionReport);
        ExcelUtil<HisInspectionReport> util = new ExcelUtil<HisInspectionReport>(HisInspectionReport.class);
        return util.exportExcel(list, "inspectionReport");
    }

    /**
     * 新增检查检验
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检查检验
     */
    @RequiresPermissions("his:inspectionReport:add")
    @Log(title = "检查检验", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisInspectionReport hisInspectionReport)
    {
        return toAjax(hisInspectionReportService.insertHisInspectionReport(hisInspectionReport));
    }

    /**
     * 修改检查检验
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisInspectionReport hisInspectionReport = hisInspectionReportService.selectHisInspectionReportById(id);
        mmap.put("hisInspectionReport", hisInspectionReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存检查检验
     */
    @RequiresPermissions("his:inspectionReport:edit")
    @Log(title = "检查检验", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisInspectionReport hisInspectionReport)
    {
        return toAjax(hisInspectionReportService.updateHisInspectionReport(hisInspectionReport));
    }

    /**
     * 删除检查检验
     */
    @RequiresPermissions("his:inspectionReport:remove")
    @Log(title = "检查检验", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisInspectionReportService.deleteHisInspectionReportByIds(ids));
    }

    @RequiresPermissions("his:inspectionReport:detail")
    @GetMapping("/detail")
    public String expensesBillDetail(@RequestParam(value = "id") Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            HisInspectionReport report = hisInspectionReportService.selectHisInspectionReportById(id);
            mmap.put("report", report);
        }
        return prefix + "/inspectionReportItem";
    }

    @RequiresPermissions("his:inspectionReport:list")
    @PostMapping("/detail/list")
    @ResponseBody
    public TableDataInfo list(HisInspectionReportItem item)
    {
        startPage();
        List<HisInspectionReportItem> list = hisInspectionReportService.selectHisInspectionReportItemList(item);
        return getDataTable(list);
    }
}
