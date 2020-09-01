package com.ruoyi.his.controller;

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
import com.ruoyi.his.domain.HisDoctorDepartment;
import com.ruoyi.his.service.IHisDoctorDepartmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医生科室关系Controller
 * 
 * @author bend
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/his/doctorDepartment")
public class HisDoctorDepartmentController extends BaseController
{
    private String prefix = "his/doctorDepartment";

    @Autowired
    private IHisDoctorDepartmentService hisDoctorDepartmentService;

    @RequiresPermissions("his:doctorDepartment:view")
    @GetMapping()
    public String doctorDepartment()
    {
        return prefix + "/doctorDepartment";
    }

    /**
     * 查询医生科室关系列表
     */
    @RequiresPermissions("his:doctorDepartment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisDoctorDepartment hisDoctorDepartment)
    {
        startPage();
        List<HisDoctorDepartment> list = hisDoctorDepartmentService.selectHisDoctorDepartmentList(hisDoctorDepartment);
        return getDataTable(list);
    }

    /**
     * 导出医生科室关系列表
     */
    @RequiresPermissions("his:doctorDepartment:export")
    @Log(title = "医生科室关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisDoctorDepartment hisDoctorDepartment)
    {
        List<HisDoctorDepartment> list = hisDoctorDepartmentService.selectHisDoctorDepartmentList(hisDoctorDepartment);
        ExcelUtil<HisDoctorDepartment> util = new ExcelUtil<HisDoctorDepartment>(HisDoctorDepartment.class);
        return util.exportExcel(list, "doctorDepartment");
    }

    /**
     * 新增医生科室关系
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医生科室关系
     */
    @RequiresPermissions("his:doctorDepartment:add")
    @Log(title = "医生科室关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisDoctorDepartment hisDoctorDepartment)
    {
        return toAjax(hisDoctorDepartmentService.insertHisDoctorDepartment(hisDoctorDepartment));
    }

    /**
     * 修改医生科室关系
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisDoctorDepartment hisDoctorDepartment = hisDoctorDepartmentService.selectHisDoctorDepartmentById(id);
        mmap.put("hisDoctorDepartment", hisDoctorDepartment);
        return prefix + "/edit";
    }

    /**
     * 修改保存医生科室关系
     */
    @RequiresPermissions("his:doctorDepartment:edit")
    @Log(title = "医生科室关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisDoctorDepartment hisDoctorDepartment)
    {
        return toAjax(hisDoctorDepartmentService.updateHisDoctorDepartment(hisDoctorDepartment));
    }

    /**
     * 删除医生科室关系
     */
    @RequiresPermissions("his:doctorDepartment:remove")
    @Log(title = "医生科室关系", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisDoctorDepartmentService.deleteHisDoctorDepartmentByIds(ids));
    }
}
