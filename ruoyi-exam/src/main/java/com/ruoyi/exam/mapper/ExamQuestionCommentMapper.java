package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestionComment;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 问题点评 数据层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface ExamQuestionCommentMapper  extends MyMapper<ExamQuestionComment>
{

	/**
     * 查询问题点评列表
     * 
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
	public List<ExamQuestionComment> selectExamQuestionCommentList(ExamQuestionComment examQuestionComment);
	
}