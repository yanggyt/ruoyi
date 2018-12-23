package com.ruoyi.train.courseware.mapper;

import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.courseware.domain.TrainCourseware;

/**
 * 课件 数据层
 *
 * @author zhujj
 * @date 2018-12-23
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