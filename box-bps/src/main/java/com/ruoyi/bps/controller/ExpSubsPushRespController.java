package com.ruoyi.bps.controller;

import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.service.IExpSubsPushRespService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 快递订阅推送信息增删改查Controller
 * 
 * @author box
 * @date 2021-05-13
 */
@Controller
@RequestMapping("/bps/expsubspushresp")
public class ExpSubsPushRespController extends BaseController
{
    private String prefix = "bps/expsubspushresp";

    @Autowired
    private IExpSubsPushRespService expSubsPushRespService;

    @RequiresPermissions("bps:expsubspushresp:view")
    @GetMapping()
    public String expsubspushresp()
    {
        return prefix + "/expsubspushresp";
    }

    /**
     * 查询快递订阅推送信息列表
     */
    @RequiresPermissions("bps:expsubspushresp:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpSubsPushResp expSubsPushResp)
    {
        startPage();
        List<ExpSubsPushResp> list = expSubsPushRespService.selectExpSubsPushRespList(expSubsPushResp);
        return getDataTable(list);
    }

    /**
     * 导出快递订阅推送信息列表
     */
    @RequiresPermissions("bps:expsubspushresp:export")
    @Log(title = "快递订阅推送信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpSubsPushResp expSubsPushResp)
    {
        List<ExpSubsPushResp> list = expSubsPushRespService.selectExpSubsPushRespList(expSubsPushResp);
        ExcelUtil<ExpSubsPushResp> util = new ExcelUtil<ExpSubsPushResp>(ExpSubsPushResp.class);
        return util.exportExcel(list, "快递订阅推送信息数据");
    }

    /**
     * 新增快递订阅推送信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存快递订阅推送信息
     */
    @RequiresPermissions("bps:expsubspushresp:add")
    @Log(title = "快递订阅推送信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpSubsPushResp expSubsPushResp)
    {
        return toAjax(expSubsPushRespService.insertExpSubsPushResp(expSubsPushResp));
    }

    /**
     * 修改快递订阅推送信息
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        ExpSubsPushResp expSubsPushResp = expSubsPushRespService.selectExpSubsPushRespById(sid);
        mmap.put("expSubsPushResp", expSubsPushResp);
        return prefix + "/edit";
    }

    /**
     * 修改保存快递订阅推送信息
     */
    @RequiresPermissions("bps:expsubspushresp:edit")
    @Log(title = "快递订阅推送信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpSubsPushResp expSubsPushResp)
    {
        return toAjax(expSubsPushRespService.updateExpSubsPushResp(expSubsPushResp));
    }

    /**
     * 删除快递订阅推送信息
     */
    @RequiresPermissions("bps:expsubspushresp:remove")
    @Log(title = "快递订阅推送信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expSubsPushRespService.deleteExpSubsPushRespByIds(ids));
    }
}
