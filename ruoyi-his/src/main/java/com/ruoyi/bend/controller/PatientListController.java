package com.ruoyi.bend.controller;

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
import com.ruoyi.bend.domain.PatientList;
import com.ruoyi.bend.service.IPatientListService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 就诊人列表Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/patientList")
public class PatientListController extends BaseController
{
    private String prefix = "bend/patientList";

    @Autowired
    private IPatientListService patientListService;

    @RequiresPermissions("bend:patientList:view")
    @GetMapping()
    public String patientList()
    {
        return prefix + "/patientList";
    }

    /**
     * 查询就诊人列表列表
     */
    @RequiresPermissions("bend:patientList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PatientList patientList)
    {
        startPage();
        List<PatientList> list = patientListService.selectPatientListList(patientList);
        return getDataTable(list);
    }

    /**
     * 导出就诊人列表列表
     */
    @RequiresPermissions("bend:patientList:export")
    @Log(title = "就诊人列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PatientList patientList)
    {
        List<PatientList> list = patientListService.selectPatientListList(patientList);
        ExcelUtil<PatientList> util = new ExcelUtil<PatientList>(PatientList.class);
        return util.exportExcel(list, "patientList");
    }

    /**
     * 新增就诊人列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存就诊人列表
     */
    @RequiresPermissions("bend:patientList:add")
    @Log(title = "就诊人列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PatientList patientList)
    {
        return toAjax(patientListService.insertPatientList(patientList));
    }

    /**
     * 修改就诊人列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PatientList patientList = patientListService.selectPatientListById(id);
        mmap.put("patientList", patientList);
        return prefix + "/edit";
    }

    /**
     * 修改保存就诊人列表
     */
    @RequiresPermissions("bend:patientList:edit")
    @Log(title = "就诊人列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PatientList patientList)
    {
        return toAjax(patientListService.updatePatientList(patientList));
    }

    /**
     * 删除就诊人列表
     */
    @RequiresPermissions("bend:patientList:remove")
    @Log(title = "就诊人列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(patientListService.deletePatientListByIds(ids));
    }
}
