package com.ruoyi.train.mapper;

import com.ruoyi.train.domain.TrainCourseSectionCourseware;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 章节课件 数据层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface TrainCourseSectionCoursewareMapper  extends MyMapper<TrainCourseSectionCourseware>
{

	/**
     * 查询章节课件列表
     * 
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
	public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewareList(TrainCourseSectionCourseware trainCourseSectionCourseware);
	
}