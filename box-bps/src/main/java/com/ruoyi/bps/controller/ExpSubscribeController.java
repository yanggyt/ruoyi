package com.ruoyi.bps.controller;

import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.service.IExpSubsPushApiService;
import com.ruoyi.bps.service.IExpSubscribeService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.kuaidi100.sdk.response.SubscribeResp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快递订阅Controller
 * 
 * @author box
 * @date 2021-05-20
 */
@Controller
@RequestMapping("/bps/subscribe")
public class ExpSubscribeController extends BaseController
{
    private String prefix = "bps/subscribe";

    @Autowired
    private IExpSubscribeService expSubscribeService;

    @Autowired
    private IExpSubsPushApiService iExpSubsPushApiService;

    @RequiresPermissions("bps:subscribe:view")
    @GetMapping()
    public String subscribe()
    {
        return prefix + "/subscribe";
    }

    /**
     * 订阅快递
     */
    @CrossOrigin
    @RequestMapping("/subscribe")
    @ResponseBody
    public SubscribeResp ExpressSubscribe(@RequestBody ExpSubscribe expSubscribe) {
       return iExpSubsPushApiService.ExpressSubscribe(expSubscribe);
    }


    /**
     * 查询快递订阅列表
     */
    @RequiresPermissions("bps:subscribe:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpSubscribe expSubscribe)
    {
        startPage();
        List<ExpSubscribe> list = new ArrayList<>();
        if(expSubscribe.getNumber().contains(",")){
            List<String> number= Arrays.asList(expSubscribe.getNumber().split(","));
            list=expSubscribeService.selectExpSubsPushRespByNumber(number);
        }
        else {
            list = expSubscribeService.selectExpSubscribeList(expSubscribe);
        }
        return getDataTable(list);
    }

    /**
     * 导出快递订阅列表
     */
    @RequiresPermissions("bps:subscribe:export")
    @Log(title = "快递订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpSubscribe expSubscribe)
    {
        List<ExpSubscribe> list = expSubscribeService.selectExpSubscribeList(expSubscribe);
        ExcelUtil<ExpSubscribe> util = new ExcelUtil<ExpSubscribe>(ExpSubscribe.class);
        return util.exportExcel(list, "快递订阅数据");
    }

    /**
     * 新增快递订阅
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存快递订阅
     */
    @RequiresPermissions("bps:subscribe:add")
    @Log(title = "快递订阅", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpSubscribe expSubscribe)
    {
        return toAjax(expSubscribeService.insertExpSubscribe(expSubscribe));
    }

    /**
     * 修改快递订阅
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        ExpSubscribe expSubscribe = expSubscribeService.selectExpSubscribeById(sid);
        mmap.put("expSubscribe", expSubscribe);
        return prefix + "/edit";
    }

    /**
     * 修改保存快递订阅
     */
    @RequiresPermissions("bps:subscribe:edit")
    @Log(title = "快递订阅", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpSubscribe expSubscribe)
    {
        return toAjax(expSubscribeService.updateExpSubscribe(expSubscribe));
    }

    /**
     * 删除快递订阅
     */
    @RequiresPermissions("bps:subscribe:remove")
    @Log(title = "快递订阅", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expSubscribeService.deleteExpSubscribeByIds(ids));
    }
}
