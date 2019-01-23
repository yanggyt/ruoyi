package com.ruoyi.cms.controller;

import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamUserErrorQuestionService;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/web")
public class CmsUserController {
    private static final Logger log = LoggerFactory.getLogger( CmsUserController.class );

    private String prefix = "web";

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;



    @RequestMapping("/user/login.html")
    public String login(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/login";
    }



    @RequestMapping("/user/reg.html")
    public String reg(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/reg";
    }



    @RequestMapping("/user/index.html")
    public String webUserIndex(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/index";
    }
    @RequestMapping("/user/home.html")
    public String webUserHome(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/home";
    }
    @RequestMapping("/user/set.html")
    public String webUserSet(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/set";
    }

    @RequestMapping("/user/message.html")
    public String webUserMessage(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/message";
    }

    @RequestMapping("/user/errorquestion.html")
    public String errorquestion(ModelMap map) {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        examUserErrorQuestion.setVipUserId(ShiroUtils.getSysUser().getUserId().intValue());
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailList(examUserErrorQuestion);
        map.put("data", list);
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/errorquestion";
    }
}
