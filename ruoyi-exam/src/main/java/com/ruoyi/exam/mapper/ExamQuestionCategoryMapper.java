package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestionCategory;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 试题分类 数据层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface ExamQuestionCategoryMapper  extends MyMapper<ExamQuestionCategory>
{

	/**
     * 查询试题分类列表
     * 
     * @param examQuestionCategory 试题分类信息
     * @return 试题分类集合
     */
	public List<ExamQuestionCategory> selectExamQuestionCategoryList(ExamQuestionCategory examQuestionCategory);
	
}