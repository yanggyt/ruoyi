package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Attrib;
import com.ruoyi.system.service.IAttribService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 属性Controller
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Controller
@RequestMapping("/system/attrib")
public class AttribController extends BaseController
{
    private String prefix = "system/attrib";

    @Autowired
    private IAttribService attribService;

    @RequiresPermissions("system:attrib:view")
    @GetMapping()
    public String attrib()
    {
        return prefix + "/attrib";
    }

    /**
     * 查询属性列表
     */
    @RequiresPermissions("system:attrib:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Attrib attrib)
    {
        startPage();
        List<Attrib> list = attribService.selectAttribList(attrib);
        return getDataTable(list);
    }

    /**
     * 导出属性列表
     */
    @RequiresPermissions("system:attrib:export")
    @Log(title = "属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Attrib attrib)
    {
        List<Attrib> list = attribService.selectAttribList(attrib);
        ExcelUtil<Attrib> util = new ExcelUtil<Attrib>(Attrib.class);
        return util.exportExcel(list, "属性数据");
    }

    /**
     * 新增属性
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存属性
     */
    @RequiresPermissions("system:attrib:add")
    @Log(title = "属性", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Attrib attrib)
    {
        return toAjax(attribService.insertAttrib(attrib));
    }

    /**
     * 修改属性
     */
    @GetMapping("/edit/{attribNo}")
    public String edit(@PathVariable("attribNo") Long attribNo, ModelMap mmap)
    {
        Attrib attrib = attribService.selectAttribByAttribNo(attribNo);
        mmap.put("attrib", attrib);
        return prefix + "/edit";
    }

    /**
     * 修改保存属性
     */
    @RequiresPermissions("system:attrib:edit")
    @Log(title = "属性", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Attrib attrib)
    {
        return toAjax(attribService.updateAttrib(attrib));
    }

    /**
     * 删除属性1
     */
    @RequiresPermissions("system:attrib:remove")
    @Log(title = "属性", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(attribService.deleteAttribByAttribNos(ids));
    }
    /**
     * 根据材料分类获取属性
     */
    @Log(title = "根据材料分类获取属性")
    @PostMapping( "/selectAttribListByClasss/{classsId}")
    @ResponseBody
    public AjaxResult selectAttribListByClasss(@PathVariable("classsId") Long classsId)
    {
        AjaxResult ajax = new AjaxResult();
        ajax.put("data", attribService.selectAttribListByClasss(classsId));
        return ajax;
    }
}
