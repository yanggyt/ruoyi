package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestion;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 问题 数据层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface ExamQuestionMapper  extends MyMapper<ExamQuestion>
{

	/**
     * 查询问题列表
     * 
     * @param examQuestion 问题信息
     * @return 问题集合
     */
	public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion);
	
}