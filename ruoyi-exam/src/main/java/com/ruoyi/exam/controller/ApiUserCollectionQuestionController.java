package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by flower on 2019/1/9.
 */
@Api("收藏接口")
@RestController
@RequestMapping("/api")
public class ApiUserCollectionQuestionController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IExamUserCollectionQuestionService examUserCollectionQuestionService;

    /**
     * 保存收藏记录
     *
     * @param id
     * @return
     */
    @PostMapping("/v1/question/collection/{id}")
    public AjaxResult answer(@PathVariable("id") Integer id) {
        ExamUserCollectionQuestion examUserCollectionQuestion = new ExamUserCollectionQuestion();
        examUserCollectionQuestion.setExamQuestionId( id );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        examUserCollectionQuestion.setVipUserId( sysUser.getUserId().intValue() );
        examUserCollectionQuestion.setCreateBy( sysUser.getLoginName() );
        examUserCollectionQuestion.setCreateDate( new Date() );
        examUserCollectionQuestion.setDelFlag( "0" );
        examUserCollectionQuestion.setUpdateBy( sysUser.getLoginName() );
        examUserCollectionQuestion.setUpdateDate( new Date() );
        int insert = examUserCollectionQuestionService.insertSelectiveBySelf( examUserCollectionQuestion );
        AjaxResult success = success( "插入我的收藏记录成功" );
        return success;
    }

    /**
     * 我的收藏显示列表（分页）
     *
     * @return
     */
    @GetMapping("/v1/question/collection/page")
    public AjaxResult collectionPage() {
        ExamUserCollectionQuestionVO examUserCollectionQuestion = new ExamUserCollectionQuestionVO();
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        examUserCollectionQuestion.setVipUserId( sysUser.getUserId().intValue() );
        List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionPage( examUserCollectionQuestion );
        AjaxResult success = success( "查询我的收藏成功" );
        success.put( "data", list );
        return success;
    }

    /**
     * 我的收藏显示列表（不分页）
     *
     * @return
     */
    @GetMapping("/v1/question/collection/list")
    public AjaxResult collectionList() {
        ExamUserCollectionQuestionVO examUserCollectionQuestion = new ExamUserCollectionQuestionVO();
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        examUserCollectionQuestion.setVipUserId( sysUser.getUserId().intValue() );
        List<ExamUserCollectionQuestionVO> list = examUserCollectionQuestionService.selectExamUserCollectionQuestionPage( examUserCollectionQuestion );
        AjaxResult success = success( "查询我的收藏成功" );
        success.put( "data", list );
        return success;
    }

    /**
     * 删除我的收藏记录
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/question/collection/delete/{id}")
    public AjaxResult deleteCollectionQuestion(@PathVariable("id") String id) {
        examUserCollectionQuestionService.deleteById( id );
        AjaxResult success = success( "删除成功" );
        return success;
    }
}
