package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionComment;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题点评 服务层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface IExamQuestionCommentService extends AbstractBaseService<ExamQuestionComment>
{
	/**
     * 查询问题点评信息
     * 
     * @param id 问题点评ID
     * @return 问题点评信息
     */
	public ExamQuestionComment selectExamQuestionCommentById(String id);
	
	/**
     * 查询问题点评列表
     * 
     * @param examQuestionComment 问题点评信息
     * @return 问题点评集合
     */
	public List<ExamQuestionComment> selectExamQuestionCommentList(ExamQuestionComment examQuestionComment);
	
	/**
     * 新增问题点评
     * 
     * @param examQuestionComment 问题点评信息
     * @return 结果
     */
	public int insertExamQuestionComment(ExamQuestionComment examQuestionComment);
	
	/**
     * 修改问题点评
     * 
     * @param examQuestionComment 问题点评信息
     * @return 结果
     */
	public int updateExamQuestionComment(ExamQuestionComment examQuestionComment);
		
	/**
     * 删除问题点评信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionCommentByIds(String ids);
	
}
