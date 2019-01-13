package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.IExamPracticeQuestionService;
import com.ruoyi.exam.service.IExamPracticeService;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.exam.service.IExamUserErrorQuestionService;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.EntityUtils;
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
@Api("练习")
@RestController
@RequestMapping("/api")
public class ApiPracticeController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;

    @Autowired
    private IExamPracticeQuestionService examPracticeQuestionService;

    @Autowired
    private IExamQuestionService examQuestionService;

    @Autowired
    private IExamUserErrorQuestionService examUserErrorQuestionService;

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/v1/practice/list")
    public AjaxResult list(ExamPractice examPractice) {

        List<ExamPractice> list = examPracticeService.selectListFromWeb(examPractice);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }

    /**
     * 查询练习具体的问题列表
     *
     * @param map
     * @return
     */
    @GetMapping("/v1/practice/info")
    public AjaxResult queryOne(@RequestParam Map<String, Object> map) {
        List<ExamQuestionVO> result = examQuestionService.selectQuestionListByPracticeId(map);
        if (map.containsKey("disorder") && map.get("disorder").toString().equals("1")) {
            Collections.shuffle(result);
        }
        AjaxResult success = success("查询成功");
        success.put("data", result);
        return success;
    }

    /**
     * 保存错题记录
     *
     * @param questionIds
     * @return
     * @description 练习时答错题就保存到错题记录中
     * 传入问题id
     */
    @PostMapping("/v1/practice/answer")
    public AjaxResult answer(@RequestBody List<String> questionIds) {
        for (String questionId : questionIds) {
            ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
            examUserErrorQuestion.setExamQuestionId(Integer.parseInt(questionId));
            SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
            examUserErrorQuestion.setVipUserId(sysUser.getUserId().intValue());
            examUserErrorQuestion.setCreateBy(sysUser.getLoginName());
            examUserErrorQuestion.setCreateDate(new Date());
            examUserErrorQuestion.setDelFlag("0");
            examUserErrorQuestion.setUpdateBy(sysUser.getLoginName());
            examUserErrorQuestion.setUpdateDate(new Date());
            int insert = examUserErrorQuestionService.insertError( examUserErrorQuestion );
        }
        AjaxResult success = success("插入错题本成功");
        return success;
    }

    /**
     * 查询我的错题列表
     *
     * @return
     */
    @GetMapping("/v1/practice/{userId}/error")
    public AjaxResult queryError(@PathVariable("userId") String userId) {
        ExamUserErrorQuestion examUserErrorQuestion = new ExamUserErrorQuestion();
        examUserErrorQuestion.setVipUserId(Integer.parseInt(ShiroUtils.getUserId().toString()));
        List<ExamUserErrorQuestionVO> list = examUserErrorQuestionService.selectExamUserErrorQuestionDetailPage(examUserErrorQuestion);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }

    /**
     * 查询问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/practice/question/{id}")
    public AjaxResult queryQuestion(@PathVariable("id") String id) {
        ExamQuestionVO result = examQuestionService.selectQuestionDetail(id);
        AjaxResult success = success("查询成功");
        success.put("data", result);
        return success;
    }

}
