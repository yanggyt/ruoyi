package com.ruoyi.cms.controller;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.service.IExamPracticeService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/web")
public class CmsController {
    private static final Logger log = LoggerFactory.getLogger( CmsController.class );

    private String prefix = "web";
    @Autowired
    private ITrainCourseService trainCourseService;

    @Autowired
    private ITrainCourseCategoryService trainCourseCategoryService;

    @Autowired
    private ITrainCourseSectionService trainCourseSectionService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IExamPracticeService examPracticeService;

    @RequestMapping("")
    @GetMapping()
    public String user(TrainCourseVO trainCourse, ModelMap map) {
        List<TrainCourseVO> list = trainCourseService.selectTrainCoursePage( trainCourse );
        map.put( "trainCourse", list );
        return prefix + "/index";
    }


    @RequestMapping("/user/login.html")
    @GetMapping()
    public String login() {
        return prefix + "/user/login";
    }


    @RequestMapping("/user/reg.html")
    @GetMapping()
    public String reg() {
        return prefix + "/user/reg";
    }


    @RequestMapping("/course/courseInfo.html/{id}")
    @GetMapping()
    public String courseInfo(@PathVariable("id") Integer id, ModelMap map) {
        TrainCourse trainCourse = trainCourseService.selectById( id );
        TrainCourseSection trainCourseSection = new TrainCourseSection();
        trainCourseSection.setTrainCourseId( id );
        List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );
        ExamPractice examPractice = new ExamPractice();
        examPractice.setTrainCourseId( id );
        List<ExamPractice> examPractices = examPracticeService.selectExamPracticeList( examPractice );
        map.put( "trainCourse", trainCourse );
        map.put( "trainCourseSections", trainCourseSections );
        map.put( "examPractices", examPractices );
        return prefix + "/course/courseInfo";
    }
    @RequestMapping("/course/courseSections.html/{id}")
    @GetMapping()
    public String courseSections(@PathVariable("id") Integer id, ModelMap map) {
        TrainCourseSection tcs= trainCourseSectionService.selectById( id );
        TrainCourseSection trainCourseSection = new TrainCourseSection();
        trainCourseSection.setTrainCourseId( tcs.getTrainCourseId() );
        List<TrainCourseSection> trainCourseSections = trainCourseSectionService.selectTrainCourseSectionList( trainCourseSection );
        ExamPractice examPractice = new ExamPractice();
        examPractice.setTrainCourseId( id );
        map.put( "trainCourseSection", tcs );
        map.put( "trainCourseSections", trainCourseSections );
        return prefix + "/course/courseSections";
    }

}
