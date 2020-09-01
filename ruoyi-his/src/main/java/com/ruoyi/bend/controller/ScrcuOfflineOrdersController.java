package com.ruoyi.bend.controller;

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
import com.ruoyi.bend.domain.ScrcuOfflineOrders;
import com.ruoyi.bend.service.IScrcuOfflineOrdersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线下订单Controller
 * 
 * @author bend
 * @date 2020-09-01
 */
@Controller
@RequestMapping("/bend/offlineOrders")
public class ScrcuOfflineOrdersController extends BaseController
{
    private String prefix = "bend/offlineOrders";

    @Autowired
    private IScrcuOfflineOrdersService scrcuOfflineOrdersService;

    @RequiresPermissions("bend:offlineOrders:view")
    @GetMapping()
    public String offlineOrders()
    {
        return prefix + "/offlineOrders";
    }

    /**
     * 查询线下订单列表
     */
    @RequiresPermissions("bend:offlineOrders:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        startPage();
        List<ScrcuOfflineOrders> list = scrcuOfflineOrdersService.selectScrcuOfflineOrdersList(scrcuOfflineOrders);
        return getDataTable(list);
    }

    /**
     * 导出线下订单列表
     */
    @RequiresPermissions("bend:offlineOrders:export")
    @Log(title = "线下订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        List<ScrcuOfflineOrders> list = scrcuOfflineOrdersService.selectScrcuOfflineOrdersList(scrcuOfflineOrders);
        ExcelUtil<ScrcuOfflineOrders> util = new ExcelUtil<ScrcuOfflineOrders>(ScrcuOfflineOrders.class);
        return util.exportExcel(list, "offlineOrders");
    }

    /**
     * 新增线下订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存线下订单
     */
    @RequiresPermissions("bend:offlineOrders:add")
    @Log(title = "线下订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        return toAjax(scrcuOfflineOrdersService.insertScrcuOfflineOrders(scrcuOfflineOrders));
    }

    /**
     * 修改线下订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ScrcuOfflineOrders scrcuOfflineOrders = scrcuOfflineOrdersService.selectScrcuOfflineOrdersById(id);
        mmap.put("scrcuOfflineOrders", scrcuOfflineOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存线下订单
     */
    @RequiresPermissions("bend:offlineOrders:edit")
    @Log(title = "线下订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScrcuOfflineOrders scrcuOfflineOrders)
    {
        return toAjax(scrcuOfflineOrdersService.updateScrcuOfflineOrders(scrcuOfflineOrders));
    }

    /**
     * 删除线下订单
     */
    @RequiresPermissions("bend:offlineOrders:remove")
    @Log(title = "线下订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scrcuOfflineOrdersService.deleteScrcuOfflineOrdersByIds(ids));
    }
}
