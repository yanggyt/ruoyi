package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionCommentMapper;
import com.ruoyi.exam.domain.ExamQuestionComment;
import com.ruoyi.exam.service.IExamQuestionCommentService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 问题点评 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-06
 */
@Service
public class ExamQuestionCommentServiceImpl extends AbstractBaseServiceImpl<ExamQuestionCommentMapper,ExamQuestionComment> implements IExamQuestionCommentService
{
	@Autowired
	private ExamQuestionCommentMapper examQuestionCommentMapper;

	/**
     * 查询问题点评信息
     * 
     * @param id 问题点评ID
     * @return 问题点评信息
     */
    @Override
	public ExamQuestionComment selectExamQuestionCommentById(String id)
	{
	    return examQuestionCommentMapper.selectExamQuestionCommentById(id);
	}
	
	/**
     * 查询问题点评列表
     * 
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
	@Override
	public List<ExamQuestionComment> selectExamQuestionCommentList(ExamQuestionComment examQuestionComment)
	{
		startPage();
	    return examQuestionCommentMapper.selectExamQuestionCommentList(examQuestionComment);
	}
	
    /**
     * 新增问题点评
     * 
     * @param examQuestionComment 问题点评信息
     * @return 结果
     */
	@Override
	public int insertExamQuestionComment(ExamQuestionComment examQuestionComment)
	{
	    return examQuestionCommentMapper.insertExamQuestionComment(examQuestionComment);
	}
	
	/**
     * 修改问题点评
     * 
     * @param examQuestionComment 问题点评信息
     * @return 结果
     */
	@Override
	public int updateExamQuestionComment(ExamQuestionComment examQuestionComment)
	{
	    return examQuestionCommentMapper.updateExamQuestionComment(examQuestionComment);
	}

	/**
     * 删除问题点评对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExamQuestionCommentByIds(String ids)
	{
		return examQuestionCommentMapper.deleteExamQuestionCommentByIds(Convert.toStrArray(ids));
	}
	
}
