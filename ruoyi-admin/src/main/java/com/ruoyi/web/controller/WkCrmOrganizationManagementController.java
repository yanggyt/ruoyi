package com.ruoyi.web.controller;

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
import com.ruoyi.system.domain.WkCrmOrganizationManagement;
import com.ruoyi.system.service.IWkCrmOrganizationManagementService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 组织管理Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/management")
public class WkCrmOrganizationManagementController extends BaseController
{
    private String prefix = "system/management";

    @Autowired
    private IWkCrmOrganizationManagementService wkCrmOrganizationManagementService;

    @RequiresPermissions("system:management:view")
    @GetMapping()
    public String management()
    {
        return prefix + "/management";
    }

    /**
     * 查询组织管理列表
     */
    @RequiresPermissions("system:management:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        startPage();
        List<WkCrmOrganizationManagement> list = wkCrmOrganizationManagementService.selectWkCrmOrganizationManagementList(wkCrmOrganizationManagement);
        return getDataTable(list);
    }

    /**
     * 导出组织管理列表
     */
    @RequiresPermissions("system:management:export")
    @Log(title = "组织管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        List<WkCrmOrganizationManagement> list = wkCrmOrganizationManagementService.selectWkCrmOrganizationManagementList(wkCrmOrganizationManagement);
        ExcelUtil<WkCrmOrganizationManagement> util = new ExcelUtil<WkCrmOrganizationManagement>(WkCrmOrganizationManagement.class);
        return util.exportExcel(list, "management");
    }

    /**
     * 新增组织管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存组织管理
     */
    @RequiresPermissions("system:management:add")
    @Log(title = "组织管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        return toAjax(wkCrmOrganizationManagementService.insertWkCrmOrganizationManagement(wkCrmOrganizationManagement));
    }

    /**
     * 修改组织管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WkCrmOrganizationManagement wkCrmOrganizationManagement = wkCrmOrganizationManagementService.selectWkCrmOrganizationManagementById(id);
        mmap.put("wkCrmOrganizationManagement", wkCrmOrganizationManagement);
        return prefix + "/edit";
    }

    /**
     * 修改保存组织管理
     */
    @RequiresPermissions("system:management:edit")
    @Log(title = "组织管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmOrganizationManagement wkCrmOrganizationManagement)
    {
        return toAjax(wkCrmOrganizationManagementService.updateWkCrmOrganizationManagement(wkCrmOrganizationManagement));
    }

    /**
     * 删除组织管理
     */
    @RequiresPermissions("system:management:remove")
    @Log(title = "组织管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmOrganizationManagementService.deleteWkCrmOrganizationManagementByIds(ids));
    }
}
