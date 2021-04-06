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
import com.ruoyi.system.domain.WkCrmContract;
import com.ruoyi.system.service.IWkCrmContractService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/contract")
public class WkCrmContractController extends BaseController
{
    private String prefix = "system/contract";

    @Autowired
    private IWkCrmContractService wkCrmContractService;

    @RequiresPermissions("system:contract:view")
    @GetMapping()
    public String contract()
    {
        return prefix + "/contract";
    }

    /**
     * 查询合同列表
     */
    @RequiresPermissions("system:contract:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmContract wkCrmContract)
    {
        startPage();
        List<WkCrmContract> list = wkCrmContractService.selectWkCrmContractList(wkCrmContract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @RequiresPermissions("system:contract:export")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmContract wkCrmContract)
    {
        List<WkCrmContract> list = wkCrmContractService.selectWkCrmContractList(wkCrmContract);
        ExcelUtil<WkCrmContract> util = new ExcelUtil<WkCrmContract>(WkCrmContract.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 新增合同
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同
     */
    @RequiresPermissions("system:contract:add")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmContract wkCrmContract)
    {
        return toAjax(wkCrmContractService.insertWkCrmContract(wkCrmContract));
    }

    /**
     * 修改合同
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Integer contractId, ModelMap mmap)
    {
        WkCrmContract wkCrmContract = wkCrmContractService.selectWkCrmContractById(contractId);
        mmap.put("wkCrmContract", wkCrmContract);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同
     */
    @RequiresPermissions("system:contract:edit")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmContract wkCrmContract)
    {
        return toAjax(wkCrmContractService.updateWkCrmContract(wkCrmContract));
    }

    /**
     * 删除合同
     */
    @RequiresPermissions("system:contract:remove")
    @Log(title = "合同", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmContractService.deleteWkCrmContractByIds(ids));
    }
}
