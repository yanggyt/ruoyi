package com.ruoyi.course.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.course.domain.TrainCourseCategory;
import com.ruoyi.course.service.ITrainCourseCategoryService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程分类管理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/train/course/category")
public class TrainCourseCategoryController extends BaseController
{
    private String prefix = "course/category";

    @Autowired
    private ITrainCourseCategoryService trainCourseCategoryService;

    @RequiresPermissions("train:course:category:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("train:course:category:list")
    @GetMapping("/list")
    @ResponseBody
    public List<TrainCourseCategory> list(TrainCourseCategory dept)
    {
        List<TrainCourseCategory> deptList = trainCourseCategoryService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增课程分类
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("dept", trainCourseCategoryService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存课程分类
     */
    @Log(title = "课程分类管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("train:course:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TrainCourseCategory dept)
    {
        dept.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(trainCourseCategoryService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        TrainCourseCategory dept = trainCourseCategoryService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "课程分类管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("train:course:category:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TrainCourseCategory dept)
    {
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(trainCourseCategoryService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "课程分类管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("train:course:category:remove")
    @PostMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (trainCourseCategoryService.selectDeptCount(deptId) > 0)
        {
            return error(1, "存在下级课程分类,不允许删除");
        }
        if (trainCourseCategoryService.checkDeptExistUser(deptId))
        {
            return error(1, "课程分类存在用户,不允许删除");
        }
        return toAjax(trainCourseCategoryService.deleteDeptById(deptId));
    }

    /**
     * 校验课程分类名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(TrainCourseCategory dept)
    {
        return trainCourseCategoryService.checkDeptNameUnique(dept);
    }

    /**
     * 选择课程分类树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", trainCourseCategoryService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载课程分类列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = trainCourseCategoryService.selectDeptTree();
        return tree;
    }

}
