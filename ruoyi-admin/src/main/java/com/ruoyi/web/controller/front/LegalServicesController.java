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
import com.ruoyi.front.domain.LegalServices;
import com.ruoyi.front.service.ILegalServicesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 法律服务Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/services")
public class LegalServicesController extends BaseController
{
    private String prefix = "front/services";

    @Autowired
    private ILegalServicesService legalServicesService;

    @RequiresPermissions("front:services:view")
    @GetMapping()
    public String services()
    {
        return prefix + "/services";
    }

    /**
     * 查询法律服务列表
     */
    @RequiresPermissions("front:services:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LegalServices legalServices)
    {
        startPage();
        List<LegalServices> list = legalServicesService.selectLegalServicesList(legalServices);
        return getDataTable(list);
    }

    /**
     * 导出法律服务列表
     */
    @RequiresPermissions("front:services:export")
    @Log(title = "法律服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LegalServices legalServices)
    {
        List<LegalServices> list = legalServicesService.selectLegalServicesList(legalServices);
        ExcelUtil<LegalServices> util = new ExcelUtil<LegalServices>(LegalServices.class);
        return util.exportExcel(list, "services");
    }

    /**
     * 新增法律服务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存法律服务
     */
    @RequiresPermissions("front:services:add")
    @Log(title = "法律服务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LegalServices legalServices)
    {
        return toAjax(legalServicesService.insertLegalServices(legalServices));
    }

    /**
     * 修改法律服务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LegalServices legalServices = legalServicesService.selectLegalServicesById(id);
        mmap.put("legalServices", legalServices);
        return prefix + "/edit";
    }

    /**
     * 修改保存法律服务
     */
    @RequiresPermissions("front:services:edit")
    @Log(title = "法律服务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LegalServices legalServices)
    {
        return toAjax(legalServicesService.updateLegalServices(legalServices));
    }

    /**
     * 删除法律服务
     */
    @RequiresPermissions("front:services:remove")
    @Log(title = "法律服务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(legalServicesService.deleteLegalServicesByIds(ids));
    }
}
