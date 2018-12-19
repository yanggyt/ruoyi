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
 * 课件分类信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/train/courseware/category")
public class TrainCoursewareCategoryController extends BaseController {
    private String prefix = "courseware/category";

    @Autowired
    private ITrainCoursewareCategoryService trainCoursewareCategoryService;

    @RequiresPermissions("train:courseware:category:view")
    @GetMapping()
    public String category() {
        return prefix + "/category";
    }

    @RequiresPermissions("train:courseware:category:list")
    @GetMapping("/list")
    @ResponseBody
    public List<TrainCoursewareCategory> list(TrainCoursewareCategory category) {
        List<TrainCoursewareCategory> categoryList = trainCoursewareCategoryService.selectCategoryList( category );
        return categoryList;
    }

    /**
     * 新增课件分类
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        mmap.put( "category", trainCoursewareCategoryService.selectCategoryById( parentId ) );
        return prefix + "/add";
    }

    /**
     * 新增保存课件分类
     */
    @Log(title = "课件分类管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("train:courseware:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TrainCoursewareCategory category) {
        category.setCreateBy( ShiroUtils.getLoginName() );
        return toAjax( trainCoursewareCategoryService.insertCategory( category ) );
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TrainCoursewareCategory category = trainCoursewareCategoryService.selectCategoryById( id );
        if (StringUtils.isNotNull( category ) && 100L == id) {
            category.setParentName( "无" );
        }
        mmap.put( "category", category );
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "课件分类管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("train:courseware:category:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TrainCoursewareCategory category) {
        category.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( trainCoursewareCategoryService.updateCategory( category ) );
    }

    /**
     * 删除
     */
    @Log(title = "课件分类管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("train:courseware:category:remove")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        if (trainCoursewareCategoryService.selectCategoryCount( id ) > 0) {
            return error( 1, "存在下级课件分类,不允许删除" );
        }
        if (trainCoursewareCategoryService.checkCategoryExistCourseware( id )) {
            return error( 1, "课件分类存在课件,不允许删除" );
        }
        return toAjax( trainCoursewareCategoryService.deleteCategoryById( id ) );
    }

    /**
     * 校验课件分类名称
     */
    @PostMapping("/checkCategoryNameUnique")
    @ResponseBody
    public String checkCategoryNameUnique(TrainCoursewareCategory category) {
        return trainCoursewareCategoryService.checkCategoryNameUnique( category );
    }

    /**
     * 选择课件分类树
     */
    @GetMapping("/selectCategoryTree/{id}")
    public String selectCategoryTree(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put( "category", trainCoursewareCategoryService.selectCategoryById( id ) );
        return prefix + "/tree";
    }

    /**
     * 加载课件分类列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData() {
        List<Map<String, Object>> tree = trainCoursewareCategoryService.selectCategoryTree();
        return tree;
    }

}
