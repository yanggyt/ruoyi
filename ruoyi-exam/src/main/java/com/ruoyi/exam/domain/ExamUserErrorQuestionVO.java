package com.ruoyi.exam.domain;

/**
 * Created by flower on 2019/1/10.
 */
public class ExamUserErrorQuestionVO extends ExamUserErrorQuestion {
    private ExamQuestion question;

    public ExamQuestion getQuestion() {
        return question;
    }

    public void setQuestion(ExamQuestion question) {
        this.question = question;
    }
}
