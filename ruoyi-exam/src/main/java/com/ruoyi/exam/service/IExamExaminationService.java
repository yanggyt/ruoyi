package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.ExamExamination;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 考试 服务层
 * 
 * @author zhujj
 * @date 2018-12-24
 */
public interface IExamExaminationService extends AbstractBaseService<ExamExamination>
{
	/**
     * 查询考试分页列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
	public List<ExamExamination> selectExamExaminationPage(ExamExamination examExamination);
    /**
     * 查询考试列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
    public List<ExamExamination> selectExamExaminationList(ExamExamination examExamination);

	
}
