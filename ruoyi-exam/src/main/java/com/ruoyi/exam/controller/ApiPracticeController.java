package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.exam.service.IExamUserErrorQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ShiroUtils;
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

    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;


    @GetMapping("/list")
    public AjaxResult list(ExamPractice examPractice) {

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
    @GetMapping("/info")
    public AjaxResult queryOne(@RequestParam Map<String,Object> map) {
        List<ExamQuestionVO> result = examQuestionService.selectQuestionListByPracticeId(map);
        if(map.containsKey("disorder")&&map.get("disorder").toString().equals("1")){
            Collections.shuffle(result);
        }
        AjaxResult success = success("查询成功");
        success.put("data",result);
        return success;
    }

    /**
     * 回答问题
     * @param answers
     * @return
     */
    @PostMapping("/answer")
    public AjaxResult answer(@RequestBody List<Map<String,Object>> answers) {
        int error = 0;
        for (Map<String, Object> answer : answers) {
            String questionId = answer.get("questionId").toString();
            String userAnswer = answer.get("userAnswer").toString();
            ExamQuestion examQuestion = examQuestionService.selectById(questionId);
            if(!examQuestion.getAnswer().equals(userAnswer)){
                ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
                examUserErrorQuestion.setExamQuestionId(Integer.parseInt(questionId));
                examUserErrorQuestion.setVipUserId(Integer.parseInt(ShiroUtils.getUserId().toString()));
                examUserErrorQuestion.setCreateBy(ShiroUtils.getLoginName());
                examUserErrorQuestion.setCreateDate(new Date());
                examUserErrorQuestion.setDelFlag("0");
                examUserErrorQuestionService.insert(examUserErrorQuestion);
                error++;
            }
        }
        AjaxResult success = success(error+"题进入错题本");
        return success;
    }

    /**
     * 查询错题本列表
     * @return
     */
    @GetMapping("/queryerror")
    public AjaxResult answer() {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        examUserErrorQuestion.setVipUserId(Integer.parseInt(ShiroUtils.getUserId().toString()));
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailPage(examUserErrorQuestion);
        AjaxResult success = success("查询成功");
        success.put("data",list);
        return success;
    }

    /**
     * 查询问题详情
     * @param questionId
     * @return
     */
    @GetMapping("/queryquestion/{id}")
    public AjaxResult queryQuestion(@PathVariable("id") String questionId) {
        ExamQuestionVO result = examQuestionService.selectQuestionDetail(questionId);
        AjaxResult success = success("查询成功");
        success.put("data",result);
        return success;
    }

}
