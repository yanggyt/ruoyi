package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import java.util.List;

import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 我的错题 服务层
 * 
 * @author zhujj
 * @date 2019-01-10
 */
public interface IExamUserErrorQuestionService extends AbstractBaseService<ExamUserErrorQuestion>
{
	/**
     * 查询我的错题分页列表
     *
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
	public List<ExamUserErrorQuestion> selectExamUserErrorQuestionPage(ExamUserErrorQuestion examUserErrorQuestion);
    /**
     * 查询我的错题列表
     *
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
    public List<ExamUserErrorQuestion> selectExamUserErrorQuestionList(ExamUserErrorQuestion examUserErrorQuestion);


    List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailPage(ExamUserErrorQuestion examUserErrorQuestion);

    int insertError(ExamUserErrorQuestion examUserErrorQuestion);
}
