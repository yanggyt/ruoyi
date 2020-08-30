package com.ruoyi.dfm.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.dfm.pojo.Result;
import com.ruoyi.dfm.service.StatisticsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 项目统计控制器
 */
@Controller
@RequestMapping("/statistics.do")
public class StatisticsController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DataAnalysisController.class);
    @Autowired
    StatisticsService statisticsService;

    /**
     * 项目统计页面
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public ModelAndView defaultHandle(HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Map<String, Object>> allYear = statisticsService.getAllYear();
        List<Object> allYears = new ArrayList<>(allYear.size());
        for(Map<String, Object> map : allYear) {
            allYears.add(map.get("f_year"));
        }
        req.setAttribute("allYears", allYears);
        return new ModelAndView("statistics");
    }

    /**
     * 获取软件总利用率
     * @param req
     * @param res
     * @throws Exception
     */
    @RequestMapping("/usage")
    public void usage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            logger.info("获取软件总利用率");
            Map<String, Object> usage = statisticsService.usage();
            Result result = new Result();
            result.setSuccess(true);
            result.setMessage("获取软件利用率成功");
            result.setData(usage);
            outputJson(res, JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("获取软件利用率失败！", e);
            Result result = new Result();
            result.setSuccess(false);
            result.setMessage("获取软件利用率失败！");
            outputJson(res, JSON.toJSONString(result));
        }

    }

    /**
     * 获取根据部门分组的时间利用率
     * @param req
     * @param res
     * @throws Exception
     */
    @RequestMapping("/usageGroupByDepartment")
    public void usageGroupByDepartment(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            logger.info("获取根据部门分组的时间利用率");
            List<Map<String, Object>> usages = statisticsService.usageGroupByDepartment();
            Result result = new Result();
            result.setSuccess(true);
            result.setMessage("获取软件利用率成功");
            result.setData(usages);
            outputJson(res, JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("获取软件利用率失败！", e);
            Result result = new Result();
            result.setSuccess(false);
            result.setMessage("获取软件利用率失败！");
            outputJson(res, JSON.toJSONString(result));
        }

    }

    /**
     * 获取年度根据部门分组的时间利用率
     * @param req
     * @param res
     * @throws Exception
     */
    @RequestMapping("/usageGroupByDepartmentAndYear")
    public void usageGroupByDepartmentAndYear(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            logger.info("获取年度根据部门分组的时间利用率");
            List<Map<String, Object>> usages = statisticsService.usageGroupByDepartmentAndYear();
            Result result = new Result();
            result.setSuccess(true);
            result.setMessage("获取软件利用率成功");
            result.setData(usages);
            outputJson(res, JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("获取软件利用率失败！", e);
            Result result = new Result();
            result.setSuccess(false);
            result.setMessage("获取年度软件利用率失败！");
            outputJson(res, JSON.toJSONString(result));
        }

    }

    /**
     * 单位时间内已完成的项目数量统计
     * 月份：f_month
     * 完成的项目数量：f_count
     * @param req
     * @param res
     * @throws Exception
     */
    @RequestMapping("/completedProjectSummary")
    public void completedProjectSummary(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String year = req.getParameter("year");
        if(StringUtils.isBlank(year)) {
            year = new Date().getYear() + "";
        }
        try {
            logger.info("按月统计已完成数量");
            List<Map<String, Object>> countResult = statisticsService.countByMonth(year);
            Result result = new Result();
            result.setSuccess(true);
            result.setMessage("按月统计已完成数量成功");
            result.setData(countResult);
            outputJson(res, JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("按月统计已完成数量失败！", e);
            Result result = new Result();
            result.setSuccess(false);
            result.setMessage("按月统计已完成数量失败！年份=" + year);
            outputJson(res, JSON.toJSONString(result));
        }
    }

}
