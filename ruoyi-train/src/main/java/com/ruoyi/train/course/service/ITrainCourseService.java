package com.ruoyi.train.service;

import com.ruoyi.train.domain.TrainCourse;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课程 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseService extends AbstractBaseService<TrainCourse>
{
	/**
     * 查询课程分页列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
	public List<TrainCourse> selectTrainCoursePage(TrainCourse trainCourse);
    /**
     * 查询课程列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
    public List<TrainCourse> selectTrainCourseList(TrainCourse trainCourse);

	
}
