package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.RelevTable;
import com.ruoyi.system.service.IRelevTableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关联实体维护Controller
 * 
 * @author dalin
 * @date 2020-12-17
 */
@Controller
@RequestMapping("/system/relevtable")
public class RelevTableController extends BaseController
{
    private String prefix = "system/relevtable";

    @Autowired
    private IRelevTableService relevTableService;

    @RequiresPermissions("system:relevtable:view")
    @GetMapping()
    public String relevtable()
    {
        return prefix + "/relevtable";
    }

    /**
     * 查询关联实体维护列表
     */
    @RequiresPermissions("system:relevtable:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RelevTable relevTable)
    {
        startPage();
        List<RelevTable> list = relevTableService.selectRelevTableList(relevTable);
        return getDataTable(list);
    }

    /**
     * 导出关联实体维护列表
     */
    @RequiresPermissions("system:relevtable:export")
    @Log(title = "关联实体维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RelevTable relevTable)
    {
        List<RelevTable> list = relevTableService.selectRelevTableList(relevTable);
        ExcelUtil<RelevTable> util = new ExcelUtil<RelevTable>(RelevTable.class);
        return util.exportExcel(list, "relevtable");
    }

    /**
     * 新增关联实体维护
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存关联实体维护
     */
    @RequiresPermissions("system:relevtable:add")
    @Log(title = "关联实体维护", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RelevTable relevTable)
    {
        return toAjax(relevTableService.insertRelevTable(relevTable));
    }

    /**
     * 修改关联实体维护
     */
    @GetMapping("/edit/{relevId}")
    public String edit(@PathVariable("relevId") Long relevId, ModelMap mmap)
    {
        RelevTable relevTable = relevTableService.selectRelevTableById(relevId);
        mmap.put("relevTable", relevTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存关联实体维护
     */
    @RequiresPermissions("system:relevtable:edit")
    @Log(title = "关联实体维护", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RelevTable relevTable)
    {
        return toAjax(relevTableService.updateRelevTable(relevTable));
    }

    /**
     * 删除关联实体维护
     */
    @RequiresPermissions("system:relevtable:remove")
    @Log(title = "关联实体维护", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(relevTableService.deleteRelevTableByIds(ids));
    }
}
