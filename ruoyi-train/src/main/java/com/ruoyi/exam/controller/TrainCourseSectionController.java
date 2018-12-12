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
import com.ruoyi.exam.domain.TrainCourseSection;
import com.ruoyi.exam.service.ITrainCourseSectionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课程章节 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/exam/trainCourseSection")
public class TrainCourseSectionController extends BaseController
{
    private String prefix = "exam/trainCourseSection";
	
	@Autowired
	private ITrainCourseSectionService trainCourseSectionService;
	
	@RequiresPermissions("exam:trainCourseSection:view")
	@GetMapping()
	public String trainCourseSection()
	{
	    return prefix + "/trainCourseSection";
	}
	
	/**
	 * 查询课程章节列表
	 */
	@RequiresPermissions("exam:trainCourseSection:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseSection trainCourseSection)
	{
        List<TrainCourseSection> list = trainCourseSectionService.selectTrainCourseSectionPage(trainCourseSection);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出课程章节列表
	 */
	@RequiresPermissions("exam:trainCourseSection:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourseSection trainCourseSection)
    {
    	List<TrainCourseSection> list = trainCourseSectionService.selectTrainCourseSectionList(trainCourseSection);
        ExcelUtil<TrainCourseSection> util = new ExcelUtil<TrainCourseSection>(TrainCourseSection.class);
        return util.exportExcel(list, "trainCourseSection");
    }
	
	/**
	 * 新增课程章节
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存课程章节
	 */
	@RequiresPermissions("exam:trainCourseSection:add")
	@Log(title = "课程章节", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseSection trainCourseSection)
	{		
		return toAjax(trainCourseSectionService.insert(trainCourseSection));
	}

	/**
	 * 修改课程章节
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseSection trainCourseSection = trainCourseSectionService.selectById(id);
		mmap.put("trainCourseSection", trainCourseSection);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存课程章节
	 */
	@RequiresPermissions("exam:trainCourseSection:edit")
	@Log(title = "课程章节", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseSection trainCourseSection)
	{		
		return toAjax(trainCourseSectionService.updateById(trainCourseSection));
	}
	
	/**
	 * 删除课程章节
	 */
	@RequiresPermissions("exam:trainCourseSection:remove")
	@Log(title = "课程章节", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseSectionService.deleteByIds(ids));
	}
	
}
