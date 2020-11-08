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
import com.ruoyi.front.domain.ContractType;
import com.ruoyi.front.service.IContractTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同分类Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/contractType")
public class ContractTypeController extends BaseController
{
    private String prefix = "front/contract_type";

    @Autowired
    private IContractTypeService contractTypeService;

    @RequiresPermissions("front:contractType:view")
    @GetMapping()
    public String contract()
    {
        return prefix + "/type";
    }

    /**
     * 查询合同分类列表
     */
    @RequiresPermissions("front:contractType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ContractType contractType)
    {
        startPage();
        List<ContractType> list = contractTypeService.selectContractTypeList(contractType);
        return getDataTable(list);
    }

    /**
     * 导出合同分类列表
     */
    @RequiresPermissions("front:contractType:export")
    @Log(title = "合同分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ContractType contractType)
    {
        List<ContractType> list = contractTypeService.selectContractTypeList(contractType);
        ExcelUtil<ContractType> util = new ExcelUtil<ContractType>(ContractType.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 新增合同分类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同分类
     */
    @RequiresPermissions("front:contractType:add")
    @Log(title = "合同分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ContractType contractType)
    {
        return toAjax(contractTypeService.insertContractType(contractType));
    }

    /**
     * 修改合同分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ContractType contractType = contractTypeService.selectContractTypeById(id);
        mmap.put("contractType", contractType);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同分类
     */
    @RequiresPermissions("front:contractType:edit")
    @Log(title = "合同分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ContractType contractType)
    {
        return toAjax(contractTypeService.updateContractType(contractType));
    }

    /**
     * 删除合同分类
     */
    @RequiresPermissions("front:contractType:remove")
    @Log(title = "合同分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(contractTypeService.deleteContractTypeByIds(ids));
    }
}
