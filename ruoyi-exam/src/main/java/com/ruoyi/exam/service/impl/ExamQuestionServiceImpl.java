package com.ruoyi.exam.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.exam.domain.ExamQuestionItem;
import com.ruoyi.exam.mapper.ExamQuestionItemMapper;
import com.ruoyi.framework.web.util.ShiroUtils;
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
 * @date 2018-12-06
 */
@Service
public class ExamQuestionServiceImpl extends AbstractBaseServiceImpl<ExamQuestionMapper,ExamQuestion> implements IExamQuestionService
{
	@Autowired
	private ExamQuestionMapper examQuestionMapper;

	@Autowired
	private ExamQuestionItemMapper examQuestionItemMapper;

	/**
	 * 查询问题信息
	 *
	 * @param id 问题ID
	 * @return 问题信息
	 */
	@Override
	public ExamQuestion selectExamQuestionById(String id)
	{
		return examQuestionMapper.selectExamQuestionById(id);
	}

	/**
	 * 查询问题列表
	 *
	 * @param examQuestion 问题信息
	 * @return 问题集合
	 */
	@Override
	public List<ExamQuestion> selectExamQuestionList(ExamQuestion examQuestion)
	{
		startPage();
		return examQuestionMapper.selectExamQuestionList(examQuestion);
	}

	/**
	 * 新增问题
	 *
	 * @param examQuestion 问题信息
	 * @return 结果
	 */
	@Override
	public int insertExamQuestion(ExamQuestion examQuestion)
	{
		return examQuestionMapper.insertExamQuestion(examQuestion);
	}

	/**
	 * 修改问题
	 *
	 * @param examQuestion 问题信息
	 * @return 结果
	 */
	@Override
	public int updateExamQuestion(ExamQuestion examQuestion)
	{
		return examQuestionMapper.updateExamQuestion(examQuestion);
	}

	/**
	 * 删除问题对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteExamQuestionByIds(String ids)
	{
		return examQuestionMapper.deleteExamQuestionByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertQuestion(ExamQuestion examQuestion, String[] number, String[] content) {
		Date date = new Date();
		examQuestion.setCreateDate(date);
		examQuestion.setCreateBy(ShiroUtils.getLoginName());
		int i = examQuestionMapper.insertExamQuestion(examQuestion);
		examQuestion.setCreateDate(null);
		List<ExamQuestion> select = examQuestionMapper.selectExamQuestionList(examQuestion);
		ExamQuestionItem examQuestionItem = new ExamQuestionItem();
		for (int i1 = 0; i1 < number.length; i1++) {examQuestionItem.setContent(content[i1]);
			examQuestionItem.setNumber(number[i1]);
			examQuestionItem.setExamQuestionId(select.get(0).getId()+"");
			examQuestionItem.setCreateDate(date);
			examQuestionItem.setCreateBy(ShiroUtils.getLoginName());
			examQuestionItemMapper.insertExamQuestionItem(examQuestionItem);


		}


		return i ;
	}

}
