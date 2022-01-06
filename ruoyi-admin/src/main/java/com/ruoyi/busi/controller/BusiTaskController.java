package com.ruoyi.busi.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.busi.domain.BusiTask;
import com.ruoyi.busi.service.IBusiTaskService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产任务Controller
 * 
 * @author WangCL
 * @date 2021-12-30
 */
@Controller
@RequestMapping("/busi/task")
public class BusiTaskController extends BaseController
{
    private String prefix = "busi/task";

    @Autowired
    private IBusiTaskService busiTaskService;

    @RequiresPermissions("busi:task:view")
    @GetMapping()
    public String task()
    {
        return prefix + "/task";
    }

    /**
     * 查询生产任务列表
     */
    @RequiresPermissions("busi:task:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiTask busiTask)
    {
        startPage();
        List<BusiTask> list = busiTaskService.selectBusiTaskList(busiTask);
        return getDataTable(list);
    }

    /**
     * 导出生产任务列表
     */
    @RequiresPermissions("busi:task:export")
    @Log(title = "生产任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiTask busiTask)
    {
        List<BusiTask> list = busiTaskService.selectBusiTaskList(busiTask);
        ExcelUtil<BusiTask> util = new ExcelUtil<BusiTask>(BusiTask.class);
        return util.exportExcel(list, "生产任务数据");
    }

    /**
     * 新增生产任务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存生产任务
     */
    @RequiresPermissions("busi:task:add")
    @Log(title = "生产任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiTask busiTask)
    {
        return toAjax(busiTaskService.insertBusiTask(busiTask));
    }

    /**
     * 修改生产任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiTask busiTask = busiTaskService.selectBusiTaskById(id);
        mmap.put("busiTask", busiTask);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产任务
     */
    @RequiresPermissions("busi:task:edit")
    @Log(title = "生产任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiTask busiTask)
    {
        return toAjax(busiTaskService.updateBusiTask(busiTask));
    }

    /**
     * 删除生产任务
     */
    @RequiresPermissions("busi:task:remove")
    @Log(title = "生产任务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiTaskService.deleteBusiTaskByIds(ids));
    }


    /**
     * 通过订单ID查询产品需求和已分配数量信息
     */
    @PostMapping("/selectProductRequire")
    @ResponseBody
    public AjaxResult selectProductRequire(@RequestParam(name = "orderId",required = false) String orderId)
    {
        List<Map> list = busiTaskService.selectProductRequire(orderId);
        return success(list);
    }
}
