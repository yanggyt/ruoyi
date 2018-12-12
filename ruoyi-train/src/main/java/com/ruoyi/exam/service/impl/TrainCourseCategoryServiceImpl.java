package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TrainCourseCategoryMapper;
import com.ruoyi.exam.domain.TrainCourseCategory;
import com.ruoyi.exam.service.ITrainCourseCategoryService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 课程分类 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Service
public class TrainCourseCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCourseCategoryMapper,TrainCourseCategory> implements ITrainCourseCategoryService
{
	@Autowired
	private TrainCourseCategoryMapper trainCourseCategoryMapper;

	
	/**
     * 查询课程分类列表
     * 
     * @param trainCourseCategory 课程分类信息
     * @return 课程分类集合
     */
	@Override
	public List<TrainCourseCategory> selectTrainCourseCategoryList(TrainCourseCategory trainCourseCategory)
	{
        return trainCourseCategoryMapper.selectTrainCourseCategoryList(trainCourseCategory);
	}
    /**
     * 查询课程分类分页列表
     *
     * @param trainCourseCategory 课程分类信息
     * @return 课程分类集合
     */
    @Override
    public List<TrainCourseCategory> selectTrainCourseCategoryPage(TrainCourseCategory trainCourseCategory)
    {
        startPage();
        return trainCourseCategoryMapper.selectTrainCourseCategoryList(trainCourseCategory);
    }

}
