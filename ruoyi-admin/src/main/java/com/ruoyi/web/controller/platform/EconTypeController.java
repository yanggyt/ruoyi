package com.ruoyi.web.controller.platform;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.province.platform.domain.EconType;
import com.ruoyi.province.platform.service.IEconTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 经济类型Controller
 * 
 * @author dalin
 * @date 2020-12-08
 */
@Controller
@RequestMapping("/platform/econtype")
public class EconTypeController extends BaseController
{
    private String prefix = "platform/econtype";

    @Autowired
    private IEconTypeService econTypeService;

    @RequiresPermissions("platform:econtype:view")
    @GetMapping()
    public String econtype()
    {
        return prefix + "/econtype";
    }

    /**
     * 查询经济类型列表
     */
    @RequiresPermissions("platform:econtype:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EconType econType)
    {
        startPage();
        List<EconType> list = econTypeService.selectEconTypeList(econType);
        return getDataTable(list);
    }

    /**
     * 导出经济类型列表
     */
    @RequiresPermissions("platform:econtype:export")
    @Log(title = "经济类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EconType econType)
    {
        List<EconType> list = econTypeService.selectEconTypeList(econType);
        ExcelUtil<EconType> util = new ExcelUtil<EconType>(EconType.class);
        return util.exportExcel(list, "econtype");
    }

    /**
     * 新增经济类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存经济类型
     */
    @RequiresPermissions("platform:econtype:add")
    @Log(title = "经济类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EconType econType)
    {
        return toAjax(econTypeService.insertEconType(econType));
    }

    /**
     * 修改经济类型
     */
    @GetMapping("/edit/{econId}")
    public String edit(@PathVariable("econId") Integer econId, ModelMap mmap)
    {
        EconType econType = econTypeService.selectEconTypeById(econId);
        mmap.put("econType", econType);
        return prefix + "/edit";
    }

    /**
     * 修改保存经济类型
     */
    @RequiresPermissions("platform:econtype:edit")
    @Log(title = "经济类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EconType econType)
    {
        return toAjax(econTypeService.updateEconType(econType));
    }

    /**
     * 删除经济类型
     */
    @RequiresPermissions("platform:econtype:remove")
    @Log(title = "经济类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(econTypeService.deleteEconTypeByIds(ids));
    }
}
