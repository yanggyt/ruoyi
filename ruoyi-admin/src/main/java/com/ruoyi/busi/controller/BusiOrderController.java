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
import com.ruoyi.busi.domain.BusiOrder;
import com.ruoyi.busi.service.IBusiOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author WangCL
 * @date 2021-12-21
 */
@Controller
@RequestMapping("/busi/order")
public class BusiOrderController extends BaseController
{
    private String prefix = "busi/order";

    @Autowired
    private IBusiOrderService busiOrderService;

    @RequiresPermissions("busi:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("busi:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiOrder busiOrder)
    {
        startPage();
        List<BusiOrder> list = busiOrderService.selectBusiOrderList(busiOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("busi:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiOrder busiOrder)
    {
        List<BusiOrder> list = busiOrderService.selectBusiOrderList(busiOrder);
        ExcelUtil<BusiOrder> util = new ExcelUtil<BusiOrder>(BusiOrder.class);
        return util.exportExcel(list, "订单数据");
    }

    /**
     * 新增订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单
     */
    @RequiresPermissions("busi:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiOrder busiOrder)
    {
        return toAjax(busiOrderService.insertBusiOrder(busiOrder));
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiOrder busiOrder = busiOrderService.selectBusiOrderById(id);
        mmap.put("busiOrder", busiOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("busi:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiOrder busiOrder)
    {
        return toAjax(busiOrderService.updateBusiOrder(busiOrder));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("busi:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiOrderService.deleteBusiOrderByIds(ids));
    }
}
