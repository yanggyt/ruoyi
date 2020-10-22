package com.ruoyi.web.controller.front;

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
import com.ruoyi.front.domain.OnlineCourses;
import com.ruoyi.front.service.IOnlineCoursesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线上课程Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/courses")
public class OnlineCoursesController extends BaseController
{
    private String prefix = "front/courses";

    @Autowired
    private IOnlineCoursesService onlineCoursesService;

    @RequiresPermissions("front:courses:view")
    @GetMapping()
    public String courses()
    {
        return prefix + "/courses";
    }

    /**
     * 查询线上课程列表
     */
    @RequiresPermissions("front:courses:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OnlineCourses onlineCourses)
    {
        startPage();
        List<OnlineCourses> list = onlineCoursesService.selectOnlineCoursesList(onlineCourses);
        return getDataTable(list);
    }

    /**
     * 导出线上课程列表
     */
    @RequiresPermissions("front:courses:export")
    @Log(title = "线上课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OnlineCourses onlineCourses)
    {
        List<OnlineCourses> list = onlineCoursesService.selectOnlineCoursesList(onlineCourses);
        ExcelUtil<OnlineCourses> util = new ExcelUtil<OnlineCourses>(OnlineCourses.class);
        return util.exportExcel(list, "courses");
    }

    /**
     * 新增线上课程
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存线上课程
     */
    @RequiresPermissions("front:courses:add")
    @Log(title = "线上课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OnlineCourses onlineCourses)
    {
        return toAjax(onlineCoursesService.insertOnlineCourses(onlineCourses));
    }

    /**
     * 修改线上课程
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OnlineCourses onlineCourses = onlineCoursesService.selectOnlineCoursesById(id);
        mmap.put("onlineCourses", onlineCourses);
        return prefix + "/edit";
    }

    /**
     * 修改保存线上课程
     */
    @RequiresPermissions("front:courses:edit")
    @Log(title = "线上课程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OnlineCourses onlineCourses)
    {
        return toAjax(onlineCoursesService.updateOnlineCourses(onlineCourses));
    }

    /**
     * 删除线上课程
     */
    @RequiresPermissions("front:courses:remove")
    @Log(title = "线上课程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(onlineCoursesService.deleteOnlineCoursesByIds(ids));
    }
}
