package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCoursewareCategory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课件分类 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface TrainCoursewareCategoryMapper  extends MyMapper<TrainCoursewareCategory>
{

	/**
     * 查询课件分类列表
     * 
     * @param trainCoursewareCategory 课件分类信息
     * @return 课件分类集合
     */
	public List<TrainCoursewareCategory> selectTrainCoursewareCategoryList(TrainCoursewareCategory trainCoursewareCategory);
	
}