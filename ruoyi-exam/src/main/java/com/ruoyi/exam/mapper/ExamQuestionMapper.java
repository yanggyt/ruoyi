package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestion;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 问题 数据层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface ExamQuestionMapper  extends MyMapper<ExamQuestion>
{
	/**
     * 查询问题信息
     *
     * @param id 问题ID
     * @return 问题信息
     */
	public ExamQuestion selectExamQuestionById(String id);

	/**
     * 查询问题列表
     * 
     * @param examQuestion 问题信息
     * @return 问题集合
     */
	public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion);
	
	/**
     * 新增问题
     *
     * @param examQuestion 问题信息
     * @return 结果
     */
	public int insertExamQuestion(ExamQuestion examQuestion);

	/**
     * 修改问题
     *
     * @param examQuestion 问题信息
     * @return 结果
     */
	public int updateExamQuestion(ExamQuestion examQuestion);

	/**
     * 删除问题
     *
     * @param id 问题ID
     * @return 结果
     */
	public int deleteExamQuestionById(String id);

	/**
     * 批量删除问题
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionByIds(String[] ids);

    List<ExamQuestion> selectListBycategory(ExamQuestion examQuestion);
}