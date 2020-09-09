package com.ruoyi.his.controller;

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
import com.ruoyi.his.domain.HisInspectionApply;
import com.ruoyi.his.service.IHisInspectionApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 申请单Controller
 * 
 * @author bend
 * @date 2020-07-10
 */
@Controller
@RequestMapping("/his/inspectionApply")
public class HisInspectionApplyController extends BaseController
{
    private String prefix = "his/inspectionApply";

    @Autowired
    private IHisInspectionApplyService hisInspectionApplyService;

    @RequiresPermissions("his:inspectionApply:view")
    @GetMapping()
    public String inspectionApply()
    {
        return prefix + "/inspectionApply";
    }

    /**
     * 查询申请单列表
     */
    @RequiresPermissions("his:inspectionApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisInspectionApply hisInspectionApply)
    {
        startPage();
        List<HisInspectionApply> list = hisInspectionApplyService.selectHisInspectionApplyList(hisInspectionApply);
        return getDataTable(list);
    }

    /**
     * 导出申请单列表
     */
    @RequiresPermissions("his:inspectionApply:export")
    @Log(title = "申请单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisInspectionApply hisInspectionApply)
    {
        List<HisInspectionApply> list = hisInspectionApplyService.selectHisInspectionApplyList(hisInspectionApply);
        ExcelUtil<HisInspectionApply> util = new ExcelUtil<HisInspectionApply>(HisInspectionApply.class);
        return util.exportExcel(list, "inspectionApply");
    }

    /**
     * 新增申请单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存申请单
     */
    @RequiresPermissions("his:inspectionApply:add")
    @Log(title = "申请单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisInspectionApply hisInspectionApply)
    {
        return toAjax(hisInspectionApplyService.insertHisInspectionApply(hisInspectionApply));
    }

    /**
     * 修改申请单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisInspectionApply hisInspectionApply = hisInspectionApplyService.selectHisInspectionApplyById(id);
        mmap.put("hisInspectionApply", hisInspectionApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存申请单
     */
    @RequiresPermissions("his:inspectionApply:edit")
    @Log(title = "申请单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisInspectionApply hisInspectionApply)
    {
        return toAjax(hisInspectionApplyService.updateHisInspectionApply(hisInspectionApply));
    }

    /**
     * 删除申请单
     */
    @RequiresPermissions("his:inspectionApply:remove")
    @Log(title = "申请单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisInspectionApplyService.deleteHisInspectionApplyByIds(ids));
    }
}
