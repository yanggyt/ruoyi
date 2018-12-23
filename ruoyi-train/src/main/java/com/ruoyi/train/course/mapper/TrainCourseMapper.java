package com.ruoyi.train.mapper;

import com.ruoyi.train.domain.TrainCourse;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

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