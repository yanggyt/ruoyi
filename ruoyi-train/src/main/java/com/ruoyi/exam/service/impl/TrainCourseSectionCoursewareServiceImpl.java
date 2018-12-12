package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TrainCourseSectionCoursewareMapper;
import com.ruoyi.exam.domain.TrainCourseSectionCourseware;
import com.ruoyi.exam.service.ITrainCourseSectionCoursewareService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 章节课件 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Service
public class TrainCourseSectionCoursewareServiceImpl extends AbstractBaseServiceImpl<TrainCourseSectionCoursewareMapper,TrainCourseSectionCourseware> implements ITrainCourseSectionCoursewareService
{
	@Autowired
	private TrainCourseSectionCoursewareMapper trainCourseSectionCoursewareMapper;

	
	/**
     * 查询章节课件列表
     * 
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
	@Override
	public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewareList(TrainCourseSectionCourseware trainCourseSectionCourseware)
	{
        return trainCourseSectionCoursewareMapper.selectTrainCourseSectionCoursewareList(trainCourseSectionCourseware);
	}
    /**
     * 查询章节课件分页列表
     *
     * @param trainCourseSectionCourseware 章节课件信息
     * @return 章节课件集合
     */
    @Override
    public List<TrainCourseSectionCourseware> selectTrainCourseSectionCoursewarePage(TrainCourseSectionCourseware trainCourseSectionCourseware)
    {
        startPage();
        return trainCourseSectionCoursewareMapper.selectTrainCourseSectionCoursewareList(trainCourseSectionCourseware);
    }

}
