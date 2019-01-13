package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.ExamUserExaminationMapper;
import com.ruoyi.exam.domain.ExamUserExamination;
import com.ruoyi.exam.service.IExamUserExaminationService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 我的考试记录 服务层实现
 * 
 * @author zhujj
 * @date 2019-01-12
 */
@Service
public class ExamUserExaminationServiceImpl extends AbstractBaseServiceImpl<ExamUserExaminationMapper,ExamUserExamination> implements IExamUserExaminationService
{
	@Autowired
	private ExamUserExaminationMapper examUserExaminationMapper;

	
	/**
     * 查询我的考试记录列表
     * 
     * @param examUserExamination 我的考试记录信息
     * @return 我的考试记录集合
     */
	@Override
	public List<ExamUserExamination> selectExamUserExaminationList(ExamUserExamination examUserExamination)
	{
        return examUserExaminationMapper.selectExamUserExaminationList(examUserExamination);
	}

    @Override
    public List<ExamUserExamination> selectLastOne(ExamUserExamination examUserExamination) {
        return examUserExaminationMapper.selectLastOne(examUserExamination);
    }

    /**
     * 查询我的考试记录分页列表
     *
     * @param examUserExamination 我的考试记录信息
     * @return 我的考试记录集合
     */
    @Override
    public List<ExamUserExamination> selectExamUserExaminationPage(ExamUserExamination examUserExamination)
    {
        startPage();
        return examUserExaminationMapper.selectExamUserExaminationList(examUserExamination);
    }

}
