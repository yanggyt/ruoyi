package com.ruoyi.cms.controller;

import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 考试列表
     * @param map
     * @return
     */
    @RequestMapping("/examination/list")
    @GetMapping()
    @ResponseBody
    public AjaxResult list(ExamExamination examExamination) {
        SysUser sysUser = sysUserService.selectUserByLoginName( ShiroUtils.getLoginName() );
        Map<String, Object> map = new HashMap<>();
        map.put( "ination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectListFromWeb( map );
        AjaxResult success = AjaxResult.success( "查询成功" );
        success.put( "data", list );
        success.put("total",new PageInfo(list).getTotal());
        return success;
    }

    /**
     * 报名列表
     * @param map
     * @return
     */
    @RequestMapping("/examination/signuplist")
    @GetMapping()
    @ResponseBody
    public AjaxResult signupist(ExamExamination examExamination) {
        SysUser sysUser = sysUserService.selectUserByLoginName( ShiroUtils.getLoginName() );

        Map<String, Object> map = new HashMap<>();
        map.put( "ination", examExamination );
        map.put( "userId", sysUser.getUserId() );
        List<ExamExamination> list = examExaminationService.selectEnterNameListFromWeb( map );
        AjaxResult success = AjaxResult.success( "查询成功" );
        success.put("total",new PageInfo(list).getTotal());
        success.put( "data", list );
        return success;
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
