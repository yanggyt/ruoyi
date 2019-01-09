package com.ruoyi.exam.controller;

import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.domain.ExamPracticeQuestion;
import com.ruoyi.exam.domain.ExamPracticeQuestionVO;
import com.ruoyi.exam.domain.ExamQuestion;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flower on 2019/1/9.
 */
@Api("练习")
@RestController
@RequestMapping("/api/v1/practice")
public class ApiPracticeController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;

    @Autowired
    private IExamPracticeQuestionService examPracticeQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;


    @PostMapping("/list")
    public TableDataInfo list(ExamPractice examPractice) {

        List<ExamPractice> list = examPracticeService.selectListFromWeb(examPractice);
        return getDataTable( list );
    }

    @GetMapping("/queryone/question/{id}")
    public TableDataInfo queryOne(@PathVariable("id") Integer id) {
        ExamPracticeQuestionVO examPracticeQuestion = new ExamPracticeQuestionVO();
        examPracticeQuestion.setId(id);
        List<ExamPracticeQuestionVO> list = examPracticeQuestionService.selectExamPracticeQuestionList(examPracticeQuestion);
        List<String> ids = new ArrayList<>();
        for (ExamPracticeQuestionVO item : list) {
            ids.add(item.getExamQuestionId().toString());
        }
        List<ExamQuestion> result = examQuestionService.selectByIdsPage(ids);
        return getDataTable( result );
    }

}
