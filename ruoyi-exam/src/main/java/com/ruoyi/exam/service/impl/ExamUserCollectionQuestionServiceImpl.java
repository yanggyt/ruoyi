package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamUserCollectionQuestionMapper;
import com.ruoyi.exam.domain.ExamUserCollectionQuestion;
import com.ruoyi.exam.service.IExamUserCollectionQuestionService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的收藏 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-16
 */
@Service
public class ExamUserCollectionQuestionServiceImpl extends AbstractBaseServiceImpl<ExamUserCollectionQuestionMapper,ExamUserCollectionQuestion> implements IExamUserCollectionQuestionService
{
	@Autowired
	private ExamUserCollectionQuestionMapper examUserCollectionQuestionMapper;

	
	/**
     * 查询我的收藏列表
     * 
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
	@Override
	public List<ExamUserCollectionQuestion> selectExamUserCollectionQuestionList(ExamUserCollectionQuestion examUserCollectionQuestion)
	{
        return examUserCollectionQuestionMapper.selectExamUserCollectionQuestionList(examUserCollectionQuestion);
	}

    @Override
    public int insertSelectiveBySelf(ExamUserCollectionQuestion examUserCollectionQuestion) {
	    return examUserCollectionQuestionMapper.insertSelective(examUserCollectionQuestion);
    }

    /**
     * 查询我的收藏分页列表
     *
     * @param examUserCollectionQuestion 我的收藏信息
     * @return 我的收藏集合
     */
    @Override
    public List<ExamUserCollectionQuestion> selectExamUserCollectionQuestionPage(ExamUserCollectionQuestion examUserCollectionQuestion)
    {
        startPage();
        return examUserCollectionQuestionMapper.selectExamUserCollectionQuestionList(examUserCollectionQuestion);
    }

}
