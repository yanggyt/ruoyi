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
import com.sinosoft.activity.service.*;
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

    @Autowired
    private IActConfigService iActConfigService;

    @Autowired
    private IActPageConfigGuideService iActPageConfigGuideService;

    @Autowired
    private IActPageConfigUserinfoService iActPageConfigUserinfoService;

    @Autowired
    private IActPageConfigSubscribeService iActPageConfigSubscribeService;

    /**
     * 新增保存抽奖活动管理
     */
    @RequiresPermissions("activity:info:add")
    @Log(title = "抽奖活动管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ActVO vo)
    {
        try{
            logger.info("前台传参"+ JSON.toJSONString(vo));
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            //新增基本信息
            vo.getDrawInfo().setCREATETIMESTAMP(date);
            String code = format.format(date);
            vo.getDrawInfo().setDRAWCODE(code);
            drawInfoService.insertDrawInfo(vo.getDrawInfo());

            //新增展示内容
            vo.getActPageConfigGuide().setCreateTime(date);
            vo.getActPageConfigGuide().setActCode(code);
            iActPageConfigGuideService.insertActPageConfigGuide(vo.getActPageConfigGuide());

            //新增选择玩法
            vo.getDrawRule().setCREATETIMESTAMP(date);
            vo.getDrawRule().setDRAWCODE(code);
            iDrawRuleService.insertDrawRule(vo.getDrawRule());

            //新增收集信息
            vo.getActPageConfigUserinfo().setCreateTime(date);
            vo.getActPageConfigUserinfo().setActCode(code);
            iActPageConfigUserinfoService.insertActPageConfigUserinfo(vo.getActPageConfigUserinfo());

            //新增分享信息
            vo.getActConfig().setCreateTime(date);
            vo.getActConfig().setActCode(code);
            vo.getActConfig().setActName(vo.getDrawInfo().getDRAWNAME());
            iActConfigService.insertActConfig(vo.getActConfig());
            //新增二维码信息
            vo.getActPageConfigSubscribe().setCreateTime(date);
            vo.getActPageConfigSubscribe().setActCode(code);
            int i = iActPageConfigSubscribeService.insertActPageConfigSubscribe(vo.getActPageConfigSubscribe());
            return toAjax(i);
        }
      catch (Exception e){
        e.printStackTrace();
      return AjaxResult.error("系统繁忙");
  }
    }
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
