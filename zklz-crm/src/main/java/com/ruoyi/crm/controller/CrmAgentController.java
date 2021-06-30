package com.ruoyi.crm.controller;

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
import com.ruoyi.crm.domain.CrmAgent;
import com.ruoyi.crm.service.ICrmAgentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代理商管理Controller
 * 
 * @author wendukeji.cn
 * @date 2021-06-30
 */
@Controller
@RequestMapping("/crm/agent")
public class CrmAgentController extends BaseController
{
    private String prefix = "crm/agent";

    @Autowired
    private ICrmAgentService crmAgentService;

    @RequiresPermissions("crm:agent:view")
    @GetMapping()
    public String agent()
    {
        return prefix + "/agent";
    }

    /**
     * 查询代理商管理列表
     */
    @RequiresPermissions("crm:agent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmAgent crmAgent)
    {
        startPage();
        List<CrmAgent> list = crmAgentService.selectCrmAgentList(crmAgent);
        return getDataTable(list);
    }

    /**
     * 导出代理商管理列表
     */
    @RequiresPermissions("crm:agent:export")
    @Log(title = "代理商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmAgent crmAgent)
    {
        List<CrmAgent> list = crmAgentService.selectCrmAgentList(crmAgent);
        ExcelUtil<CrmAgent> util = new ExcelUtil<CrmAgent>(CrmAgent.class);
        return util.exportExcel(list, "代理商管理数据");
    }

    /**
     * 新增代理商管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代理商管理
     */
    @RequiresPermissions("crm:agent:add")
    @Log(title = "代理商管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmAgent crmAgent)
    {
        return toAjax(crmAgentService.insertCrmAgent(crmAgent));
    }

    /**
     * 修改代理商管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CrmAgent crmAgent = crmAgentService.selectCrmAgentById(id);
        mmap.put("crmAgent", crmAgent);
        return prefix + "/edit";
    }

    /**
     * 修改保存代理商管理
     */
    @RequiresPermissions("crm:agent:edit")
    @Log(title = "代理商管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmAgent crmAgent)
    {
        return toAjax(crmAgentService.updateCrmAgent(crmAgent));
    }

    /**
     * 删除代理商管理
     */
    @RequiresPermissions("crm:agent:remove")
    @Log(title = "代理商管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmAgentService.deleteCrmAgentByIds(ids));
    }
}
