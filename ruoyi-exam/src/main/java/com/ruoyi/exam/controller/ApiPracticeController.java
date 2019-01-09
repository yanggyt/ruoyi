package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public AjaxResult list(@RequestBody  ExamPractice examPractice) {

        List<ExamPractice> list = examPracticeService.selectListFromWeb(examPractice);
        AjaxResult success = success("查询成功");
        success.put("data",list);
        return success;
    }

    /**
     * 查询练习具体的问题列表
     * @param map
     * @return
     */
    @PostMapping("/queryone/question")
    public AjaxResult queryOne(@RequestParam Map<String,Object> map) {
        List<ExamQuestionVO> result = examQuestionService.selectQuestionListByPracticeId(map);
        if(map.containsKey("disorder")&&map.get("disorder").toString().equals("1")){
            Collections.shuffle(result);
        }
        AjaxResult success = success("查询成功");
        success.put("data",result);
        return success;
    }

    @PostMapping("/answer")
    public AjaxResult answer(@RequestBody List<Map<String,Object>> answers) {
        for (Map<String, Object> answer : answers) {
            String questionId = answer.get("questionId").toString();
            String userAnswer = answer.get("userAnswer").toString();
            ExamQuestion examQuestion = examQuestionService.selectById(questionId);
            if(!examQuestion.getAnswer().equals(userAnswer)){

            }
        }
        AjaxResult success = success("查询成功");
        return success;
    }

}
