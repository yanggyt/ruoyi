package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCourseCategory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课程分类 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface TrainCourseCategoryMapper  extends MyMapper<TrainCourseCategory>
{

	/**
     * 查询课程分类列表
     * 
     * @param trainCourseCategory 课程分类信息
     * @return 课程分类集合
     */
	public List<TrainCourseCategory> selectTrainCourseCategoryList(TrainCourseCategory trainCourseCategory);
	
}