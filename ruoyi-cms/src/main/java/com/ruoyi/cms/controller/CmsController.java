package com.ruoyi.cms.controller;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.service.ITrainCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
public class CmsController
{
    private static final Logger log = LoggerFactory.getLogger(CmsController.class);

    private String prefix = "web";
    @Autowired
    private ITrainCourseService trainCourseService;
    @RequestMapping("")
    @GetMapping()
    public String user(TrainCourseVO trainCourse,ModelMap map) {
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


}
