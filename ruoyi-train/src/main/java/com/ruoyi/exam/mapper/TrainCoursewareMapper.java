package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCourseware;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课件 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface TrainCoursewareMapper  extends MyMapper<TrainCourseware>
{

	/**
     * 查询课件列表
     * 
     * @param trainCourseware 课件信息
     * @return 课件集合
     */
	public List<TrainCourseware> selectTrainCoursewareList(TrainCourseware trainCourseware);
	
}