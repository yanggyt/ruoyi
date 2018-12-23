package com.ruoyi.train.courseware.controller;

import java.util.List;

import com.ruoyi.train.courseware.domain.TrainCourseware;
import com.ruoyi.train.courseware.domain.TrainCoursewareCategory;
import com.ruoyi.train.courseware.service.ITrainCoursewareCategoryService;
import com.ruoyi.train.courseware.service.ITrainCoursewareService;
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
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 课件 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-23
 */
@Controller
@RequestMapping("/train/trainCourseware")
public class TrainCoursewareController extends BaseController
{
	private String prefix = "train/trainCourseware";

	@Autowired
	private ITrainCoursewareService trainCoursewareService;

	@Autowired
	private ITrainCoursewareCategoryService trainCoursewareCategoryService;
	@RequiresPermissions("train:trainCourseware:view")
	@GetMapping()
	public String trainCourseware()
	{
		return prefix + "/trainCourseware";
	}

	/**
	 * 查询课件列表
	 */
	@RequiresPermissions("train:trainCourseware:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrainCourseware trainCourseware)
	{
		List<TrainCourseware> list = trainCoursewareService.selectTrainCoursewarePage(trainCourseware);
		return getDataTable(list);
	}


	/**
	 * 导出课件列表
	 */
	@RequiresPermissions("train:trainCourseware:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TrainCourseware trainCourseware)
	{
		List<TrainCourseware> list = trainCoursewareService.selectTrainCoursewareList(trainCourseware);
		ExcelUtil<TrainCourseware> util = new ExcelUtil<TrainCourseware>(TrainCourseware.class);
		return util.exportExcel(list, "trainCourseware");
	}

	/**
	 * 新增课件
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存课件
	 */
	@RequiresPermissions("train:trainCourseware:add")
	@Log(title = "课件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrainCourseware trainCourseware)
	{
		return toAjax(trainCoursewareService.insert(trainCourseware));
	}

	/**
	 * 修改课件
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrainCourseware trainCourseware = trainCoursewareService.selectById(id);
		TrainCoursewareCategory category = trainCoursewareCategoryService.selectCategoryById((long)trainCourseware.getTrainCoursewareCategoryId());
		mmap.put("trainCourseware", trainCourseware);
		mmap.put("category", category);
		return prefix + "/edit";
	}

	/**
	 * 修改保存课件
	 */
	@RequiresPermissions("train:trainCourseware:edit")
	@Log(title = "课件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrainCourseware trainCourseware)
	{
		return toAjax(trainCoursewareService.updateById(trainCourseware));
	}

	/**
	 * 删除课件
	 */
	@RequiresPermissions("train:trainCourseware:remove")
	@Log(title = "课件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(trainCoursewareService.deleteByIds(ids));
	}

}
