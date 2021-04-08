package com.sinosoft.activity.controller;

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
import com.sinosoft.activity.domain.ActPageConfigGuide;
import com.sinosoft.activity.service.IActPageConfigGuideService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动展示内容配置Controller
 * 
 * @author dy
 * @date 2021-04-08
 */
@Controller
@RequestMapping("/system/guide")
public class ActPageConfigGuideController extends BaseController
{
    private String prefix = "system/guide";

    @Autowired
    private IActPageConfigGuideService actPageConfigGuideService;

    @RequiresPermissions("system:guide:view")
    @GetMapping()
    public String guide()
    {
        return prefix + "/guide";
    }

    /**
     * 查询活动展示内容配置列表
     */
    @RequiresPermissions("system:guide:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActPageConfigGuide actPageConfigGuide)
    {
        startPage();
        List<ActPageConfigGuide> list = actPageConfigGuideService.selectActPageConfigGuideList(actPageConfigGuide);
        return getDataTable(list);
    }

    /**
     * 导出活动展示内容配置列表
     */
    @RequiresPermissions("system:guide:export")
    @Log(title = "活动展示内容配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ActPageConfigGuide actPageConfigGuide)
    {
        List<ActPageConfigGuide> list = actPageConfigGuideService.selectActPageConfigGuideList(actPageConfigGuide);
        ExcelUtil<ActPageConfigGuide> util = new ExcelUtil<ActPageConfigGuide>(ActPageConfigGuide.class);
        return util.exportExcel(list, "guide");
    }

    /**
     * 新增活动展示内容配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动展示内容配置
     */
    @RequiresPermissions("system:guide:add")
    @Log(title = "活动展示内容配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ActPageConfigGuide actPageConfigGuide)
    {
        return toAjax(actPageConfigGuideService.insertActPageConfigGuide(actPageConfigGuide));
    }

    /**
     * 修改活动展示内容配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        ActPageConfigGuide actPageConfigGuide = actPageConfigGuideService.selectActPageConfigGuideById(id);
        mmap.put("actPageConfigGuide", actPageConfigGuide);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动展示内容配置
     */
    @RequiresPermissions("system:guide:edit")
    @Log(title = "活动展示内容配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ActPageConfigGuide actPageConfigGuide)
    {
        return toAjax(actPageConfigGuideService.updateActPageConfigGuide(actPageConfigGuide));
    }

    /**
     * 删除活动展示内容配置
     */
    @RequiresPermissions("system:guide:remove")
    @Log(title = "活动展示内容配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(actPageConfigGuideService.deleteActPageConfigGuideByIds(ids));
    }
}
