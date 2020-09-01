package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.service.IHisOutpatientExpensesBillService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 待缴费单Controller
 * 
 * @author bend
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/his/expensesBill")
public class HisOutpatientExpensesBillController extends BaseController
{
    private String prefix = "his/expensesBill";

    @Autowired
    private IHisOutpatientExpensesBillService hisOutpatientExpensesBillService;

    @RequiresPermissions("his:expensesBill:view")
    @GetMapping()
    public String expensesBill()
    {
        return prefix + "/expensesBill";
    }

    /**
     * 查询待缴费单列表
     */
    @RequiresPermissions("his:expensesBill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisOutpatientExpensesBill hisOutpatientExpensesBill)
    {
        startPage();
        List<HisOutpatientExpensesBill> list = hisOutpatientExpensesBillService.selectHisOutpatientExpensesBillList(hisOutpatientExpensesBill);
        return getDataTable(list);
    }

    /**
     * 导出待缴费单列表
     */
    @RequiresPermissions("his:expensesBill:export")
    @Log(title = "待缴费单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisOutpatientExpensesBill hisOutpatientExpensesBill)
    {
        List<HisOutpatientExpensesBill> list = hisOutpatientExpensesBillService.selectHisOutpatientExpensesBillList(hisOutpatientExpensesBill);
        ExcelUtil<HisOutpatientExpensesBill> util = new ExcelUtil<HisOutpatientExpensesBill>(HisOutpatientExpensesBill.class);
        return util.exportExcel(list, "expensesBill");
    }

    /**
     * 新增待缴费单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存待缴费单
     */
    @RequiresPermissions("his:expensesBill:add")
    @Log(title = "待缴费单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisOutpatientExpensesBill hisOutpatientExpensesBill)
    {
        return toAjax(hisOutpatientExpensesBillService.insertHisOutpatientExpensesBill(hisOutpatientExpensesBill));
    }

    /**
     * 修改待缴费单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisOutpatientExpensesBill hisOutpatientExpensesBill = hisOutpatientExpensesBillService.selectHisOutpatientExpensesBillById(id);
        mmap.put("hisOutpatientExpensesBill", hisOutpatientExpensesBill);
        return prefix + "/edit";
    }

    /**
     * 修改保存待缴费单
     */
    @RequiresPermissions("his:expensesBill:edit")
    @Log(title = "待缴费单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisOutpatientExpensesBill hisOutpatientExpensesBill)
    {
        return toAjax(hisOutpatientExpensesBillService.updateHisOutpatientExpensesBill(hisOutpatientExpensesBill));
    }

    /**
     * 删除待缴费单
     */
    @RequiresPermissions("his:expensesBill:remove")
    @Log(title = "待缴费单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisOutpatientExpensesBillService.deleteHisOutpatientExpensesBillByIds(ids));
    }

    @RequiresPermissions("his:expensesBill:detail")
    @GetMapping("/detail")
    public String expensesBillDetail(@RequestParam(value = "billId") Long billId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(billId))
        {
            HisOutpatientExpensesBill bill = hisOutpatientExpensesBillService.selectHisOutpatientExpensesBillById(billId);
            mmap.put("bill", bill);
        }
        return prefix + "/expensesBillDetail";
    }

    @RequiresPermissions("his:expensesBill:list")
    @PostMapping("/detail/list")
    @ResponseBody
    public TableDataInfo list(HisOutpatientExpensesBillDetail detail)
    {
        startPage();
        List<HisOutpatientExpensesBillDetail> list = hisOutpatientExpensesBillService.selectHisOutpatientExpensesBillDetailList(detail);
        return getDataTable(list);
    }
}
