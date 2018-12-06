package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamQuestionItemMapper;
import com.ruoyi.exam.domain.ExamQuestionItem;
import com.ruoyi.exam.service.IExamQuestionItemService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 问题选项 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-06
 */
@Service
public class ExamQuestionItemServiceImpl extends AbstractBaseServiceImpl<ExamQuestionItemMapper,ExamQuestionItem> implements IExamQuestionItemService
{
	@Autowired
	private ExamQuestionItemMapper examQuestionItemMapper;

	/**
     * 查询问题选项信息
     * 
     * @param id 问题选项ID
     * @return 问题选项信息
     */
    @Override
	public ExamQuestionItem selectExamQuestionItemById(String id)
	{
	    return examQuestionItemMapper.selectExamQuestionItemById(id);
	}
	
	/**
     * 查询问题选项列表
     * 
     * @param examQuestionItem 问题选项信息
     * @return 问题选项集合
     */
	@Override
	public List<ExamQuestionItem> selectExamQuestionItemList(ExamQuestionItem examQuestionItem)
	{
	    return examQuestionItemMapper.selectExamQuestionItemList(examQuestionItem);
	}
	
    /**
     * 新增问题选项
     * 
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	@Override
	public int insertExamQuestionItem(ExamQuestionItem examQuestionItem)
	{
	    return examQuestionItemMapper.insertExamQuestionItem(examQuestionItem);
	}
	
	/**
     * 修改问题选项
     * 
     * @param examQuestionItem 问题选项信息
     * @return 结果
     */
	@Override
	public int updateExamQuestionItem(ExamQuestionItem examQuestionItem)
	{
	    return examQuestionItemMapper.updateExamQuestionItem(examQuestionItem);
	}

	/**
     * 删除问题选项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExamQuestionItemByIds(String ids)
	{
		return examQuestionItemMapper.deleteExamQuestionItemByIds(Convert.toStrArray(ids));
	}
	
}
