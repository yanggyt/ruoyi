package com.ruoyi.web.controller.front;

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
import com.ruoyi.front.domain.ClasssicCases;
import com.ruoyi.front.service.IClasssicCasesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 典型案例Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/cases")
public class ClasssicCasesController extends BaseController
{
    private String prefix = "front/cases";

    @Autowired
    private IClasssicCasesService classsicCasesService;

    @RequiresPermissions("front:cases:view")
    @GetMapping()
    public String cases()
    {
        return prefix + "/cases";
    }

    /**
     * 查询典型案例列表
     */
    @RequiresPermissions("front:cases:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClasssicCases classsicCases)
    {
        startPage();
        List<ClasssicCases> list = classsicCasesService.selectClasssicCasesList(classsicCases);
        return getDataTable(list);
    }

    /**
     * 导出典型案例列表
     */
    @RequiresPermissions("front:cases:export")
    @Log(title = "典型案例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClasssicCases classsicCases)
    {
        List<ClasssicCases> list = classsicCasesService.selectClasssicCasesList(classsicCases);
        ExcelUtil<ClasssicCases> util = new ExcelUtil<ClasssicCases>(ClasssicCases.class);
        return util.exportExcel(list, "cases");
    }

    /**
     * 新增典型案例
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存典型案例
     */
    @RequiresPermissions("front:cases:add")
    @Log(title = "典型案例", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClasssicCases classsicCases)
    {
        return toAjax(classsicCasesService.insertClasssicCases(classsicCases));
    }

    /**
     * 修改典型案例
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ClasssicCases classsicCases = classsicCasesService.selectClasssicCasesById(id);
        mmap.put("classsicCases", classsicCases);
        return prefix + "/edit";
    }

    /**
     * 修改保存典型案例
     */
    @RequiresPermissions("front:cases:edit")
    @Log(title = "典型案例", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClasssicCases classsicCases)
    {
        return toAjax(classsicCasesService.updateClasssicCases(classsicCases));
    }

    /**
     * 删除典型案例
     */
    @RequiresPermissions("front:cases:remove")
    @Log(title = "典型案例", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classsicCasesService.deleteClasssicCasesByIds(ids));
    }
}
