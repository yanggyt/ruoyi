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
import com.ruoyi.exam.domain.TrainCourseSectionCourseware;
import com.ruoyi.exam.service.ITrainCourseSectionCoursewareService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 章节课件 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/exam/trainCourseSectionCourseware")
public class TrainCourseSectionCoursewareController extends BaseController
{
    private String prefix = "exam/trainCourseSectionCourseware";
	
	@Autowired
	private ITrainCourseSectionCoursewareService trainCourseSectionCoursewareService;
	
	@RequiresPermissions("exam:trainCourseSectionCourseware:view")
	@GetMapping()
	public String trainCourseSectionCourseware()
	{
	    return prefix + "/trainCourseSectionCourseware";
	}
	
	/**
	 * 查询章节课件列表
	 */
	@RequiresPermissions("exam:trainCourseSectionCourseware:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseSectionCourseware trainCourseSectionCourseware)
	{
        List<TrainCourseSectionCourseware> list = trainCourseSectionCoursewareService.selectTrainCourseSectionCoursewarePage(trainCourseSectionCourseware);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出章节课件列表
	 */
	@RequiresPermissions("exam:trainCourseSectionCourseware:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrainCourseSectionCourseware trainCourseSectionCourseware)
    {
    	List<TrainCourseSectionCourseware> list = trainCourseSectionCoursewareService.selectTrainCourseSectionCoursewareList(trainCourseSectionCourseware);
        ExcelUtil<TrainCourseSectionCourseware> util = new ExcelUtil<TrainCourseSectionCourseware>(TrainCourseSectionCourseware.class);
        return util.exportExcel(list, "trainCourseSectionCourseware");
    }
	
	/**
	 * 新增章节课件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存章节课件
	 */
	@RequiresPermissions("exam:trainCourseSectionCourseware:add")
	@Log(title = "章节课件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseSectionCourseware trainCourseSectionCourseware)
	{		
		return toAjax(trainCourseSectionCoursewareService.insert(trainCourseSectionCourseware));
	}

	/**
	 * 修改章节课件
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseSectionCourseware trainCourseSectionCourseware = trainCourseSectionCoursewareService.selectById(id);
		mmap.put("trainCourseSectionCourseware", trainCourseSectionCourseware);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存章节课件
	 */
	@RequiresPermissions("exam:trainCourseSectionCourseware:edit")
	@Log(title = "章节课件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseSectionCourseware trainCourseSectionCourseware)
	{		
		return toAjax(trainCourseSectionCoursewareService.updateById(trainCourseSectionCourseware));
	}
	
	/**
	 * 删除章节课件
	 */
	@RequiresPermissions("exam:trainCourseSectionCourseware:remove")
	@Log(title = "章节课件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(trainCourseSectionCoursewareService.deleteByIds(ids));
	}
	
}
