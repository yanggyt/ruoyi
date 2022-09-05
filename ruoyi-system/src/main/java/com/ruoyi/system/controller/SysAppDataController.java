package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Classs;
import com.ruoyi.system.domain.SysAppData;
import com.ruoyi.system.service.IClasssService;
import com.ruoyi.system.service.impl.ISysAppDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 编码申请数据Controller
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
@Controller
@RequestMapping("/system/data")
public class SysAppDataController extends BaseController
{
    private String prefix = "system/data";

    @Autowired
    private ISysAppDataService sysAppDataService;
    @Autowired
    private IClasssService classsService;

    @RequiresPermissions("system:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/data";
    }

    /**
     * 查询编码申请数据列表
     */
    @RequiresPermissions("system:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysAppData sysAppData)
    {
        startPage();
        List<SysAppData> list = sysAppDataService.selectSysAppDataList(sysAppData);
        return getDataTable(list);
    }

    /**
     * 导出编码申请数据列表
     */
    @RequiresPermissions("system:data:export")
    @Log(title = "编码申请数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysAppData sysAppData)
    {
        List<SysAppData> list = sysAppDataService.selectSysAppDataList(sysAppData);
        ExcelUtil<SysAppData> util = new ExcelUtil<SysAppData>(SysAppData.class);
        return util.exportExcel(list, "编码申请数据数据");
    }

    /**
     * 新增编码申请数据
     */
    @GetMapping("/add")
    public String add( ModelMap mmap)
    {
        Classs classs=new Classs();
        classs.setApprovalStatusNo(Classs.APPROVAL_APPROVED);
        classs.setCatEntityTypeNo(3L);
        List<Classs> classList=classsService.selectClasssList( classs);
        mmap.put("classList", classList);
        return prefix + "/add";
    }
    /**
     * 查询ERM中已经发布的分类
     */
    @PostMapping("/getClasss")
    @ResponseBody
    public AjaxResult getClasss()
    {
        Classs classs=new Classs();
        classs.setApprovalStatusNo(Classs.APPROVAL_APPROVED);
        List<Classs> classList=classsService.selectClasssList( classs);
        return AjaxResult.success(classList);
    }

    /**
     * 新增保存编码申请数据
     */
    @RequiresPermissions("system:data:add")
    @Log(title = "编码申请数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAppData sysAppData)
    {
        return toAjax(sysAppDataService.insertSysAppData(sysAppData));
    }

    /**
     * 修改编码申请数据
     */
    @GetMapping("/edit/{appDataId}")
    public String edit(@PathVariable("appDataId") Long appDataId, ModelMap mmap)
    {
        SysAppData sysAppData = sysAppDataService.selectSysAppDataByAppDataId(appDataId);
        mmap.put("sysAppData", sysAppData);
        return prefix + "/edit";
    }

    /**
     * 修改保存编码申请数据
     */
    @RequiresPermissions("system:data:edit")
    @Log(title = "编码申请数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysAppData sysAppData)
    {
        return toAjax(sysAppDataService.updateSysAppData(sysAppData));
    }

    /**
     * 删除编码申请数据
     */
    @RequiresPermissions("system:data:remove")
    @Log(title = "编码申请数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAppDataService.deleteSysAppDataByAppDataIds(ids));
    }
    /**
     * 获取已审批的材料分类
     */
    @Log(title = "获取已审批的材料分类")
    @PostMapping( "/selectApprovalClasss")
    @ResponseBody
    public AjaxResult selectApprovalClasss()
    {
        AjaxResult ajax = new AjaxResult();
        ajax.put("data", classsService.selectApprovalClasss());
        return ajax;
    }
}
