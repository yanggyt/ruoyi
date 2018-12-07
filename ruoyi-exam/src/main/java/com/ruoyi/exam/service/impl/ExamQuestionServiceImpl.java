package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionMapper;
import com.ruoyi.exam.domain.ExamQuestion;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 问题 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-07
 */
@Service
public class ExamQuestionServiceImpl extends AbstractBaseServiceImpl<ExamQuestionMapper,ExamQuestion> implements IExamQuestionService
{
	@Autowired
	private ExamQuestionMapper examQuestionMapper;

	
	/**
     * 查询问题列表
     * 
     * @param examQuestion 问题信息
     * @return 问题集合
     */
	@Override
	public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion)
	{
        return examQuestionMapper.selectExamQuestionList(examQuestion);
	}
    /**
     * 查询问题分页列表
     *
     * @param examQuestion 问题信息
     * @return 问题集合
     */
    @Override
    public List<ExamQuestion> selectExamQuestionPage(ExamQuestion examQuestion)
    {
        startPage();
        return examQuestionMapper.selectExamQuestionList(examQuestion);
    }

}
