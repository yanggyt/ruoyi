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
import com.ruoyi.his.domain.HisRegistrationTemplate;
import com.ruoyi.his.service.IHisRegistrationTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 挂号模板Controller
 * 
 * @author bend
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/his/registrationTemplate")
public class HisRegistrationTemplateController extends BaseController
{
    private String prefix = "his/registrationTemplate";

    @Autowired
    private IHisRegistrationTemplateService hisRegistrationTemplateService;

    @RequiresPermissions("his:registrationTemplate:view")
    @GetMapping()
    public String registrationTemplate()
    {
        return prefix + "/registrationTemplate";
    }

    /**
     * 查询挂号模板列表
     */
    @RequiresPermissions("his:registrationTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisRegistrationTemplate hisRegistrationTemplate)
    {
        startPage();
        List<HisRegistrationTemplate> list = hisRegistrationTemplateService.selectHisRegistrationTemplateList(hisRegistrationTemplate);
        return getDataTable(list);
    }

    /**
     * 导出挂号模板列表
     */
    @RequiresPermissions("his:registrationTemplate:export")
    @Log(title = "挂号模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisRegistrationTemplate hisRegistrationTemplate)
    {
        List<HisRegistrationTemplate> list = hisRegistrationTemplateService.selectHisRegistrationTemplateList(hisRegistrationTemplate);
        ExcelUtil<HisRegistrationTemplate> util = new ExcelUtil<HisRegistrationTemplate>(HisRegistrationTemplate.class);
        return util.exportExcel(list, "registrationTemplate");
    }

    /**
     * 新增挂号模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存挂号模板
     */
    @RequiresPermissions("his:registrationTemplate:add")
    @Log(title = "挂号模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return toAjax(hisRegistrationTemplateService.insertHisRegistrationTemplate(hisRegistrationTemplate));
    }

    /**
     * 修改挂号模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisRegistrationTemplate hisRegistrationTemplate = hisRegistrationTemplateService.selectHisRegistrationTemplateById(id);
        mmap.put("hisRegistrationTemplate", hisRegistrationTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存挂号模板
     */
    @RequiresPermissions("his:registrationTemplate:edit")
    @Log(title = "挂号模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return toAjax(hisRegistrationTemplateService.updateHisRegistrationTemplate(hisRegistrationTemplate));
    }

    /**
     * 删除挂号模板
     */
    @RequiresPermissions("his:registrationTemplate:remove")
    @Log(title = "挂号模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisRegistrationTemplateService.deleteHisRegistrationTemplateByIds(ids));
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("his:registrationTemplate:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return toAjax(hisRegistrationTemplateService.changeStatus(hisRegistrationTemplate));
    }
}
