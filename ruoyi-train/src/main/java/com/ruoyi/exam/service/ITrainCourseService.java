package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.TrainCourse;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课程 服务层
 * 
 * @author zhujj
 * @date 2018-12-12
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
