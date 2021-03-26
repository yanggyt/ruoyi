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
import com.sinosoft.activity.domain.DrawTaskConsume;
import com.sinosoft.activity.service.IDrawTaskConsumeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抽奖次数消费信息Controller
 * 
 * @author dy
 * @date 2021-03-26
 */
@Controller
@RequestMapping("/activity/consume")
public class DrawTaskConsumeController extends BaseController
{
    private String prefix = "activity/consume";

    @Autowired
    private IDrawTaskConsumeService drawTaskConsumeService;

    @RequiresPermissions("activity:consume:view")
    @GetMapping()
    public String consume()
    {
        return prefix + "/consume";
    }

    /**
     * 查询抽奖次数消费信息列表
     */
    @RequiresPermissions("activity:consume:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawTaskConsume drawTaskConsume)
    {
        startPage();
        List<DrawTaskConsume> list = drawTaskConsumeService.selectDrawTaskConsumeList(drawTaskConsume);
        return getDataTable(list);
    }

    /**
     * 导出抽奖次数消费信息列表
     */
    @RequiresPermissions("activity:consume:export")
    @Log(title = "抽奖次数消费信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawTaskConsume drawTaskConsume)
    {
        List<DrawTaskConsume> list = drawTaskConsumeService.selectDrawTaskConsumeList(drawTaskConsume);
        ExcelUtil<DrawTaskConsume> util = new ExcelUtil<DrawTaskConsume>(DrawTaskConsume.class);
        return util.exportExcel(list, "consume");
    }

    /**
     * 新增抽奖次数消费信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存抽奖次数消费信息
     */
    @RequiresPermissions("activity:consume:add")
    @Log(title = "抽奖次数消费信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawTaskConsume drawTaskConsume)
    {
        return toAjax(drawTaskConsumeService.insertDrawTaskConsume(drawTaskConsume));
    }

    /**
     * 修改抽奖次数消费信息
     */
    @GetMapping("/edit/{TASKCONSUMEID}")
    public String edit(@PathVariable("TASKCONSUMEID") String TASKCONSUMEID, ModelMap mmap)
    {
        DrawTaskConsume drawTaskConsume = drawTaskConsumeService.selectDrawTaskConsumeById(TASKCONSUMEID);
        mmap.put("drawTaskConsume", drawTaskConsume);
        return prefix + "/edit";
    }

    /**
     * 修改保存抽奖次数消费信息
     */
    @RequiresPermissions("activity:consume:edit")
    @Log(title = "抽奖次数消费信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawTaskConsume drawTaskConsume)
    {
        return toAjax(drawTaskConsumeService.updateDrawTaskConsume(drawTaskConsume));
    }

    /**
     * 删除抽奖次数消费信息
     */
    @RequiresPermissions("activity:consume:remove")
    @Log(title = "抽奖次数消费信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawTaskConsumeService.deleteDrawTaskConsumeByIds(ids));
    }
}
