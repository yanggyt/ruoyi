package com.ruoyi.train.service;

import com.ruoyi.train.domain.TrainCourseSection;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课程章节 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseSectionService extends AbstractBaseService<TrainCourseSection>
{
	/**
     * 查询课程章节分页列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	public List<TrainCourseSection> selectTrainCourseSectionPage(TrainCourseSection trainCourseSection);
    /**
     * 查询课程章节列表
     *
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
    public List<TrainCourseSection> selectTrainCourseSectionList(TrainCourseSection trainCourseSection);

	
}
