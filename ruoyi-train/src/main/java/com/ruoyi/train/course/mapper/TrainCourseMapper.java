package com.ruoyi.train.course.mapper;


import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourse;

import java.util.List;

/**
 * 课程 数据层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface TrainCourseMapper  extends MyMapper<TrainCourse>
{

	/**
     * 查询课程列表
     * 
     * @param trainCourse 课程信息
     * @return 课程集合
     */
	public List<TrainCourse> selectTrainCourseList(TrainCourse trainCourse);
	
}