package com.ruoyi.cms.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.exception.base.BaseException;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by flower on 2019/1/21.
 */
@Controller
@RequestMapping("/web")
public class CmsExaminationController {
    private String prefix = "web/examination/";

    @Autowired
    private IExamExaminationService examExaminationService;

    @Autowired
    private IExamPaperService examPaperService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamExaminationUserService examExaminationUserService;

    @Autowired
    private IExamUserExaminationQuestionService examUserExaminationQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamPaperQuestionService examPaperQuestionService;

    @Autowired
    private IExamPaperTypeNumberService examPaperTypeNumberService;

    @RequestMapping("/examination")
    @GetMapping()
    public String list(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "list";
    }

    @RequestMapping("/examination/start/{id}")
    @GetMapping()
    public String start(@PathVariable String id, ModelMap mmap) {
        ExamExamination examExamination = examExaminationService.selectById(id);
        Integer examUserExaminationId = -1;
        List<ExamQuestionVO> data = examExaminationService.queryExaminationQuestion(examExamination,examUserExaminationId);
        Integer examPaperId = examExamination.getExamPaperId();

        mmap.put( "data", data );
        mmap.put( "examUserExaminationId", examUserExaminationId );
        mmap.put( "examExamination", examExamination );
        mmap.put("paperId", examPaperId);
        return prefix+"detail";
    }


}
