package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCourseSection;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课程章节 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface TrainCourseSectionMapper  extends MyMapper<TrainCourseSection>
{

	/**
     * 查询课程章节列表
     * 
     * @param trainCourseSection 课程章节信息
     * @return 课程章节集合
     */
	public List<TrainCourseSection> selectTrainCourseSectionList(TrainCourseSection trainCourseSection);
	
}