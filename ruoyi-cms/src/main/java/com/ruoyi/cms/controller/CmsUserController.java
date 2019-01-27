package com.ruoyi.cms.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import cn.hutool.extra.servlet.ServletUtil;
import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.util.ServletUtils;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.train.course.domain.TrainCourse;
import com.ruoyi.train.course.domain.TrainCourseSection;
import com.ruoyi.train.course.domain.TrainCourseVO;
import com.ruoyi.train.course.service.ITrainCourseCategoryService;
import com.ruoyi.train.course.service.ITrainCourseSectionService;
import com.ruoyi.train.course.service.ITrainCourseService;
import com.ruoyi.vip.domain.vo.VipUserOrdersVO;
import com.ruoyi.vip.service.IVipUserOrdersService;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.image.IntegerComponentRaster;

import javax.servlet.http.Cookie;
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

    @Autowired
    private IExamUserCollectionQuestionService examUserCollectionQuestionService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;



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
        return prefix + "/user/set";
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

    @RequestMapping("/user/collectquestion.html")
    public String collectQuestion(ModelMap map) {
        ExamUserCollectionQuestionVO examUserCollectionQuestion = new ExamUserCollectionQuestionVO();
        SysUser sysUser = ShiroUtils.getSysUser();
        examUserCollectionQuestion.setVipUserId( sysUser.getUserId().intValue() );
        List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionList( examUserCollectionQuestion );
        map.put("data", list);
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/collectquestion";
    }

    /**
     * 增加错题
     * @param questionId
     * @return
     */
    @RequestMapping("/user/adderrorquestion")
    @ResponseBody
    public AjaxResult addErrorquestion(String questionId) {
        SysUser sysUser = ShiroUtils.getSysUser();
        examUserErrorQuestionService.insertError(questionId,sysUser);
        AjaxResult success = AjaxResult.success("插入成功");
        return success;
    }

    @RequestMapping("/user/addcollectquestion")
    @ResponseBody
    public AjaxResult addCollectionquestion(String questionId) {
        SysUser sysUser = ShiroUtils.getSysUser();
        examUserCollectionQuestionService.insertSelectiveBySelf(Integer.parseInt(questionId),sysUser);
        AjaxResult success = AjaxResult.success("插入成功");
        return success;
    }

    @RequestMapping("/user/delerrorquestion")
    @ResponseBody
    public AjaxResult delErrorquestion(Integer questionId) {
        ExamUserErrorQuestion question = new ExamUserErrorQuestion();
        question.setVipUserId(ShiroUtils.getUserId().intValue());
        question.setExamQuestionId(questionId);
        examUserErrorQuestionService.delete(question);
        AjaxResult success = AjaxResult.success("删除成功");
        return success;
    }

    @RequestMapping("/user/delcollectquestion")
    @ResponseBody
    public AjaxResult delCollectionquestion(Integer questionId) {
        ExamUserCollectionQuestion examUserCollectionQuestion = new ExamUserCollectionQuestion();
        examUserCollectionQuestion.setVipUserId(ShiroUtils.getUserId().intValue());
        examUserCollectionQuestion.setExamQuestionId(questionId);
        examUserCollectionQuestionService.delete(examUserCollectionQuestion);
        AjaxResult success = AjaxResult.success("删除成功");
        return success;
    }

    @RequestMapping("/user/userexamination.html")
    public String userExamquestion(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/userexamination";
    }


    /**
     * 我的考试记录
     * @return
     */
    @RequestMapping("/user/myuserexamination/list")
    @ResponseBody
    public AjaxResult userExamquestionList() {
        ExamUserExaminationVO examUserExamination = new ExamUserExaminationVO();
        examUserExamination.setVipUserId(ShiroUtils.getUserId().intValue());
        List<ExamUserExaminationVO> list = examUserExaminationService.selectMyExamUserExamination(examUserExamination);
        AjaxResult success = AjaxResult.success("查询成功");
        success.put("total",new PageInfo(list).getTotal());
        success.put( "data", list );
        return success;
    }



    @RequestMapping("/user/myuserexamination/detail/{id}")
    public String userExamquestion(@PathVariable Integer id, ModelMap map) {

        map.put( "user", ShiroUtils.getSysUser() );
        ExamUserExaminationVO data = examUserExaminationService.selectDetailById( id );
        map.put( "data", data );
        return prefix + "/user/userexamination_detail";
    }

}
