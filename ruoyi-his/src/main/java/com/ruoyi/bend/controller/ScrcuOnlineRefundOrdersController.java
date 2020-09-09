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
import com.ruoyi.bend.domain.ScrcuOnlineRefundOrders;
import com.ruoyi.bend.service.IScrcuOnlineRefundOrdersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 退款订单Controller
 * 
 * @author bend
 * @date 2020-09-01
 */
@Controller
@RequestMapping("/bend/onlineRefund")
public class ScrcuOnlineRefundOrdersController extends BaseController
{
    private String prefix = "bend/onlineRefund";

    @Autowired
    private IScrcuOnlineRefundOrdersService scrcuOnlineRefundOrdersService;

    @RequiresPermissions("bend:onlineRefund:view")
    @GetMapping()
    public String onlineRefund()
    {
        return prefix + "/onlineRefund";
    }

    /**
     * 查询退款订单列表
     */
    @RequiresPermissions("bend:onlineRefund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        startPage();
        List<ScrcuOnlineRefundOrders> list = scrcuOnlineRefundOrdersService.selectScrcuOnlineRefundOrdersList(scrcuOnlineRefundOrders);
        return getDataTable(list);
    }

    /**
     * 导出退款订单列表
     */
    @RequiresPermissions("bend:onlineRefund:export")
    @Log(title = "退款订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        List<ScrcuOnlineRefundOrders> list = scrcuOnlineRefundOrdersService.selectScrcuOnlineRefundOrdersList(scrcuOnlineRefundOrders);
        ExcelUtil<ScrcuOnlineRefundOrders> util = new ExcelUtil<ScrcuOnlineRefundOrders>(ScrcuOnlineRefundOrders.class);
        return util.exportExcel(list, "onlineRefund");
    }

    /**
     * 新增退款订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存退款订单
     */
    @RequiresPermissions("bend:onlineRefund:add")
    @Log(title = "退款订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        return toAjax(scrcuOnlineRefundOrdersService.insertScrcuOnlineRefundOrders(scrcuOnlineRefundOrders));
    }

    /**
     * 修改退款订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ScrcuOnlineRefundOrders scrcuOnlineRefundOrders = scrcuOnlineRefundOrdersService.selectScrcuOnlineRefundOrdersById(id);
        mmap.put("scrcuOnlineRefundOrders", scrcuOnlineRefundOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存退款订单
     */
    @RequiresPermissions("bend:onlineRefund:edit")
    @Log(title = "退款订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScrcuOnlineRefundOrders scrcuOnlineRefundOrders)
    {
        return toAjax(scrcuOnlineRefundOrdersService.updateScrcuOnlineRefundOrders(scrcuOnlineRefundOrders));
    }

    /**
     * 删除退款订单
     */
    @RequiresPermissions("bend:onlineRefund:remove")
    @Log(title = "退款订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scrcuOnlineRefundOrdersService.deleteScrcuOnlineRefundOrdersByIds(ids));
    }
}
