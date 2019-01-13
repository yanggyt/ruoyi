package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by flower on 2019/1/9.
 */
@Api("考试")
@RestController
@RequestMapping("/api")
public class ApiExaminationController extends BaseController {

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


    /**
     * 获取考试列表
     * @param examExamination
     * @return
     */
    @GetMapping("/v1/examination/list")
    public AjaxResult list(ExamExamination examExamination) {

        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );

        Map<String, Object> map = new HashMap<>();
        map.put("ination",examExamination);
        map.put("userId", sysUser.getUserId());
        List<ExamExamination> list = examExaminationService.selectListFromWeb(map);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }


    /**
     * 开始考试
     * @param inationId
     * @return
     */
    @GetMapping("/v1/examination/start/{inationId}")
    public AjaxResult start(@PathVariable("inationId") String inationId) {
        ExamExamination examExamination = examExaminationService.selectById(inationId);
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        Integer userId = Integer.parseInt(sysUser.getUserId().toString());
        //考试类型
        String type = examExamination.getType();
        //试卷ID
        Integer examPaperId = examExamination.getExamPaperId();
        //考试次数
        Integer examNumber = examExamination.getExamNumber();
        //考试时长
        Integer timeLength = examExamination.getTimeLength();

        if(type.equals("2")){
            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId(userId);
            examUserExamination.setExamPaperId(examPaperId);
            examUserExamination.setExamExaminationId(Integer.parseInt(inationId));
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne(examUserExamination);
            // 最后一次考试
            ExamUserExamination last;

            //超过考试次数
            if(userExamination.size()>=examNumber){

                last = userExamination.get(0);
                //最后一次考试已交卷，直接返回
                if(last.getUpdateDate()!=null&&!last.getUpdateDate().equals("")){
                    return error(500,"已超过"+examNumber+"次考试，");
                }else{
                    // 最后一次考试未交卷，但超过考试时长,直接返回
                    if(last.getCreateDate().getTime()+timeLength*60*1000<new Date().getTime()){
                        return error(500,"已超过"+examNumber+"次考试，");
                    }
                }

            }

            if(userExamination.size()<=0 //考试次数小于0
                    ||userExamination.get(0).getUpdateDate()!=null //最后一次考试已交卷
                    ||userExamination.get(0).getCreateDate().getTime()+timeLength*60*1000<new Date().getTime()//最后一次考试，已超过考过时长
                    ){
                ExamUserExamination insert = new ExamUserExamination();
                insert.setExamExaminationId(Integer.parseInt(inationId));
                insert.setVipUserId(userId);
                insert.setCreateDate(new Date());
                insert.setExamPaperId(examPaperId);
                insert.setDelFlag("0");
                insert.setScore(0);
                examUserExaminationService.insert(insert);

            }

        }

        List<ExamQuestionVO> list = examPaperService.selectQuestionAndItemByPaperId(examPaperId);
        //是否乱序
        if(examExamination.getQuestionDisorder().equals("2")){
            Collections.shuffle(list);
        }
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }


    /**
     * 报名列表
     * @param examExamination
     * @return
     */
    @GetMapping("/v1/examination/entername/list")
    public AjaxResult enterNameList(ExamExamination examExamination) {
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );

        Map<String, Object> map = new HashMap<>();
        map.put("ination",examExamination);
        map.put("userId",sysUser.getUserId());
        List<ExamExamination> list = examExaminationService.selectEnterNameListFromWeb(map);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }



    /**
     * 报名
     * @param sysUser
     * @param inationId
     * @return
     */
    @PostMapping("/v1/examination/entername")
    public AjaxResult enterName(SysUser sysUser,String inationId) {
        SysUser user = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        Long userId = user.getUserId();
        sysUser.setUserId(userId);
        sysUserService.updateSelectiveById(sysUser);

        ExamExaminationUser examExaminationUser = new ExamExaminationUser();
        examExaminationUser.setVipUserId(Integer.parseInt(userId.toString()));
        examExaminationUser.setDelFlag("0");
        examExaminationUser.setCreateDate(new Date());
        examExaminationUser.setCreateBy(user.getLoginName());
        examExaminationUser.setExamExaminationId(Integer.parseInt(inationId));
        examExaminationUserService.insert(examExaminationUser);

        AjaxResult success = success("报名成功");
        return success;
    }





}
