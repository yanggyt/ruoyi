package com.ruoyi.exam.domain;

import java.util.List;

/**
 * Created by flower on 2019/1/10.
 */
public class ExamQuestionVO extends ExamQuestion{
    private List<ExamQuestionItem> questionItem;

    public List<ExamQuestionItem> getQuestionItem() {
        return questionItem;
    }

    public void setQuestionItem(List<ExamQuestionItem> questionItem) {
        this.questionItem = questionItem;
    }
}
