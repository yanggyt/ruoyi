package com.ruoyi.courseware.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.courseware.service.ITrainCoursewareCategoryService;
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
 * 部门信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/train/courseware/category")
public class TrainCoursewareCategoryController extends BaseController
{
    private String prefix = "courseware/category";

    @Autowired
    private ITrainCoursewareCategoryService trainCoursewareCategoryService;

    @RequiresPermissions("vip:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("vip:dept:list")
    @GetMapping("/list")
    @ResponseBody
    public List<TrainCoursewareCategory> list(TrainCoursewareCategory dept)
    {
        List<TrainCoursewareCategory> deptList = trainCoursewareCategoryService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("dept", trainCoursewareCategoryService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("vip:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TrainCoursewareCategory dept)
    {
        dept.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(trainCoursewareCategoryService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        TrainCoursewareCategory dept = trainCoursewareCategoryService.selectDeptById(deptId);
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
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("vip:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TrainCoursewareCategory dept)
    {
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(trainCoursewareCategoryService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("vip:dept:remove")
    @PostMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (trainCoursewareCategoryService.selectDeptCount(deptId) > 0)
        {
            return error(1, "存在下级部门,不允许删除");
        }
        if (trainCoursewareCategoryService.checkDeptExistUser(deptId))
        {
            return error(1, "部门存在用户,不允许删除");
        }
        return toAjax(trainCoursewareCategoryService.deleteDeptById(deptId));
    }

    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(TrainCoursewareCategory dept)
    {
        return trainCoursewareCategoryService.checkDeptNameUnique(dept);
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", trainCoursewareCategoryService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = trainCoursewareCategoryService.selectDeptTree();
        return tree;
    }

}
