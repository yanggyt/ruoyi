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
 * @date 2018-12-06
 */
@Service
public class ExamQuestionCategoryServiceImpl extends AbstractBaseServiceImpl<ExamQuestionCategoryMapper,ExamQuestionCategory> implements IExamQuestionCategoryService
{
	@Autowired
	private ExamQuestionCategoryMapper examQuestionCategoryMapper;

	/**
     * 查询试题分类信息
     * 
     * @param id 试题分类ID
     * @return 试题分类信息
     */
    @Override
	public ExamQuestionCategory selectExamQuestionCategoryById(String id)
	{
	    return examQuestionCategoryMapper.selectExamQuestionCategoryById(id);
	}
	
	/**
     * 查询试题分类列表
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
	@Override
	public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory)
	{
		startPage();
	    return examQuestionCategoryMapper.selectExamQuestionCategoryList(examQuestionCategory);
	}
	
    /**
     * 新增试题分类
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
	@Override
	public int insertExamQuestionCategory(ExamQuestionCategory examQuestionCategory)
	{
	    return examQuestionCategoryMapper.insertExamQuestionCategory(examQuestionCategory);
	}
	
	/**
     * 修改试题分类
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 结果
     */
	@Override
	public int updateExamQuestionCategory(ExamQuestionCategory examQuestionCategory)
	{
	    return examQuestionCategoryMapper.updateExamQuestionCategory(examQuestionCategory);
	}

	/**
     * 删除试题分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExamQuestionCategoryByIds(String ids)
	{
		return examQuestionCategoryMapper.deleteExamQuestionCategoryByIds(Convert.toStrArray(ids));
	}
	
}
