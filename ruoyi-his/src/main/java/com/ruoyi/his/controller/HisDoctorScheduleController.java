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
import com.ruoyi.his.domain.HisDoctorSchedule;
import com.ruoyi.his.service.IHisDoctorScheduleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医生排班Controller
 * 
 * @author bend
 * @date 2020-07-03
 */
@Controller
@RequestMapping("/his/doctorSchedule")
public class HisDoctorScheduleController extends BaseController
{
    private String prefix = "his/doctorSchedule";

    @Autowired
    private IHisDoctorScheduleService hisDoctorScheduleService;

    @RequiresPermissions("his:doctorSchedule:view")
    @GetMapping()
    public String doctorSchedule()
    {
        return prefix + "/doctorSchedule";
    }

    /**
     * 查询医生排班列表
     */
    @RequiresPermissions("his:doctorSchedule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisDoctorSchedule hisDoctorSchedule)
    {
        startPage();
        List<HisDoctorSchedule> list = hisDoctorScheduleService.selectHisDoctorScheduleList(hisDoctorSchedule);
        return getDataTable(list);
    }

    /**
     * 导出医生排班列表
     */
    @RequiresPermissions("his:doctorSchedule:export")
    @Log(title = "医生排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisDoctorSchedule hisDoctorSchedule)
    {
        List<HisDoctorSchedule> list = hisDoctorScheduleService.selectHisDoctorScheduleList(hisDoctorSchedule);
        ExcelUtil<HisDoctorSchedule> util = new ExcelUtil<HisDoctorSchedule>(HisDoctorSchedule.class);
        return util.exportExcel(list, "doctorSchedule");
    }

    /**
     * 新增医生排班
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医生排班
     */
    @RequiresPermissions("his:doctorSchedule:add")
    @Log(title = "医生排班", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisDoctorSchedule hisDoctorSchedule)
    {
        return toAjax(hisDoctorScheduleService.insertHisDoctorSchedule(hisDoctorSchedule));
    }

    /**
     * 修改医生排班
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisDoctorSchedule hisDoctorSchedule = hisDoctorScheduleService.selectHisDoctorScheduleById(id);
        mmap.put("hisDoctorSchedule", hisDoctorSchedule);
        return prefix + "/edit";
    }

    /**
     * 修改保存医生排班
     */
    @RequiresPermissions("his:doctorSchedule:edit")
    @Log(title = "医生排班", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisDoctorSchedule hisDoctorSchedule)
    {
        return toAjax(hisDoctorScheduleService.updateHisDoctorSchedule(hisDoctorSchedule));
    }

    /**
     * 删除医生排班
     */
    @RequiresPermissions("his:doctorSchedule:remove")
    @Log(title = "医生排班", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisDoctorScheduleService.deleteHisDoctorScheduleByIds(ids));
    }
}
