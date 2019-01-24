package com.ruoyi.train.course.mapper;


import com.ruoyi.framework.web.base.MyMapper;
import com.ruoyi.train.course.domain.TrainCourseUser;

import java.util.List;

/**
 * 课程使用对象 数据层
 * 
 * @author zhujj
 * @date 2018-12-23
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

	/**
	 * 判断是否有权限
	 * @param userId
	 * @param trainCourseId
	 * @return
	 */
    List<TrainCourseUser> authority(Long userId, Integer trainCourseId,Integer days);
}