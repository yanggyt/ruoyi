package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.TrainCourseCategory;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课程分类 服务层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface ITrainCourseCategoryService extends AbstractBaseService<TrainCourseCategory>
{
	/**
     * 查询课程分类分页列表
     *
     * @param trainCourseCategory 课程分类信息
     * @return 课程分类集合
     */
	public List<TrainCourseCategory> selectTrainCourseCategoryPage(TrainCourseCategory trainCourseCategory);
    /**
     * 查询课程分类列表
     *
     * @param trainCourseCategory 课程分类信息
     * @return 课程分类集合
     */
    public List<TrainCourseCategory> selectTrainCourseCategoryList(TrainCourseCategory trainCourseCategory);

	
}
