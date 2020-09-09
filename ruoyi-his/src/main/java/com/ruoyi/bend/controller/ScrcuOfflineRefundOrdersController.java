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
import com.ruoyi.bend.domain.ScrcuOfflineRefundOrders;
import com.ruoyi.bend.service.IScrcuOfflineRefundOrdersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扫码退款Controller
 * 
 * @author bend
 * @date 2020-09-01
 */
@Controller
@RequestMapping("/bend/offlineRefund")
public class ScrcuOfflineRefundOrdersController extends BaseController
{
    private String prefix = "bend/offlineRefund";

    @Autowired
    private IScrcuOfflineRefundOrdersService scrcuOfflineRefundOrdersService;

    @RequiresPermissions("bend:offlineRefund:view")
    @GetMapping()
    public String offlineRefund()
    {
        return prefix + "/offlineRefund";
    }

    /**
     * 查询扫码退款列表
     */
    @RequiresPermissions("bend:offlineRefund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        startPage();
        List<ScrcuOfflineRefundOrders> list = scrcuOfflineRefundOrdersService.selectScrcuOfflineRefundOrdersList(scrcuOfflineRefundOrders);
        return getDataTable(list);
    }

    /**
     * 导出扫码退款列表
     */
    @RequiresPermissions("bend:offlineRefund:export")
    @Log(title = "扫码退款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        List<ScrcuOfflineRefundOrders> list = scrcuOfflineRefundOrdersService.selectScrcuOfflineRefundOrdersList(scrcuOfflineRefundOrders);
        ExcelUtil<ScrcuOfflineRefundOrders> util = new ExcelUtil<ScrcuOfflineRefundOrders>(ScrcuOfflineRefundOrders.class);
        return util.exportExcel(list, "offlineRefund");
    }

    /**
     * 新增扫码退款
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存扫码退款
     */
    @RequiresPermissions("bend:offlineRefund:add")
    @Log(title = "扫码退款", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        return toAjax(scrcuOfflineRefundOrdersService.insertScrcuOfflineRefundOrders(scrcuOfflineRefundOrders));
    }

    /**
     * 修改扫码退款
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ScrcuOfflineRefundOrders scrcuOfflineRefundOrders = scrcuOfflineRefundOrdersService.selectScrcuOfflineRefundOrdersById(id);
        mmap.put("scrcuOfflineRefundOrders", scrcuOfflineRefundOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存扫码退款
     */
    @RequiresPermissions("bend:offlineRefund:edit")
    @Log(title = "扫码退款", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScrcuOfflineRefundOrders scrcuOfflineRefundOrders)
    {
        return toAjax(scrcuOfflineRefundOrdersService.updateScrcuOfflineRefundOrders(scrcuOfflineRefundOrders));
    }

    /**
     * 删除扫码退款
     */
    @RequiresPermissions("bend:offlineRefund:remove")
    @Log(title = "扫码退款", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scrcuOfflineRefundOrdersService.deleteScrcuOfflineRefundOrdersByIds(ids));
    }
}
