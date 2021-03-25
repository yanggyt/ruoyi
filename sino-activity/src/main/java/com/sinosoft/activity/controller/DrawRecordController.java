package com.sinosoft.activity.controller;

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
import com.sinosoft.activity.domain.DrawRecord;
import com.sinosoft.activity.service.IDrawRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抽奖记录信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
@Controller
@RequestMapping("/activity/record")
public class DrawRecordController extends BaseController
{
    private String prefix = "activity/record";

    @Autowired
    private IDrawRecordService drawRecordService;

    @RequiresPermissions("activity:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询抽奖记录信息列表
     */
    @RequiresPermissions("activity:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawRecord drawRecord)
    {
        startPage();
        List<DrawRecord> list = drawRecordService.selectDrawRecordList(drawRecord);
        return getDataTable(list);
    }

    /**
     * 导出抽奖记录信息列表
     */
    @RequiresPermissions("activity:record:export")
    @Log(title = "抽奖记录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawRecord drawRecord)
    {
        List<DrawRecord> list = drawRecordService.selectDrawRecordList(drawRecord);
        ExcelUtil<DrawRecord> util = new ExcelUtil<DrawRecord>(DrawRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增抽奖记录信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存抽奖记录信息
     */
    @RequiresPermissions("activity:record:add")
    @Log(title = "抽奖记录信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawRecord drawRecord)
    {
        return toAjax(drawRecordService.insertDrawRecord(drawRecord));
    }

    /**
     * 修改抽奖记录信息
     */
    @GetMapping("/edit/{DRAWRECORDID}")
    public String edit(@PathVariable("DRAWRECORDID") String DRAWRECORDID, ModelMap mmap)
    {
        DrawRecord drawRecord = drawRecordService.selectDrawRecordById(DRAWRECORDID);
        mmap.put("drawRecord", drawRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存抽奖记录信息
     */
    @RequiresPermissions("activity:record:edit")
    @Log(title = "抽奖记录信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawRecord drawRecord)
    {
        return toAjax(drawRecordService.updateDrawRecord(drawRecord));
    }

    /**
     * 删除抽奖记录信息
     */
    @RequiresPermissions("activity:record:remove")
    @Log(title = "抽奖记录信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawRecordService.deleteDrawRecordByIds(ids));
    }
}
