package com.sinosoft.activity.controller;

import java.util.List;

import com.sinosoft.activity.domain.DrawAwardRecord;
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
import com.sinosoft.activity.service.IDrawAwardRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录发奖信息Controller
 * 
 * @author dy
 * @date 2021-03-26
 */
@Controller
@RequestMapping("/activity/award/record")
public class DrawAwardRecordController extends BaseController
{
    private String prefix = "activity/awardRecord";

    @Autowired
    private IDrawAwardRecordService drawAwardRecordService;

    @RequiresPermissions("activity:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询记录发奖信息列表
     */
    @RequiresPermissions("activity:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawAwardRecord drawAwardRecord)
    {
        startPage();
        List<DrawAwardRecord> list = drawAwardRecordService.selectDrawAwardRecordList(drawAwardRecord);
        return getDataTable(list);
    }

    /**
     * 导出记录发奖信息列表
     */
    @RequiresPermissions("activity:record:export")
    @Log(title = "记录发奖信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawAwardRecord drawAwardRecord)
    {
        List<DrawAwardRecord> list = drawAwardRecordService.selectDrawAwardRecordList(drawAwardRecord);
        ExcelUtil<DrawAwardRecord> util = new ExcelUtil<DrawAwardRecord>(DrawAwardRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增记录发奖信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存记录发奖信息
     */
    @RequiresPermissions("activity:record:add")
    @Log(title = "记录发奖信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawAwardRecord drawAwardRecord)
    {
        return toAjax(drawAwardRecordService.insertDrawAwardRecord(drawAwardRecord));
    }

    /**
     * 修改记录发奖信息
     */
    @GetMapping("/edit/{AWARDRECORDID}")
    public String edit(@PathVariable("AWARDRECORDID") String AWARDRECORDID, ModelMap mmap)
    {
        DrawAwardRecord drawAwardRecord = drawAwardRecordService.selectDrawAwardRecordById(AWARDRECORDID);
        mmap.put("drawAwardRecord", drawAwardRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存记录发奖信息
     */
    @RequiresPermissions("activity:record:edit")
    @Log(title = "记录发奖信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawAwardRecord drawAwardRecord)
    {
        return toAjax(drawAwardRecordService.updateDrawAwardRecord(drawAwardRecord));
    }

    /**
     * 删除记录发奖信息
     */
    @RequiresPermissions("activity:record:remove")
    @Log(title = "记录发奖信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawAwardRecordService.deleteDrawAwardRecordByIds(ids));
    }
}
