package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.his.domain.HisDoctorDepartment;
import com.ruoyi.his.service.IHisDoctorDepartmentService;
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
import com.ruoyi.his.domain.HisDepartment;
import com.ruoyi.his.service.IHisDepartmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 科室Controller
 * 
 * @author bend
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/his/department")
public class HisDepartmentController extends BaseController
{
    private String prefix = "his/department";

    @Autowired
    private IHisDepartmentService hisDepartmentService;
    @Autowired
    private IHisDoctorDepartmentService iHisDoctorDepartmentService;

    @RequiresPermissions("his:department:view")
    @GetMapping()
    public String department()
    {
        return prefix + "/department";
    }

    /**
     * 查询科室列表
     */
    @RequiresPermissions("his:department:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisDepartment hisDepartment)
    {
        startPage();
        List<HisDepartment> list = hisDepartmentService.selectHisDepartmentList(hisDepartment);
        return getDataTable(list);
    }

    /**
     * 导出科室列表
     */
    @RequiresPermissions("his:department:export")
    @Log(title = "科室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisDepartment hisDepartment)
    {
        List<HisDepartment> list = hisDepartmentService.selectHisDepartmentList(hisDepartment);
        ExcelUtil<HisDepartment> util = new ExcelUtil<HisDepartment>(HisDepartment.class);
        return util.exportExcel(list, "department");
    }

    /**
     * 新增科室
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存科室
     */
    @RequiresPermissions("his:department:add")
    @Log(title = "科室", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisDepartment hisDepartment)
    {
        return toAjax(hisDepartmentService.insertHisDepartment(hisDepartment));
    }

    /**
     * 修改科室
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisDepartment hisDepartment = hisDepartmentService.selectHisDepartmentById(id);
        mmap.put("hisDepartment", hisDepartment);
        return prefix + "/edit";
    }

    /**
     * 修改保存科室
     */
    @RequiresPermissions("his:department:edit")
    @Log(title = "科室", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisDepartment hisDepartment)
    {
        //科室更新时 同步更新对应关系
        String deptId = hisDepartment.getDeptId();
        HisDoctorDepartment hisDoctorDepartment = new HisDoctorDepartment();
        hisDoctorDepartment.setDeptId(deptId);
        hisDoctorDepartment.setIsShow(hisDepartment.getIsShow());
        iHisDoctorDepartmentService.updateHisDoctorDepartmentByDeptId(hisDoctorDepartment);

        return toAjax(hisDepartmentService.updateHisDepartment(hisDepartment));
    }

    /**
     * 删除科室
     */
    @RequiresPermissions("his:department:remove")
    @Log(title = "科室", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisDepartmentService.deleteHisDepartmentByIds(ids));
    }
}
