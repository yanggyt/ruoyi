package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.TrainCourseware;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课件 服务层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface ITrainCoursewareService extends AbstractBaseService<TrainCourseware>
{
	/**
     * 查询课件分页列表
     *
     * @param trainCourseware 课件信息
     * @return 课件集合
     */
	public List<TrainCourseware> selectTrainCoursewarePage(TrainCourseware trainCourseware);
    /**
     * 查询课件列表
     *
     * @param trainCourseware 课件信息
     * @return 课件集合
     */
    public List<TrainCourseware> selectTrainCoursewareList(TrainCourseware trainCourseware);

	
}
