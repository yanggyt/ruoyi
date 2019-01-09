package com.ruoyi.exam.controller;

import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by flower on 2019/1/9.
 */
@Api("练习")
@RestController
@RequestMapping("/api/practice")
public class ApiPracticeController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;


    @PostMapping("/list")
    public TableDataInfo list(ExamPractice examPractice) {

        List<ExamPractice> list = examPracticeService.selectListFromWeb(examPractice);
        return getDataTable( list );
    }

}
