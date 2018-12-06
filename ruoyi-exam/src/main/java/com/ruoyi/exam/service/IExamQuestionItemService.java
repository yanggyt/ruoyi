package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamQuestionItem;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 问题选项 服务层
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public interface IExamQuestionItemService extends AbstractBaseService<ExamQuestionItem>
{
	/**
     * 查询问题选项信息
     * 
     * @param id 问题选项ID
     * @return 问题选项信息
     */
	public ExamQuestionItem selectExamQuestionItemById(String id);
	
	/**
     * 查询问题选项列表
     * 
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem);
	
	/**
     * 新增问题选项
     * 
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	public int insertExamQuestionItem(ExamQuestionItem examQuestionItem);
	
	/**
     * 修改问题选项
     * 
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	public int updateExamQuestionItem(ExamQuestionItem examQuestionItem);
		
	/**
     * 删除问题选项信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExamQuestionItemByIds(String ids);
	
}
