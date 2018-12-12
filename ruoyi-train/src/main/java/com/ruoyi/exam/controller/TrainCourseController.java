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
import com.ruoyi.exam.domain.TrainCourse;
import com.ruoyi.exam.service.ITrainCourseService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课程 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/exam/trainCourse")
public class TrainCourseController extends BaseController
{
    private String prefix = "exam/trainCourse";
	
	@Autowired
	private ITrainCourseService trainCourseService;
	
	@RequiresPermissions("exam:trainCourse:view")
	@GetMapping()
	public String trainCourse()
	{
	    return prefix + "/trainCourse";
	}
	
	/**
	 * 查询课程列表
	 */
	@RequiresPermissions("exam:trainCourse:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourse trainCourse)
	{
        List<TrainCourse> list = trainCourseService.selectTrainCoursePage(trainCourse);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出课程列表
	 */
	@RequiresPermissions("exam:trainCourse:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourse trainCourse)
    {
    	List<TrainCourse> list = trainCourseService.selectTrainCourseList(trainCourse);
        ExcelUtil<TrainCourse> util = new ExcelUtil<TrainCourse>(TrainCourse.class);
        return util.exportExcel(list, "trainCourse");
    }
	
	/**
	 * 新增课程
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存课程
	 */
	@RequiresPermissions("exam:trainCourse:add")
	@Log(title = "课程", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourse trainCourse)
	{		
		return toAjax(trainCourseService.insert(trainCourse));
	}

	/**
	 * 修改课程
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourse trainCourse = trainCourseService.selectById(id);
		mmap.put("trainCourse", trainCourse);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存课程
	 */
	@RequiresPermissions("exam:trainCourse:edit")
	@Log(title = "课程", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourse trainCourse)
	{		
		return toAjax(trainCourseService.updateById(trainCourse));
	}
	
	/**
	 * 删除课程
	 */
	@RequiresPermissions("exam:trainCourse:remove")
	@Log(title = "课程", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseService.deleteByIds(ids));
	}
	
}
