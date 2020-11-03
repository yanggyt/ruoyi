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
import com.ruoyi.front.domain.ContractTemplate;
import com.ruoyi.front.service.IContractTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同模板Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/contractTemplate")
public class ContractTemplateController extends BaseController
{
    private String prefix = "front/contract_template";

    @Autowired
    private IContractTemplateService contractTemplateService;

    @RequiresPermissions("front:contract_template:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/template";
    }

    /**
     * 查询合同模板列表
     */
    @RequiresPermissions("front:contractTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ContractTemplate contractTemplate)
    {
        startPage();
        List<ContractTemplate> list = contractTemplateService.selectContractTemplateList(contractTemplate);
        return getDataTable(list);
    }

    /**
     * 导出合同模板列表
     */
    @RequiresPermissions("front:contractTemplate:export")
    @Log(title = "合同模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ContractTemplate contractTemplate)
    {
        List<ContractTemplate> list = contractTemplateService.selectContractTemplateList(contractTemplate);
        ExcelUtil<ContractTemplate> util = new ExcelUtil<ContractTemplate>(ContractTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 新增合同模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同模板
     */
    @RequiresPermissions("front:contractTemplate:add")
    @Log(title = "合同模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ContractTemplate contractTemplate)
    {
        return toAjax(contractTemplateService.insertContractTemplate(contractTemplate));
    }

    /**
     * 修改合同模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ContractTemplate contractTemplate = contractTemplateService.selectContractTemplateById(id);
        mmap.put("contractTemplate", contractTemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同模板
     */
    @RequiresPermissions("front:contract_template:edit")
    @Log(title = "合同模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ContractTemplate contractTemplate)
    {
        return toAjax(contractTemplateService.updateContractTemplate(contractTemplate));
    }

    /**
     * 删除合同模板
     */
    @RequiresPermissions("front:contractTemplate:remove")
    @Log(title = "合同模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(contractTemplateService.deleteContractTemplateByIds(ids));
    }
}
