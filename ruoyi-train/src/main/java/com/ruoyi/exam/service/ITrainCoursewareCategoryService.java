package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.TrainCoursewareCategory;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 课件分类 服务层
 * 
 * @author zhujj
 * @date 2018-12-12
 */
public interface ITrainCoursewareCategoryService extends AbstractBaseService<TrainCoursewareCategory>
{
	/**
     * 查询课件分类分页列表
     *
     * @param trainCoursewareCategory 课件分类信息
     * @return 课件分类集合
     */
	public List<TrainCoursewareCategory> selectTrainCoursewareCategoryPage(TrainCoursewareCategory trainCoursewareCategory);
    /**
     * 查询课件分类列表
     *
     * @param trainCoursewareCategory 课件分类信息
     * @return 课件分类集合
     */
    public List<TrainCoursewareCategory> selectTrainCoursewareCategoryList(TrainCoursewareCategory trainCoursewareCategory);

	
}
