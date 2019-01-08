package com.ruoyi.train.course.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-23
 */
@RestController
@RequestMapping("/api/v1")
public class ApiTrainCourseController extends BaseController {
	@Autowired
	private ITrainCourseService trainCourseService;

	@Autowired
	private ITrainCourseCategoryService trainCourseCategoryService;

	@Autowired
	private ITrainCourseSectionService trainCourseSectionService;

	/**
	 * 查询课程列表
	 */
	@GetMapping("/trainCourse/list")
	@ResponseBody
	public AjaxResult list(TrainCourse trainCourse) {
		List<TrainCourse> list = trainCourseService.selectTrainCoursePage( trainCourse );
		AjaxResult success = success( "查询成功" );
		success.put( "data", list );
		return success;
	}

	/**
	 * 查询课程详情
	 */
	@GetMapping("/trainCourse/{id}")
	public AjaxResult get(@PathVariable("id") Integer id) {
		TrainCourse trainCourse = trainCourseService.selectById( id );
		TrainCourseCategory courseCategory = trainCourseCategoryService.selectCategoryById( (long) trainCourse.getTrainCourseCategoryId() );
		JSONObject jsonObject = JSONObject.fromObject( trainCourse );
		jsonObject.put( "courseCategory", courseCategory );
		AjaxResult success = success( "查询成功" );
		success.put( "data", jsonObject );
		return success;
	}

	/**
	 * 查询课程章节详情
	 */
	@GetMapping("/trainCourse/{id}/section")
	public AjaxResult trainCourseSection(@PathVariable("id") Integer id) {
		TrainCourseSection trainCourseSection = new TrainCourseSection();
		trainCourseSection.setTrainCourseId( id );
		List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );

		AjaxResult success = success( "查询成功" );
		success.put( "data", trainCourseSections );
		return success;
	}
}
