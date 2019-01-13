package com.ruoyi.exam.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamExaminationMapper;
import com.ruoyi.exam.domain.ExamExamination;
import com.ruoyi.exam.service.IExamExaminationService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 考试 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-24
 */
@Service
public class ExamExaminationServiceImpl extends AbstractBaseServiceImpl<ExamExaminationMapper,ExamExamination> implements IExamExaminationService
{
	@Autowired
	private ExamExaminationMapper examExaminationMapper;

	
	/**
     * 查询考试列表
     * 
     * @param examExamination 考试信息
     * @return 考试集合
     */
	@Override
	public List<ExamExamination> selectExamExaminationList(ExamExamination examExamination)
	{
        return examExaminationMapper.selectExamExaminationList(examExamination);
	}

    @Override
    public List<ExamExamination> selectListFromWeb(Map<String, Object> map) {
        startPage();
	    return examExaminationMapper.selectListFromWeb(map);
    }

    @Override
    public List<ExamExamination> selectEnterNameListFromWeb(Map<String, Object> map) {
        startPage();
        return examExaminationMapper.selectEnterNameListFromWeb(map);
    }

    /**
     * 查询考试分页列表
     *
     * @param examExamination 考试信息
     * @return 考试集合
     */
    @Override
    public List<ExamExamination> selectExamExaminationPage(ExamExamination examExamination)
    {
        startPage();
        return examExaminationMapper.selectExamExaminationList(examExamination);
    }

}
