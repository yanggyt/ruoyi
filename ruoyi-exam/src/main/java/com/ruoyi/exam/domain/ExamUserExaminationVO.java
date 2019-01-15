package com.ruoyi.exam.domain;

import java.util.List;

/**
 * Created by flower on 2019/1/14.
 */
public class ExamUserExaminationVO extends ExamUserExamination{

    private ExamExaminationVO examExaminationVO;

    private String userName;

    private String examPaperName;

    private List<ExamUserExaminationQuestionVO> examUserExaminationQuestions;

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public ExamExaminationVO getExamExaminationVO() {
        return examExaminationVO;
    }

    public void setExamExaminationVO(ExamExaminationVO examExaminationVO) {
        this.examExaminationVO = examExaminationVO;
    }

    public List<ExamUserExaminationQuestionVO> getExamUserExaminationQuestions() {
        return examUserExaminationQuestions;
    }

    public void setExamUserExaminationQuestions(List<ExamUserExaminationQuestionVO> examUserExaminationQuestions) {
        this.examUserExaminationQuestions = examUserExaminationQuestions;
    }
}
