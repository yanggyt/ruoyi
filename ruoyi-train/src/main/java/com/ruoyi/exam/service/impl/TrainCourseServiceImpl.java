package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TrainCourseMapper;
import com.ruoyi.exam.domain.TrainCourse;
import com.ruoyi.exam.service.ITrainCourseService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 课程 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Service
public class TrainCourseServiceImpl extends AbstractBaseServiceImpl<TrainCourseMapper,TrainCourse> implements ITrainCourseService
{
	@Autowired
	private TrainCourseMapper trainCourseMapper;

	
	/**
     * 查询课程列表
     * 
     * @param trainCourse 课程信息
     * @return 课程集合
     */
	@Override
	public List<TrainCourse> selectTrainCourseList(TrainCourse trainCourse)
	{
        return trainCourseMapper.selectTrainCourseList(trainCourse);
	}
    /**
     * 查询课程分页列表
     *
     * @param trainCourse 课程信息
     * @return 课程集合
     */
    @Override
    public List<TrainCourse> selectTrainCoursePage(TrainCourse trainCourse)
    {
        startPage();
        return trainCourseMapper.selectTrainCourseList(trainCourse);
    }

}
