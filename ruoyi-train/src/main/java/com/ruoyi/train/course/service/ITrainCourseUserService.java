package com.ruoyi.train.service;

import com.ruoyi.train.domain.TrainCourseUser;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课程使用对象 服务层
 * 
 * @author zhujj
 * @date 2018-12-23
 */
public interface ITrainCourseUserService extends AbstractBaseService<TrainCourseUser>
{
	/**
     * 查询课程使用对象分页列表
     *
     * @param trainCourseUser 课程使用对象信息
     * @return 课程使用对象集合
     */
	public List<TrainCourseUser> selectTrainCourseUserPage(TrainCourseUser trainCourseUser);
    /**
     * 查询课程使用对象列表
     *
     * @param trainCourseUser 课程使用对象信息
     * @return 课程使用对象集合
     */
    public List<TrainCourseUser> selectTrainCourseUserList(TrainCourseUser trainCourseUser);

	
}
