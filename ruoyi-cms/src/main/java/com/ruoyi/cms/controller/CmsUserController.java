package com.ruoyi.cms.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.*;
import cn.hutool.extra.servlet.ServletUtil;
import com.ruoyi.exam.domain.ExamPractice;
import com.ruoyi.exam.domain.ExamUserErrorQuestion;
import com.ruoyi.exam.domain.ExamUserErrorQuestionVO;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.image.IntegerComponentRaster;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/web")
@SessionAttributes("user")
public class CmsUserController {
    private static final Logger log = LoggerFactory.getLogger( CmsUserController.class );

    private String prefix = "web";


    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;

    @Autowired
    private IExamUserCollectionQuestionService examUserCollectionQuestionService;

    @Autowired
    private IExamUserExaminationService examUserExaminationService;

    @Autowired
    private IVipUserOrdersService vipUserOrdersService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysPasswordService passwordService;


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

    @RequestMapping("/user/regaccount")
    @ResponseBody
    public AjaxResult reg(SysUser user) {
        List<SysUser> sysUsers = sysUserService.selectUserList(user);
        AjaxResult success = AjaxResult.success("注册成功");
        if(sysUsers.size()>0){
            success = AjaxResult.error("账户名已存在");
            return success;
        }
        user.setStatus("0");
        user.setDelFlag("0");
        user.setCreateTime(new Date());
        user.setSalt( ShiroUtils.randomSalt() );
        user.setUserType( UserConstants.USER_VIP );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        sysUserService.insertUser(user);
        return success;
    }



    @RequestMapping("/user/index.html")
    public String webUserIndex(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        map.addAttribute("user", ShiroUtils.getSysUser());
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
        int i = examUserCollectionQuestionService.insertSelectiveBySelf(Integer.parseInt(questionId), sysUser);
        AjaxResult success = AjaxResult.success("收藏成功");
        if(i==0){
            success = AjaxResult.success("已收藏,无法重复收藏");
        }
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


    /**
     * 考试记录详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/user/myuserexamination/detail/{id}")
    public String userExamquestion(@PathVariable Integer id, ModelMap map) {

        map.put( "user", ShiroUtils.getSysUser() );
        ExamUserExaminationVO data = examUserExaminationService.selectDetailById( id );
        map.put( "data", data );
        return prefix + "/user/userexamination_detail";
    }

    @RequestMapping("/user/orders.html")
    public String orders(ModelMap map) {
        map.put( "user", ShiroUtils.getSysUser() );
        return prefix + "/user/orders";
    }

    @RequestMapping("/user/orders/list")
    @ResponseBody
    public AjaxResult orders(VipUserOrdersVO vipUserOrders) {
        AjaxResult success = AjaxResult.success("插入成功");
        vipUserOrders.setVipUserId(ShiroUtils.getUserId().intValue());
        List<VipUserOrdersVO> list = vipUserOrdersService.selectVipUserOrdersPage(vipUserOrders);
        success.put("total",new PageInfo(list).getTotal());
        success.put( "data", list );
        return success;
    }
    /**
     * 保存头像
     */
    @Log(title = "个人信息修改", businessType = BusinessType.UPDATE)
    @RequestMapping("/user/update")
    @ResponseBody
    public AjaxResult update(SysUser user) {
        if (sysUserService.updateUserInfo( user ) > 0) {
            ShiroUtils.setSysUser( sysUserService.selectUserById( user.getUserId() ) );
        }
        return AjaxResult.success();
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @RequestMapping("/user/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        sysUserService.resetUserPwd( user );
        return AjaxResult.success();
    }

}
