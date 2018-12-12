package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TrainCoursewareCategoryMapper;
import com.ruoyi.exam.domain.TrainCoursewareCategory;
import com.ruoyi.exam.service.ITrainCoursewareCategoryService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 课件分类 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-12
 */
@Service
public class TrainCoursewareCategoryServiceImpl extends AbstractBaseServiceImpl<TrainCoursewareCategoryMapper,TrainCoursewareCategory> implements ITrainCoursewareCategoryService
{
	@Autowired
	private TrainCoursewareCategoryMapper trainCoursewareCategoryMapper;

	
	/**
     * 查询课件分类列表
     * 
     * @param trainCoursewareCategory 课件分类信息
     * @return 课件分类集合
     */
	@Override
	public List<TrainCoursewareCategory> selectTrainCoursewareCategoryList(TrainCoursewareCategory trainCoursewareCategory)
	{
        return trainCoursewareCategoryMapper.selectTrainCoursewareCategoryList(trainCoursewareCategory);
	}
    /**
     * 查询课件分类分页列表
     *
     * @param trainCoursewareCategory 课件分类信息
     * @return 课件分类集合
     */
    @Override
    public List<TrainCoursewareCategory> selectTrainCoursewareCategoryPage(TrainCoursewareCategory trainCoursewareCategory)
    {
        startPage();
        return trainCoursewareCategoryMapper.selectTrainCoursewareCategoryList(trainCoursewareCategory);
    }

}
