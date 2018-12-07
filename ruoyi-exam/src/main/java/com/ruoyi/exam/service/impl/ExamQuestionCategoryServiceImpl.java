package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionCategoryMapper;
import com.ruoyi.exam.domain.ExamQuestionCategory;
import com.ruoyi.exam.service.IExamQuestionCategoryService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 试题分类 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Service
public class ExamQuestionCategoryServiceImpl extends AbstractBaseServiceImpl<ExamQuestionCategoryMapper,ExamQuestionCategory> implements IExamQuestionCategoryService
{
	@Autowired
	private ExamQuestionCategoryMapper examQuestionCategoryMapper;

	
	/**
     * 查询试题分类列表
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
	@Override
	public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory)
	{
        return examQuestionCategoryMapper.selectExamQuestionCategoryList(examQuestionCategory);
	}
    /**
     * 查询试题分类分页列表
     *
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
    @Override
    public List<ExamQuestionCategory> selectExamQuestionCategoryPage(ExamQuestionCategory examQuestionCategory)
    {
        startPage();
        return examQuestionCategoryMapper.selectExamQuestionCategoryList(examQuestionCategory);
    }

}
