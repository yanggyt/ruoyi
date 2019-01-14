package com.ruoyi.exam.domain;

/**
 * Created by flower on 2019/1/14.
 */
public class ExamUserExaminationVO extends ExamUserExamination{

    private ExamExaminationVO examExaminationVO;

    public ExamExaminationVO getExamExaminationVO() {
        return examExaminationVO;
    }

    public void setExamExaminationVO(ExamExaminationVO examExaminationVO) {
        this.examExaminationVO = examExaminationVO;
    }
}
