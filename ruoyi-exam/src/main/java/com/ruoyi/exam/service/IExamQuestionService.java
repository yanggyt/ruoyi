package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestion;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题 服务层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface IExamQuestionService extends AbstractBaseService<ExamQuestion>
{
	/**
     * 查询问题分页列表
     *
     * @param examQuestion 问题信息
     * @return 问题集合
     */
	public List<ExamQuestion> selectExamQuestionPage(ExamQuestion examQuestion);
    /**
     * 查询问题列表
     *
     * @param examQuestion 问题信息
     * @return 问题集合
     */
    public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion);

	
}
