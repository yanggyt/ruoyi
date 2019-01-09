package com.ruoyi.exam.service.impl;

import java.util.List;

import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamUserErrorQuestionMapper;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.service.IExamUserErrorQuestionService;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的错题 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-10
 */
@Service
public class ExamUserErrorQuestionServiceImpl extends AbstractBaseServiceImpl<ExamUserErrorQuestionMapper,ExamUserErrorQuestion> implements IExamUserErrorQuestionService
{
	@Autowired
	private ExamUserErrorQuestionMapper examUserErrorQuestionMapper;

	
	/**
     * 查询我的错题列表
     * 
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
	@Override
	public List<ExamUserErrorQuestion> selectExamUserErrorQuestionList(ExamUserErrorQuestion examUserErrorQuestion)
	{
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionList(examUserErrorQuestion);
	}

    @Override
    public List<ExamUserErrorQuestionVO> selectExamUserErrorQuestionDetailPage(ExamUserErrorQuestion examUserErrorQuestion) {
        startPage();
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionDetailPage(examUserErrorQuestion);
    }

    /**
     * 查询我的错题分页列表
     *
     * @param examUserErrorQuestion 我的错题信息
     * @return 我的错题集合
     */
    @Override
    public List<ExamUserErrorQuestion> selectExamUserErrorQuestionPage(ExamUserErrorQuestion examUserErrorQuestion)
    {
        startPage();
        return examUserErrorQuestionMapper.selectExamUserErrorQuestionList(examUserErrorQuestion);
    }

}
