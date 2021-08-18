package com.ruoyi.bps.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bps.domain.ExpTopgpLog;
import com.ruoyi.bps.service.IExpTopgpLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * ERP订阅推送日志Controller
 * 
 * @author Bo
 * @date 2021-08-11
 */
@Controller
@RequestMapping("/bps/expTopgpLog")
public class ExpTopgpLogController extends BaseController
{
    private String prefix = "bps/expTopgpLog";

    @Autowired
    private IExpTopgpLogService expTopgpLogService;

    @RequiresPermissions("bps:expTopgpLog:view")
    @GetMapping()
    public String expTopgpLog()
    {
        return prefix + "/expTopgpLog";
    }

    /**
     * 查询ERP订阅推送日志列表
     */
    @RequiresPermissions("bps:expTopgpLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpTopgpLog expTopgpLog)
    {
        startPage();
        List<ExpTopgpLog> list = expTopgpLogService.selectExpTopgpLogList(expTopgpLog);
        return getDataTable(list);
    }

    /**
     * 导出ERP订阅推送日志列表
     */
    @RequiresPermissions("bps:expTopgpLog:export")
    @Log(title = "ERP订阅推送日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpTopgpLog expTopgpLog)
    {
        List<ExpTopgpLog> list = expTopgpLogService.selectExpTopgpLogList(expTopgpLog);
        ExcelUtil<ExpTopgpLog> util = new ExcelUtil<ExpTopgpLog>(ExpTopgpLog.class);
        return util.exportExcel(list, "ERP订阅推送日志数据");
    }

    /**
     * 新增ERP订阅推送日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存ERP订阅推送日志
     */
    @RequiresPermissions("bps:expTopgpLog:add")
    @Log(title = "ERP订阅推送日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpTopgpLog expTopgpLog)
    {
        return toAjax(expTopgpLogService.insertExpTopgpLog(expTopgpLog));
    }

    /**
     * 修改ERP订阅推送日志
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        ExpTopgpLog expTopgpLog = expTopgpLogService.selectExpTopgpLogBySid(sid);
        mmap.put("expTopgpLog", expTopgpLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存ERP订阅推送日志
     */
    @RequiresPermissions("bps:expTopgpLog:edit")
    @Log(title = "ERP订阅推送日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpTopgpLog expTopgpLog)
    {
        return toAjax(expTopgpLogService.updateExpTopgpLog(expTopgpLog));
    }

    /**
     * 删除ERP订阅推送日志
     */
    @RequiresPermissions("bps:expTopgpLog:remove")
    @Log(title = "ERP订阅推送日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expTopgpLogService.deleteExpTopgpLogBySids(ids));
    }
}
