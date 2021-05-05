package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.EheologicalParameters;
import com.ruoyi.system.service.IEheologicalParametersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流变参数Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/system/parameters")
public class EheologicalParametersController extends BaseController
{
    private String prefix = "system/parameters";

    @Autowired
    private IEheologicalParametersService eheologicalParametersService;

    @RequiresPermissions("system:parameters:view")
    @GetMapping()
    public String parameters()
    {
        return prefix + "/parameters";
    }

    /**
     * 查询流变参数列表
     */
    @RequiresPermissions("system:parameters:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EheologicalParameters eheologicalParameters)
    {
        startPage();
        List<EheologicalParameters> list = eheologicalParametersService.selectEheologicalParametersList(eheologicalParameters);
        return getDataTable(list);
    }

    /**
     * 导出流变参数列表
     */
    @RequiresPermissions("system:parameters:export")
    @Log(title = "流变参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EheologicalParameters eheologicalParameters)
    {
        List<EheologicalParameters> list = eheologicalParametersService.selectEheologicalParametersList(eheologicalParameters);
        ExcelUtil<EheologicalParameters> util = new ExcelUtil<EheologicalParameters>(EheologicalParameters.class);
        return util.exportExcel(list, "流变参数数据");
    }

    /**
     * 新增流变参数
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流变参数
     */
    @RequiresPermissions("system:parameters:add")
    @Log(title = "流变参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EheologicalParameters eheologicalParameters)
    {
        return toAjax(eheologicalParametersService.insertEheologicalParameters(eheologicalParameters));
    }

    /**
     * 修改流变参数
     */
    @GetMapping("/edit/{rheologicalParameternumber}")
    public String edit(@PathVariable("rheologicalParameternumber") Long rheologicalParameternumber, ModelMap mmap)
    {
        EheologicalParameters eheologicalParameters = eheologicalParametersService.selectEheologicalParametersById(rheologicalParameternumber);
        mmap.put("eheologicalParameters", eheologicalParameters);
        return prefix + "/edit";
    }

    /**
     * 修改保存流变参数
     */
    @RequiresPermissions("system:parameters:edit")
    @Log(title = "流变参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EheologicalParameters eheologicalParameters)
    {
        return toAjax(eheologicalParametersService.updateEheologicalParameters(eheologicalParameters));
    }

    /**
     * 删除流变参数
     */
    @RequiresPermissions("system:parameters:remove")
    @Log(title = "流变参数", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eheologicalParametersService.deleteEheologicalParametersByIds(ids));
    }
}
