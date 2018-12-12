package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TrainCourseUser;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 课程使用对象 数据层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface TrainCourseUserMapper  extends MyMapper<TrainCourseUser>
{

	/**
     * 查询课程使用对象列表
     * 
     * @param trainCourseUser 课程使用对象信息
     * @return 课程使用对象集合
     */
	public List<TrainCourseUser> selectTrainCourseUserList(TrainCourseUser trainCourseUser);
	
}