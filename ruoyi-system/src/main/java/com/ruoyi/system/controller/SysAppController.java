package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysApp;
import com.ruoyi.system.service.impl.ISysAppDataService;
import com.ruoyi.system.service.impl.ISysAppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 编码申请Controller
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
@Controller
@RequestMapping("/system/app")
public class SysAppController extends BaseController
{
    private String prefix = "system/app";
    @Autowired
    private ISysAppDataService sysAppDataService;
    @Autowired
    private ISysAppService sysAppService;

    @RequiresPermissions("system:app:view")
    @GetMapping()
    public String app()
    {
        return prefix + "/app";
    }
    @RequiresPermissions("system:app:view")
    @GetMapping("/fuwenben")
    public String fuwenben()
    {
        return prefix + "/fuwenben";
    }

    /**
     * 查询编码申请列表
     */
    @RequiresPermissions("system:app:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysApp sysApp)
    {
        startPage();
        List<SysApp> list = sysAppService.selectSysAppList(sysApp);
        return getDataTable(list);
    }

    /**
     * 导出编码申请列表
     */
    @RequiresPermissions("system:app:export")
    @Log(title = "编码申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysApp sysApp)
    {
        List<SysApp> list = sysAppService.selectSysAppList(sysApp);
        ExcelUtil<SysApp> util = new ExcelUtil<SysApp>(SysApp.class);
        return util.exportExcel(list, "编码申请数据");
    }

    /**
     * 新增编码申请
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存编码申请
     */
    @RequiresPermissions("system:app:add")
    @Log(title = "编码申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysApp sysApp)
    {
        return toAjax(sysAppService.insertSysApp(sysApp));
    }

    /**
     * 查询申请单详细
     */
    @RequiresPermissions("system:app:list")
    @GetMapping("/detail/{appId}")
    public String detail(@PathVariable("appId") Long dictId, ModelMap mmap)
    {
        mmap.put("app", sysAppService.selectSysAppByAppId(dictId));
        mmap.put("appList", sysAppService.selectAppAll());
        return "system/data/data";
    }
    /**
     * 修改编码申请
     */
    @GetMapping("/edit/{appId}")
    public String edit(@PathVariable("appId") Long appId, ModelMap mmap)
    {
        SysApp sysApp = sysAppService.selectSysAppByAppId(appId);
        mmap.put("sysApp", sysApp);
        return prefix + "/edit";
    }

    /**
     * 修改保存编码申请
     */
    @RequiresPermissions("system:app:edit")
    @Log(title = "编码申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysApp sysApp)
    {
        return toAjax(sysAppService.updateSysApp(sysApp));
    }

    /**
     * 删除编码申请
     */
    @RequiresPermissions("system:app:remove")
    @Log(title = "编码申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAppService.deleteSysAppByAppIds(ids));
    }
}
