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
import com.ruoyi.exam.domain.TrainCoursewareCategory;
import com.ruoyi.exam.service.ITrainCoursewareCategoryService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课件分类 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/exam/trainCoursewareCategory")
public class TrainCoursewareCategoryController extends BaseController
{
    private String prefix = "exam/trainCoursewareCategory";
	
	@Autowired
	private ITrainCoursewareCategoryService trainCoursewareCategoryService;
	
	@RequiresPermissions("exam:trainCoursewareCategory:view")
	@GetMapping()
	public String trainCoursewareCategory()
	{
	    return prefix + "/trainCoursewareCategory";
	}
	
	/**
	 * 查询课件分类列表
	 */
	@RequiresPermissions("exam:trainCoursewareCategory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCoursewareCategory trainCoursewareCategory)
	{
        List<TrainCoursewareCategory> list = trainCoursewareCategoryService.selectTrainCoursewareCategoryPage(trainCoursewareCategory);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出课件分类列表
	 */
	@RequiresPermissions("exam:trainCoursewareCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCoursewareCategory trainCoursewareCategory)
    {
    	List<TrainCoursewareCategory> list = trainCoursewareCategoryService.selectTrainCoursewareCategoryList(trainCoursewareCategory);
        ExcelUtil<TrainCoursewareCategory> util = new ExcelUtil<TrainCoursewareCategory>(TrainCoursewareCategory.class);
        return util.exportExcel(list, "trainCoursewareCategory");
    }
	
	/**
	 * 新增课件分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存课件分类
	 */
	@RequiresPermissions("exam:trainCoursewareCategory:add")
	@Log(title = "课件分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCoursewareCategory trainCoursewareCategory)
	{		
		return toAjax(trainCoursewareCategoryService.insert(trainCoursewareCategory));
	}

	/**
	 * 修改课件分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCoursewareCategory trainCoursewareCategory = trainCoursewareCategoryService.selectById(id);
		mmap.put("trainCoursewareCategory", trainCoursewareCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存课件分类
	 */
	@RequiresPermissions("exam:trainCoursewareCategory:edit")
	@Log(title = "课件分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCoursewareCategory trainCoursewareCategory)
	{		
		return toAjax(trainCoursewareCategoryService.updateById(trainCoursewareCategory));
	}
	
	/**
	 * 删除课件分类
	 */
	@RequiresPermissions("exam:trainCoursewareCategory:remove")
	@Log(title = "课件分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCoursewareCategoryService.deleteByIds(ids));
	}
	
}
