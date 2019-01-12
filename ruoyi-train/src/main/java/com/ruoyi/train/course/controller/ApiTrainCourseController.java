package com.ruoyi.train.course.controller;

import cn.hutool.json.JSONObject;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseCategory;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
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
	public AjaxResult list( TrainCourse trainCourse) {
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
		AjaxResult success = success( "查询成功" );
		success.put( "data", trainCourse );
		return success;
	}

	/**
	 * 查询课程章节列表详情
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
	/**
	 * 查询课程章节列表详情
	 */
	@GetMapping("/trainCourse/section/{id}")
	public AjaxResult trainCourseSectionInfo(@PathVariable("id") Integer id) {
		TrainCourseSection trainCourseSections = trainCourseSectionService.selectById( id );

		AjaxResult success = success( "查询成功" );
		success.put( "data", trainCourseSections );
		return success;
	}
}
