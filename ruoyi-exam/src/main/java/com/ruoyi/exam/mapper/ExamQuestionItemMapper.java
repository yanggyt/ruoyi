package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.ExamQuestionItem;
import java.util.List;
import com.ruoyi.framework.web.base.MyMapper;

/**
 * 问题选项 数据层
 * 
 * @author zhujj
 * @date 2018-12-07
 */
public interface ExamQuestionItemMapper  extends MyMapper<ExamQuestionItem>
{

	/**
     * 查询问题选项列表
     * 
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem);
	
}