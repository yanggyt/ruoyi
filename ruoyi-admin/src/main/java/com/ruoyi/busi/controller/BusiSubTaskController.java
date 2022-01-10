package com.ruoyi.busi.controller;

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
import com.ruoyi.busi.domain.BusiSubTask;
import com.ruoyi.busi.service.IBusiSubTaskService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品子任务Controller
 * 
 * @author WangCL
 * @date 2022-01-10
 */
@Controller
@RequestMapping("/busi/subtask")
public class BusiSubTaskController extends BaseController
{
    private String prefix = "busi/subtask";

    @Autowired
    private IBusiSubTaskService busiSubTaskService;

    @RequiresPermissions("busi:subtask:view")
    @GetMapping()
    public String subtask()
    {
        return prefix + "/subtask";
    }

    /**
     * 查询产品子任务列表
     */
    @RequiresPermissions("busi:subtask:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiSubTask busiSubTask)
    {
        startPage();
        List<BusiSubTask> list = busiSubTaskService.selectBusiSubTaskList(busiSubTask);
        return getDataTable(list);
    }

    /**
     * 导出产品子任务列表
     */
    @RequiresPermissions("busi:subtask:export")
    @Log(title = "产品子任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiSubTask busiSubTask)
    {
        List<BusiSubTask> list = busiSubTaskService.selectBusiSubTaskList(busiSubTask);
        ExcelUtil<BusiSubTask> util = new ExcelUtil<BusiSubTask>(BusiSubTask.class);
        return util.exportExcel(list, "产品子任务数据");
    }

    /**
     * 新增产品子任务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品子任务
     */
    @RequiresPermissions("busi:subtask:add")
    @Log(title = "产品子任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiSubTask busiSubTask)
    {
        return toAjax(busiSubTaskService.insertBusiSubTask(busiSubTask));
    }

    /**
     * 修改产品子任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiSubTask busiSubTask = busiSubTaskService.selectBusiSubTaskById(id);
        mmap.put("busiSubTask", busiSubTask);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品子任务
     */
    @RequiresPermissions("busi:subtask:edit")
    @Log(title = "产品子任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiSubTask busiSubTask)
    {
        return toAjax(busiSubTaskService.updateBusiSubTask(busiSubTask));
    }

    /**
     * 删除产品子任务
     */
    @RequiresPermissions("busi:subtask:remove")
    @Log(title = "产品子任务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiSubTaskService.deleteBusiSubTaskByIds(ids));
    }
}
