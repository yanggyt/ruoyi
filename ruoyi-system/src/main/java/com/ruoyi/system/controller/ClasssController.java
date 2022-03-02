package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Classs;
import com.ruoyi.system.service.IClasssService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Table containing Class HierarchyController
 * 
 * @author zbj
 * @date 2021-08-31
 */
@Controller
@RequestMapping("/system/class")
public class ClasssController extends BaseController
{
    private String prefix = "system/class";

    @Autowired
    private IClasssService classsService;

    @RequiresPermissions("system:class:view")
    @GetMapping()
    public String classs()
    {
        return prefix + "/class";
    }

    /**
     * 查询Table containing Class Hierarchy树列表
     */
    @RequiresPermissions("system:class:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Classs classs)
    {
        startPage();
        List<Classs> list = classsService.selectClasssList(classs);
        return getDataTable(list);
    }

    /**
     * 导出Table containing Class Hierarchy列表
     */
    @RequiresPermissions("system:class:export")
    @Log(title = "Table containing Class Hierarchy", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Classs classs)
    {
        List<Classs> list = classsService.selectClasssList(classs);
        ExcelUtil<Classs> util = new ExcelUtil<Classs>(Classs.class);
        return util.exportExcel(list, "Table containing Class Hierarchy数据");
    }

    /**
     * 新增Table containing Class Hierarchy
     */
    @GetMapping(value = { "/add/{classNo}", "/add/" })
    public String add(@PathVariable(value = "classNo", required = false) Long classNo, ModelMap mmap)
    {
        if (StringUtils.isNotNull(classNo))
        {
            mmap.put("classs", classsService.selectClasssByClassNo(classNo));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存Table containing Class Hierarchy
     */
    @RequiresPermissions("system:class:add")
    @Log(title = "Table containing Class Hierarchy", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Classs classs)
    {
        return toAjax(classsService.insertClasss(classs));
    }

    /**
     * 修改Table containing Class Hierarchy
     */
    @GetMapping("/edit/{classNo}")
    public String edit(@PathVariable("classNo") Long classNo, ModelMap mmap)
    {
        Classs classs = classsService.selectClasssByClassNo(classNo);
        mmap.put("classs", classs);
        return prefix + "/edit";
    }

    /**
     * 修改保存Table containing Class Hierarchy
     */
    @RequiresPermissions("system:class:edit")
    @Log(title = "Table containing Class Hierarchy", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Classs classs)
    {
        return toAjax(classsService.updateClasss(classs));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:class:remove")
    @Log(title = "Table containing Class Hierarchy", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{classNo}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("classNo") Long classNo)
    {
        return toAjax(classsService.deleteClasssByClassNo(classNo));
    }

    public static void main(String[] args) {
        if(true){

        }
            System.out.println(1);

            System.out.println(2);
    }
    /**
     * 选择Table containing Class Hierarchy树
     */
    @GetMapping(value = { "/selectClassTree/{classNo}", "/selectClassTree/" })
    public String selectClassTree(@PathVariable(value = "classNo", required = false) Long classNo, ModelMap mmap)
    {
        if (StringUtils.isNotNull(classNo))
        {
            mmap.put("classs", classsService.selectClasssByClassNo(classNo));
        }
        return prefix + "/tree";
    }

    /**
     * 加载物料分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = classsService.selectClasssTree();
        return ztrees;
    }
}
