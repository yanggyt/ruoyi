package com.ruoyi.exam.controller;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.service.*;
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
@Api("练习接口")
@RestController
@RequestMapping("/api")
public class ApiPracticeController extends BaseController {

    @Autowired
    private IExamPracticeService examPracticeService;


    @Autowired
    private IExamQuestionService examQuestionService;

    /**
     * 查询练习列表
     * @param examPractice
     * @return
     */
    @GetMapping("/v1/practice/list")
    public AjaxResult list(@RequestParam ExamPractice examPractice) {

        List<ExamPracticeVO> list = examPracticeService.selectListFromWeb(examPractice);
        AjaxResult success = success("查询成功");
        success.put("data", list);
        return success;
    }

    /**
     * 查询练习题库的问题列表
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

}
