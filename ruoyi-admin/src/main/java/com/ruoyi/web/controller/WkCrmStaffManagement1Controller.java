package com.ruoyi.web.controller;

import java.util.List;

import com.ruoyi.system.service.IWkCrmCandidateService;
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
import com.ruoyi.system.domain.WkCrmStaffManagement1;
import com.ruoyi.system.service.IWkCrmStaffManagement1Service;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工管理Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/management1")
public class WkCrmStaffManagement1Controller extends BaseController
{
    private String prefix = "system/management1";

    @Autowired
    private IWkCrmStaffManagement1Service wkCrmStaffManagement1Service;

    @RequiresPermissions("system:management1:view")
    @GetMapping()
    public String management1()
    {
        return prefix + "/management1";
    }

    /**
     * 查询员工管理列表
     */
    @RequiresPermissions("system:management1:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        startPage();
        List<WkCrmStaffManagement1> list = wkCrmStaffManagement1Service.selectWkCrmStaffManagement1List(wkCrmStaffManagement1);
        return getDataTable(list);
    }

    /**
     * 导出员工管理列表
     */
    @RequiresPermissions("system:management1:export")
    @Log(title = "员工管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        List<WkCrmStaffManagement1> list = wkCrmStaffManagement1Service.selectWkCrmStaffManagement1List(wkCrmStaffManagement1);
        ExcelUtil<WkCrmStaffManagement1> util = new ExcelUtil<WkCrmStaffManagement1>(WkCrmStaffManagement1.class);
        return util.exportExcel(list, "management1");
    }

    /**
     * 新增员工管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存员工管理
     */
    @RequiresPermissions("system:management1:add")
    @Log(title = "员工管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        return toAjax(wkCrmStaffManagement1Service.insertWkCrmStaffManagement1(wkCrmStaffManagement1));
    }

    /**
     * 修改员工管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WkCrmStaffManagement1 wkCrmStaffManagement1 = wkCrmStaffManagement1Service.selectWkCrmStaffManagement1ById(id);
        mmap.put("wkCrmStaffManagement1", wkCrmStaffManagement1);
        return prefix + "/edit";
    }

    /**
     * 修改保存员工管理
     */
    @RequiresPermissions("system:management1:edit")
    @Log(title = "员工管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmStaffManagement1 wkCrmStaffManagement1)
    {
        return toAjax(wkCrmStaffManagement1Service.updateWkCrmStaffManagement1(wkCrmStaffManagement1));
    }

    /**
     * 删除员工管理
     */
    @RequiresPermissions("system:management1:remove")
    @Log(title = "员工管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        
        return toAjax(wkCrmStaffManagement1Service.deleteWkCrmStaffManagement1ByIds(ids));
    }
}
