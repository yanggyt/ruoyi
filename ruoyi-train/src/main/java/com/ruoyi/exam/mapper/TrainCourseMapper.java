package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCourse;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课程 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
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