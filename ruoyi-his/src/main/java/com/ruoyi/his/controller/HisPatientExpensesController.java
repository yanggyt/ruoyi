package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;
import com.ruoyi.his.domain.HisPatientExpensesDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisPatientExpenses;
import com.ruoyi.his.service.IHisPatientExpensesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用记录Controller
 * 
 * @author bend
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/his/patientExpenses")
public class HisPatientExpensesController extends BaseController
{
    private String prefix = "his/patientExpenses";

    @Autowired
    private IHisPatientExpensesService hisPatientExpensesService;

    @RequiresPermissions("his:patientExpenses:view")
    @GetMapping()
    public String patientExpenses()
    {
        return prefix + "/patientExpenses";
    }

    /**
     * 查询费用记录列表
     */
    @RequiresPermissions("his:patientExpenses:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisPatientExpenses hisPatientExpenses)
    {
        startPage();
        List<HisPatientExpenses> list = hisPatientExpensesService.selectHisPatientExpensesList(hisPatientExpenses);
        return getDataTable(list);
    }

    /**
     * 导出费用记录列表
     */
    @RequiresPermissions("his:patientExpenses:export")
    @Log(title = "费用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisPatientExpenses hisPatientExpenses)
    {
        List<HisPatientExpenses> list = hisPatientExpensesService.selectHisPatientExpensesList(hisPatientExpenses);
        ExcelUtil<HisPatientExpenses> util = new ExcelUtil<HisPatientExpenses>(HisPatientExpenses.class);
        return util.exportExcel(list, "patientExpenses");
    }

    /**
     * 新增费用记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存费用记录
     */
    @RequiresPermissions("his:patientExpenses:add")
    @Log(title = "费用记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisPatientExpenses hisPatientExpenses)
    {
        return toAjax(hisPatientExpensesService.insertHisPatientExpenses(hisPatientExpenses));
    }

    /**
     * 修改费用记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisPatientExpenses hisPatientExpenses = hisPatientExpensesService.selectHisPatientExpensesById(id);
        mmap.put("hisPatientExpenses", hisPatientExpenses);
        return prefix + "/edit";
    }

    /**
     * 修改保存费用记录
     */
    @RequiresPermissions("his:patientExpenses:edit")
    @Log(title = "费用记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisPatientExpenses hisPatientExpenses)
    {
        return toAjax(hisPatientExpensesService.updateHisPatientExpenses(hisPatientExpenses));
    }

    /**
     * 删除费用记录
     */
    @RequiresPermissions("his:patientExpenses:remove")
    @Log(title = "费用记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisPatientExpensesService.deleteHisPatientExpensesByIds(ids));
    }

    /**
     * 费用记录详情
     */
    @RequiresPermissions("his:patientExpenses:detail")
    @GetMapping("/detail")
    public String patientExpensesDetail(@RequestParam(value = "expensesId") Long expensesId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(expensesId))
        {
            HisPatientExpenses patientExpenses = hisPatientExpensesService.selectHisPatientExpensesById(expensesId);
            mmap.put("patientExpenses", patientExpenses);
        }
        return prefix + "/patientExpensesDetail";
    }

    @RequiresPermissions("his:patientExpenses:list")
    @PostMapping("/detail/list")
    @ResponseBody
    public TableDataInfo list(HisPatientExpensesDetail detail)
    {
        startPage();
        List<HisPatientExpensesDetail> list = hisPatientExpensesService.selectHisPatientExpensesDetailList(detail);
        return getDataTable(list);
    }
}
