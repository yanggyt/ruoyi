package com.ruoyi.web.controller.exam;

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
import com.ruoyi.exam.domain.TrainCourseCategory;
import com.ruoyi.exam.service.ITrainCourseCategoryService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课程分类 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/exam/trainCourseCategory")
public class TrainCourseCategoryController extends BaseController
{
    private String prefix = "exam/trainCourseCategory";
	
	@Autowired
	private ITrainCourseCategoryService trainCourseCategoryService;
	
	@RequiresPermissions("exam:trainCourseCategory:view")
	@GetMapping()
	public String trainCourseCategory()
	{
	    return prefix + "/trainCourseCategory";
	}
	
	/**
	 * 查询课程分类列表
	 */
	@RequiresPermissions("exam:trainCourseCategory:list")
	@GetMapping("/list")
	@ResponseBody
	public List<TrainCourseCategory> list(TrainCourseCategory trainCourseCategory)
	{
        List<TrainCourseCategory> list = trainCourseCategoryService.selectTrainCourseCategoryPage(trainCourseCategory);
		return list;
	}
	
	
	/**
	 * 导出课程分类列表
	 */
	@RequiresPermissions("exam:trainCourseCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourseCategory trainCourseCategory)
    {
    	List<TrainCourseCategory> list = trainCourseCategoryService.selectTrainCourseCategoryList(trainCourseCategory);
        ExcelUtil<TrainCourseCategory> util = new ExcelUtil<TrainCourseCategory>(TrainCourseCategory.class);
        return util.exportExcel(list, "trainCourseCategory");
    }
	
	/**
	 * 新增课程分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存课程分类
	 */
	@RequiresPermissions("exam:trainCourseCategory:add")
	@Log(title = "课程分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseCategory trainCourseCategory)
	{		
		return toAjax(trainCourseCategoryService.insert(trainCourseCategory));
	}

	/**
	 * 修改课程分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseCategory trainCourseCategory = trainCourseCategoryService.selectById(id);
		mmap.put("trainCourseCategory", trainCourseCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存课程分类
	 */
	@RequiresPermissions("exam:trainCourseCategory:edit")
	@Log(title = "课程分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseCategory trainCourseCategory)
	{		
		return toAjax(trainCourseCategoryService.updateById(trainCourseCategory));
	}
	
	/**
	 * 删除课程分类
	 */
	@RequiresPermissions("exam:trainCourseCategory:remove")
	@Log(title = "课程分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseCategoryService.deleteByIds(ids));
	}
	
}
