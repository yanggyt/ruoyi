package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AttribValue;
import com.ruoyi.system.service.IAttribValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Table containing ATTRIB_VALUEController
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Api(tags ={"属性值查询"} )
@Controller
@RequestMapping("/system/value")
public class AttribValueController extends BaseController
{
    private String prefix = "system/value";

    @Autowired
    private IAttribValueService attribValueService;

    @RequiresPermissions("system:value:view")
    @GetMapping()
    public String value()
    {
        return prefix + "/value";
    }

    /**
     * 查询Table containing ATTRIB_VALUE列表
     */
    @RequiresPermissions("system:value:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AttribValue attribValue)
    {
        startPage();
        List<AttribValue> list = attribValueService.selectAttribValueList(attribValue);
        return getDataTable(list);
    }

    /**
     * 导出Table containing ATTRIB_VALUE列表
     */
    @RequiresPermissions("system:value:export")
    @Log(title = "Table containing ATTRIB_VALUE", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AttribValue attribValue)
    {
        List<AttribValue> list = attribValueService.selectAttribValueList(attribValue);
        ExcelUtil<AttribValue> util = new ExcelUtil<AttribValue>(AttribValue.class);
        return util.exportExcel(list, "Table containing ATTRIB_VALUE数据");
    }

    /**
     * 新增Table containing ATTRIB_VALUE
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Table containing ATTRIB_VALUE
     */
    @RequiresPermissions("system:value:add")
    @Log(title = "Table containing ATTRIB_VALUE", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AttribValue attribValue)
    {
        return toAjax(attribValueService.insertAttribValue(attribValue));
    }

    /**
     * 修改Table containing ATTRIB_VALUE
     */
    @GetMapping("/edit/{attribValueNo}")
    public String edit(@PathVariable("attribValueNo") Long attribValueNo, ModelMap mmap)
    {
        AttribValue attribValue = attribValueService.selectAttribValueByAttribValueNo(attribValueNo);
        mmap.put("attribValue", attribValue);
        return prefix + "/edit";
    }

    /**
     * 修改保存Table containing ATTRIB_VALUE
     */
    @RequiresPermissions("system:value:edit")
    @Log(title = "Table containing ATTRIB_VALUE", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AttribValue attribValue)
    {
        return toAjax(attribValueService.updateAttribValue(attribValue));
    }

    /**
     * 删除Table containing ATTRIB_VALUE
     */
    @RequiresPermissions("system:value:remove")
    @Log(title = "Table containing ATTRIB_VALUE", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(attribValueService.deleteAttribValueByAttribValueNos(ids));
    }
    /**
     * 根据属性获取属性值
     */
    @Log(title = "根据属性获取属性值")
    @PostMapping( "/selectByAttrib/{attribId}")
    @ResponseBody
    public AjaxResult selectByAttrib(@PathVariable("attribId") Long attribId)
    {
        AjaxResult ajax = new AjaxResult();
        ajax.put("data", attribValueService.selectByAttrib(attribId));
        return ajax;
    }
    /**
     * 根据分类获取描述规则
     */
@ApiOperation("根据分类ID获取描述规则")

    @Log(title = "根据分类ID获取描述规则")
    @PostMapping( "/selectPropertyFormulaByTypeId/{typeId}")
    @ResponseBody
    public AjaxResult selectPropertyFormulaByTypeId(@ApiParam@PathVariable("typeId") Long typeId)
    {
        AjaxResult ajax = new AjaxResult();
        ajax.put("data", attribValueService.selectPropertyFormulaByTypeId(typeId));
        return ajax;
    }
    /**
     * 根据分类和描述获取描述规则
     */
@ApiOperation("根据分类和描述获取描述规则")

    @Log(title = "根据分类和描述获取描述规则")
    @PostMapping( "/selectPropertyFormulaByTypeAndProperty/{class_no},{entity_property_no}")
    @ResponseBody
    public AjaxResult selectPropertyFormulaByTypeId(@ApiParam@PathVariable("class_no") Long class_no,@ApiParam@PathVariable("entity_property_no") Long entity_property_no)
    {
        AjaxResult ajax = new AjaxResult();
        ajax.put("data", attribValueService.selectPropertyFormulaByTypeAndProperty(class_no,entity_property_no));
        return ajax;
    }
}
