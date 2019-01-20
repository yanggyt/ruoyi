package com.ruoyi.cms.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.exception.base.BaseException;
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
    public String list() {
        return prefix + "list";
    }

    @RequestMapping("/examination/start/{id}")
    @GetMapping()
    public String start(@PathVariable String id, ModelMap mmap) {
        ExamExamination examExamination = examExaminationService.selectById( id );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        Integer userId = Integer.parseInt( sysUser.getUserId().toString() );
        //考试类型
        String type = examExamination.getType();
        //试卷ID
        Integer examPaperId = examExamination.getExamPaperId();
        //考试次数
        Integer examNumber = examExamination.getExamNumber();
        //考试时长
        Integer timeLength = examExamination.getTimeLength();
        //考试记录ID
        Integer examUserExaminationId = -1;
        if(1==1){
            throw new BaseException("已超过" + examNumber + "次考试，");
        }
        ExamUserExamination insert = new ExamUserExamination();
        //正式考试
        if (type.equals( "2" )) {
            ExamUserExamination examUserExamination = new ExamUserExamination();
            examUserExamination.setVipUserId( userId );
            examUserExamination.setExamPaperId( examPaperId );
            examUserExamination.setExamExaminationId( Integer.parseInt( id ) );
            //考试记录集合
            List<ExamUserExamination> userExamination = examUserExaminationService.selectLastOne( examUserExamination );
            // 最后一次考试
            ExamUserExamination last;

            //超过考试次数
            if (userExamination.size() >= examNumber) {

                last = userExamination.get( 0 );
                //最后一次考试已交卷，直接返回
                if (last.getUpdateDate() != null && !last.getUpdateDate().equals( "" )) {
                    throw new BaseException("已超过" + examNumber + "次考试，");
//                    return error( 500, "已超过" + examNumber + "次考试，" );
                } else {
                    // 最后一次考试未交卷，但超过考试时长,直接返回
                    if (last.getCreateDate().getTime() + timeLength * 60 * 1000 < new Date().getTime()) {
                        throw  new BaseException( "已超过" + examNumber + "次考试，" );
                    }
                }

            }

            if (userExamination.size() <= 0 //考试次数小于0
                    || userExamination.get( 0 ).getUpdateDate() != null //最后一次考试已交卷
                    || userExamination.get( 0 ).getCreateDate().getTime() + timeLength * 60 * 1000 < new Date().getTime()//最后一次考试，已超过考过时长
                    ) {
                insert.setExamExaminationId( Integer.parseInt( id ) );
                insert.setVipUserId( userId );
                insert.setCreateDate( new Date() );
                insert.setExamPaperId( examPaperId );
                insert.setDelFlag( "0" );
                insert.setScore( 0 );
                examUserExaminationService.insertOne( insert );
                examUserExaminationId = insert.getId();
            } else {
                examUserExaminationId = userExamination.get( 0 ).getId();
            }

        }
        ExamPaper examPaper = examPaperService.selectById(examPaperId);
        List<ExamQuestionVO> data = new ArrayList<>();
        List<ExamQuestionVO> list = examPaperService.selectQuestionAndItemByPaperId( examPaperId );
        //随机试卷
        if(examPaper.getType().equals("2")){
            Collections.shuffle( list );
            ExamPaperTypeNumber examPaperTypeNumber = new ExamPaperTypeNumber();
            examPaperTypeNumber.setExamPaperId(examPaperId);
            List<ExamPaperTypeNumber> examPaperTypeNumbers = examPaperTypeNumberService.selectList(examPaperTypeNumber);
            //三种题型的数量
            int one=0,two=0,three=0;
            for (ExamPaperTypeNumber item : examPaperTypeNumbers) {
                if(item.getExamQuestionType()==1){
                    one = item.getNumber();
                }
                if(item.getExamQuestionType()==2){
                    two = item.getNumber();
                }
                if(item.getExamQuestionType()==3){
                    three = item.getNumber();
                }
            }
            for (ExamQuestionVO item : list) {
                if(item.getType().equals("1")&&one>0){
                    data.add(item);
                    one--;
                }
                if(item.getType().equals("2")&&two>0){
                    data.add(item);
                    two--;
                }
                if(item.getType().equals("3")&&three>0){
                    data.add(item);
                    three--;
                }
            }
        }else{
            data = list;
        }
        //是否乱序
        if (examExamination.getQuestionDisorder().equals( "2" )) {
            Collections.shuffle( list );
        }

        mmap.put( "data", data );
        mmap.put( "examUserExaminationId", examUserExaminationId );
        mmap.put( "examExamination", examExamination );
        return prefix+"deatil";
    }


}
