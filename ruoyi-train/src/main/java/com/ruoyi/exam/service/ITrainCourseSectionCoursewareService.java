package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.TrainCourseSectionCourseware;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 章节课件 服务层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface ITrainCourseSectionCoursewareService extends AbstractBaseService<TrainCourseSectionCourseware>
{
	/**
     * 查询章节课件分页列表
     *
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
	public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewarePage(TrainCourseSectionCourseware trainCourseSectionCourseware);
    /**
     * 查询章节课件列表
     *
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
    public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewareList(TrainCourseSectionCourseware trainCourseSectionCourseware);

	
}
