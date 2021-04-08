package com.sinosoft.activity.controller;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.sinosoft.activity.domain.ActConfig;
import com.sinosoft.activity.domain.DrawInfo;
import com.sinosoft.activity.domain.DrawPrizeInfo;
import com.sinosoft.activity.domain.DrawRule;
import com.sinosoft.activity.service.IDrawInfoService;
import com.sinosoft.activity.service.IDrawPrizeInfoService;
import com.sinosoft.activity.service.IDrawRuleService;
import com.sinosoft.activity.vo.ActVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抽奖活动管理Controller
 * 
 * @author xlh
 * @date 2021-03-25
 */
@Controller
@RequestMapping("/activity/info")
public class DrawInfoController extends BaseController
{
    private String prefix = "activity/info";

    @Autowired
    private IDrawInfoService drawInfoService;

    @RequiresPermissions("activity:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    @Autowired
    private IDrawPrizeInfoService iDrawPrizeInfoService;

    @Autowired
    private IDrawRuleService iDrawRuleService;
    /**
     * 查询抽奖活动管理列表
     */
    @RequiresPermissions("activity:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawInfo drawInfo)
    {
        startPage();
        List<DrawInfo> list = drawInfoService.selectDrawInfoList(drawInfo);
        return getDataTable(list);
    }

    /**
     * 导出抽奖活动管理列表
     */
    @RequiresPermissions("activity:info:export")
    @Log(title = "抽奖活动管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawInfo drawInfo)
    {
        List<DrawInfo> list = drawInfoService.selectDrawInfoList(drawInfo);
        ExcelUtil<DrawInfo> util = new ExcelUtil<DrawInfo>(DrawInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增抽奖活动管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存抽奖活动管理
     */
    @RequiresPermissions("activity:info:add")
    @Log(title = "抽奖活动管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawInfo drawInfo)
    {
        logger.info("前台传参"+ JSON.toJSONString(drawInfo));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        drawInfo.setCREATETIMESTAMP(date);
        String format1 = format.format(date);
        drawInfo.setDRAWCODE(format1);
         drawInfoService.insertDrawInfo(drawInfo);
        DrawRule drawRule = new  DrawRule();
        BeanUtils.copyProperties(drawInfo,drawRule);
        logger.info("接口新增"+ JSON.toJSONString(drawRule));
        int i = iDrawRuleService.insertDrawRule(drawRule);
        return toAjax(i);
    }

    @PostMapping("/add/test")
    @ResponseBody
    public AjaxResult addtest(@RequestBody ActVO vo){
        ActConfig actConfig = vo.getActConfig();
        return null;
    }


    /**
     * 修改抽奖活动管理
     */
    @GetMapping("/edit/{DRAWID}")
    public String edit(@PathVariable("DRAWID") String DRAWID, ModelMap mmap)
    {
        DrawInfo drawInfo = drawInfoService.selectDrawInfoById(DRAWID);
        mmap.put("drawInfo", drawInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存抽奖活动管理
     */
    @RequiresPermissions("activity:info:edit")
    @Log(title = "抽奖活动管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawInfo drawInfo)
    {
        logger.info("修改存储抽奖特殊规则对象传参："+JSON.toJSONString(drawInfo));
        drawInfo.setLASTUPDATETIMESTAMP(new Date());
        drawInfoService.updateDrawInfo(drawInfo);
        DrawRule drawRule = new  DrawRule();
        BeanUtils.copyProperties(drawInfo,drawRule);
        logger.info("修改存储抽奖特殊规则对象入参："+JSON.toJSONString(drawRule));
        int i = iDrawRuleService.updateDrawRule(drawRule);
        return toAjax(i);
    }

    /**
     * 删除抽奖活动管理
     */
    @RequiresPermissions("activity:info:remove")
    @Log(title = "抽奖活动管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawInfoService.deleteDrawInfoByIds(ids));
    }

    /**
     * 获取存储奖品的基础信息对象
     *
     * @return
     */
    @GetMapping("/prizeInfo")
    @ResponseBody
    public List<DrawPrizeInfo> selectDrawPrizeInfoList(DrawPrizeInfo drawPrizeInfo) {

        return iDrawPrizeInfoService.selectDrawPrizeInfoList(drawPrizeInfo);
    }

    @RequestMapping("/prizeDetail/{DRAWCODE}/{DRAWNAME}")
    public String  prizeDetail(@PathVariable("DRAWCODE") String  DRAWCODE ,@PathVariable("DRAWNAME") String  DRAWNAME , ModelMap mapp){
        mapp.put("DRAWCODE",DRAWCODE);
        mapp.put("DRAWNAME", URLDecoder.decode(DRAWNAME));
        return prefix + "/configList";
    }
}
