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
import com.ruoyi.system.domain.JdStudent;
import com.ruoyi.system.service.IJdStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交大学生数据Controller
 * 
 * @author ruoyi
 * @date 2023-07-07
 */
@Controller
@RequestMapping("/system/student")
public class JdStudentController extends BaseController
{
    private String prefix = "system/student";

    @Autowired
    private IJdStudentService jdStudentService;

    @RequiresPermissions("system:student:view")
    @GetMapping()
    public String student()
    {
        return prefix + "/student";
    }

    /**
     * 查询交大学生数据列表
     */
    @RequiresPermissions("system:student:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JdStudent jdStudent)
    {
        startPage();
        List<JdStudent> list = jdStudentService.selectJdStudentList(jdStudent);
        return getDataTable(list);
    }

    /**
     * 导出交大学生数据列表
     */
    @RequiresPermissions("system:student:export")
    @Log(title = "交大学生数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JdStudent jdStudent)
    {
        List<JdStudent> list = jdStudentService.selectJdStudentList(jdStudent);
        ExcelUtil<JdStudent> util = new ExcelUtil<JdStudent>(JdStudent.class);
        return util.exportExcel(list, "交大学生数据数据");
    }

    /**
     * 新增交大学生数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存交大学生数据
     */
    @RequiresPermissions("system:student:add")
    @Log(title = "交大学生数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(JdStudent jdStudent)
    {
        return toAjax(jdStudentService.insertJdStudent(jdStudent));
    }

    /**
     * 修改交大学生数据
     */
    @RequiresPermissions("system:student:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JdStudent jdStudent = jdStudentService.selectJdStudentById(id);
        mmap.put("jdStudent", jdStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存交大学生数据
     */
    @RequiresPermissions("system:student:edit")
    @Log(title = "交大学生数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(JdStudent jdStudent)
    {
        return toAjax(jdStudentService.updateJdStudent(jdStudent));
    }

    /**
     * 删除交大学生数据
     */
    @RequiresPermissions("system:student:remove")
    @Log(title = "交大学生数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jdStudentService.deleteJdStudentByIds(ids));
    }
}
