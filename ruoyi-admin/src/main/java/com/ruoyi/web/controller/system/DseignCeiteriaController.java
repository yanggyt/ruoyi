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
import com.ruoyi.system.domain.DseignCeiteria;
import com.ruoyi.system.service.IDseignCeiteriaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设计标准Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/system/ceiteria")
public class DseignCeiteriaController extends BaseController
{
    private String prefix = "system/ceiteria";

    @Autowired
    private IDseignCeiteriaService dseignCeiteriaService;

    @RequiresPermissions("system:ceiteria:view")
    @GetMapping()
    public String ceiteria()
    {
        return prefix + "/ceiteria";
    }

    /**
     * 查询设计标准列表
     */
    @RequiresPermissions("system:ceiteria:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DseignCeiteria dseignCeiteria)
    {
        startPage();
        List<DseignCeiteria> list = dseignCeiteriaService.selectDseignCeiteriaList(dseignCeiteria);
        return getDataTable(list);
    }

    /**
     * 导出设计标准列表
     */
    @RequiresPermissions("system:ceiteria:export")
    @Log(title = "设计标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DseignCeiteria dseignCeiteria)
    {
        List<DseignCeiteria> list = dseignCeiteriaService.selectDseignCeiteriaList(dseignCeiteria);
        ExcelUtil<DseignCeiteria> util = new ExcelUtil<DseignCeiteria>(DseignCeiteria.class);
        return util.exportExcel(list, "设计标准数据");
    }

    /**
     * 新增设计标准
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设计标准
     */
    @RequiresPermissions("system:ceiteria:add")
    @Log(title = "设计标准", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DseignCeiteria dseignCeiteria)
    {
        return toAjax(dseignCeiteriaService.insertDseignCeiteria(dseignCeiteria));
    }

    /**
     * 修改设计标准
     */
    @GetMapping("/edit/{designStandardNumber}")
    public String edit(@PathVariable("designStandardNumber") Long designStandardNumber, ModelMap mmap)
    {
        DseignCeiteria dseignCeiteria = dseignCeiteriaService.selectDseignCeiteriaById(designStandardNumber);
        mmap.put("dseignCeiteria", dseignCeiteria);
        return prefix + "/edit";
    }

    /**
     * 修改保存设计标准
     */
    @RequiresPermissions("system:ceiteria:edit")
    @Log(title = "设计标准", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DseignCeiteria dseignCeiteria)
    {
        return toAjax(dseignCeiteriaService.updateDseignCeiteria(dseignCeiteria));
    }

    /**
     * 删除设计标准
     */
    @RequiresPermissions("system:ceiteria:remove")
    @Log(title = "设计标准", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dseignCeiteriaService.deleteDseignCeiteriaByIds(ids));
    }
}
