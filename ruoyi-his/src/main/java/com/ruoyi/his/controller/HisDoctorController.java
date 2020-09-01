package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisDepartment;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.domain.HisDoctorDepartment;
import com.ruoyi.his.domain.HisRegistrationTemplate;
import com.ruoyi.his.service.IHisDepartmentService;
import com.ruoyi.his.service.IHisDoctorDepartmentService;
import com.ruoyi.his.service.IHisDoctorService;
import com.ruoyi.his.service.IHisRegistrationTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医生Controller
 * 
 * @author bend
 * @date 2020-07-01
 */
@Controller
@RequestMapping("/his/doctor")
public class HisDoctorController extends BaseController
{
    private String prefix = "his/doctor";

    @Autowired
    private IHisDoctorService hisDoctorService;
    @Autowired
    private IHisRegistrationTemplateService iHisRegistrationTemplateService;
    @Autowired
    private IHisDoctorDepartmentService iHisDoctorDepartmentService;
    @Autowired
    private IHisDepartmentService iHisDepartmentService;


    @RequiresPermissions("his:doctor:view")
    @GetMapping()
    public String doctor()
    {
        return prefix + "/doctor";
    }

    /**
     * 查询医生列表
     */
    @RequiresPermissions("his:doctor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisDoctor hisDoctor)
    {
        startPage();
        List<HisDoctor> list = hisDoctorService.selectHisDoctorList(hisDoctor);
        return getDataTable(list);
    }

    /**
     * 导出医生列表
     */
    @RequiresPermissions("his:doctor:export")
    @Log(title = "医生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisDoctor hisDoctor)
    {
        List<HisDoctor> list = hisDoctorService.selectHisDoctorList(hisDoctor);
        ExcelUtil<HisDoctor> util = new ExcelUtil<HisDoctor>(HisDoctor.class);
        return util.exportExcel(list, "doctor");
    }

    /**
     * 新增医生
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医生
     */
    @RequiresPermissions("his:doctor:add")
    @Log(title = "医生", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisDoctor hisDoctor)
    {
        return toAjax(hisDoctorService.insertHisDoctor(hisDoctor));
    }

    /**
     * 修改医生
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisDoctor hisDoctor = hisDoctorService.selectHisDoctorById(id);
        mmap.put("hisDoctor", hisDoctor);
        if(Func.isNotEmpty(hisDoctor)){
            //编辑时可设置挂号模板
            HisRegistrationTemplate template = new HisRegistrationTemplate();
            template.setOrgCode(hisDoctor.getOrgCode());
            template.setIsShow(1);
            List<HisRegistrationTemplate> hisRegistrationTemplateList = iHisRegistrationTemplateService.selectHisRegistrationTemplateList(template);
            if (Func.isNotEmpty(hisRegistrationTemplateList)){
                mmap.put("hisRegistrationTemplateList", hisRegistrationTemplateList);
            }
            //修改默认科室
            HisDoctorDepartment hisDoctorDepartment= new HisDoctorDepartment();
            hisDoctorDepartment.setDoctorId(hisDoctor.getDoctorId());
            List<HisDoctorDepartment> hisDoctorDepartmentList = iHisDoctorDepartmentService.selectHisDoctorDepartmentList(hisDoctorDepartment);
            if (Func.isNotEmpty(hisDoctorDepartmentList)){
                mmap.put("hisDoctorDepartmentList", hisDoctorDepartmentList);
            }
        }
        return prefix + "/edit";
    }

    /**
     * 修改保存医生
     */
    @RequiresPermissions("his:doctor:edit")
    @Log(title = "医生", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisDoctor hisDoctor)
    {
        //根据挂号模板ID获取模板名称
        String templateId = hisDoctor.getTemplateId();
        if (Func.isNotEmpty(templateId)){
            HisRegistrationTemplate template = new HisRegistrationTemplate();
            template.setTemplateId(templateId);
            HisRegistrationTemplate hisRegistrationTemplate = iHisRegistrationTemplateService.selectHisRegistrationTemplate(template);
            if (Func.isNotEmpty(hisRegistrationTemplate)){
                hisDoctor.setTemplateName(hisRegistrationTemplate.getTemplateName());
            }
        }
        //设置默认科室
        String deptId = hisDoctor.getDeptId();
        if (Func.isNotEmpty(deptId)){
            HisDepartment hisDepartment = iHisDepartmentService.selectHisDepartmentByDeptId(deptId);
            if (Func.isNotEmpty(hisDepartment)){
                hisDoctor.setDeptName(hisDepartment.getDeptName());
            }
        }
        return toAjax(hisDoctorService.updateHisDoctor(hisDoctor));
    }

    /**
     * 删除医生
     */
    @RequiresPermissions("his:doctor:remove")
    @Log(title = "医生", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisDoctorService.deleteHisDoctorByIds(ids));
    }
}
