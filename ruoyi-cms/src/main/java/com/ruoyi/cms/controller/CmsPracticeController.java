package com.ruoyi.cms.controller;

import com.ruoyi.exam.domain.ExamQuestionVO;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by flower on 2019/1/18.
 */
@Controller
@RequestMapping("/web")
public class CmsPracticeController {

    @Autowired
    private IExamPracticeService examPracticeService;


    @Autowired
    private IExamQuestionService examQuestionService;


    private String prefix = "web/practice/";

    @RequestMapping("/practice")
    @GetMapping()
    public String list() {
        return prefix + "list";
    }

    @RequestMapping("/practice/start/{id}")
    @GetMapping()
    public String start(@PathVariable String id, ModelMap mmap) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("practiceId", id);
        List<ExamQuestionVO> result = examQuestionService.selectQuestionListByPracticeId(map);
        if (map.containsKey("disorder") && map.get("disorder").toString().equals("1")) {
            Collections.shuffle(result);
        }
        mmap.put("data", result);
        return prefix + "detail";
    }
}
