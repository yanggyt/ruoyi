package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionCategory;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 试题分类 服务层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface IExamQuestionCategoryService extends AbstractBaseService<ExamQuestionCategory>
{
	/**
     * 查询试题分类分页列表
     *
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
	public List<ExamQuestionCategory> selectExamQuestionCategoryPage(ExamQuestionCategory examQuestionCategory);
    /**
     * 查询试题分类列表
     *
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
    public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory);

	
}
