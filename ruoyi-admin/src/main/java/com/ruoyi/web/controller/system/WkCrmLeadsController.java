package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.WkCrmLeads;
import com.ruoyi.system.service.IWkCrmLeadsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线索Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/leads")
public class WkCrmLeadsController extends BaseController
{
    private String prefix = "system/leads";

    @Autowired
    private IWkCrmLeadsService wkCrmLeadsService;

    @RequiresPermissions("system:leads:view")
    @GetMapping()
    public String leads()
    {
        return prefix + "/leads";
    }

    /**
     * 查询线索列表
     */
    @RequiresPermissions("system:leads:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmLeads wkCrmLeads)
    {
        startPage();
        List<WkCrmLeads> list = wkCrmLeadsService.selectWkCrmLeadsList(wkCrmLeads);
        return getDataTable(list);
    }

    /**
     * 导出线索列表
     */
    @RequiresPermissions("system:leads:export")
    @Log(title = "线索", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmLeads wkCrmLeads)
    {
        List<WkCrmLeads> list = wkCrmLeadsService.selectWkCrmLeadsList(wkCrmLeads);
        ExcelUtil<WkCrmLeads> util = new ExcelUtil<WkCrmLeads>(WkCrmLeads.class);
        return util.exportExcel(list, "leads");
    }

    /**
     * 新增线索
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存线索
     */
    @RequiresPermissions("system:leads:add")
    @Log(title = "线索", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmLeads wkCrmLeads)
    {
        return toAjax(wkCrmLeadsService.insertWkCrmLeads(wkCrmLeads));
    }

    /**
     * 修改线索
     */
    @GetMapping("/edit/{leadsId}")
    public String edit(@PathVariable("leadsId") Long leadsId, ModelMap mmap)
    {
        WkCrmLeads wkCrmLeads = wkCrmLeadsService.selectWkCrmLeadsById(leadsId);
        mmap.put("wkCrmLeads", wkCrmLeads);
        return prefix + "/edit";
    }

    /**
     * 修改保存线索
     */
    @RequiresPermissions("system:leads:edit")
    @Log(title = "线索", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmLeads wkCrmLeads)
    {
        return toAjax(wkCrmLeadsService.updateWkCrmLeads(wkCrmLeads));
    }

    /**
     * 删除线索
     */
    @RequiresPermissions("system:leads:remove")
    @Log(title = "线索", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmLeadsService.deleteWkCrmLeadsByIds(ids));
    }
}
