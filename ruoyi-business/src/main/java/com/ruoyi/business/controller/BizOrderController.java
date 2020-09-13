package com.ruoyi.business.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
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
import com.ruoyi.business.domain.BizOrder;
import com.ruoyi.business.service.IBizOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
@Controller
@RequestMapping("/business/order")
public class BizOrderController extends BaseController
{
    private String prefix = "business/order";

    @Autowired
    private IBizOrderService bizOrderService;

    @RequiresPermissions("business:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("business:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizOrder bizOrder)
    {
        startPage();
        List<BizOrder> list = bizOrderService.selectBizOrderList(bizOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("business:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizOrder bizOrder)
    {
        List<BizOrder> list = bizOrderService.selectBizOrderList(bizOrder);
        ExcelUtil<BizOrder> util = new ExcelUtil<BizOrder>(BizOrder.class);
        return util.exportExcel(list, "order");
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
    @RequiresPermissions("business:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizOrder bizOrder)
    {
        return toAjax(bizOrderService.insertBizOrder(bizOrder));
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id", required = true)  Long id, ModelMap mmap)
    {
        BizOrder bizOrder = bizOrderService.selectBizOrderById(id);
        mmap.put("bizOrder", bizOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("business:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizOrder bizOrder)
    {
        return toAjax(bizOrderService.updateBizOrder(bizOrder));
    }

    /**
     * 订单发货
     */
    @RequiresPermissions("business:order:edit")
    @Log(title = "订单发货", businessType = BusinessType.UPDATE)
    @PostMapping("/deliver")
    @ResponseBody
    public AjaxResult editDeliver(Long orderID)
    {
        return toAjax(bizOrderService.deliverBizOrder(orderID));
    }

    /**
     * 订单修改地址/备注
     */
    @RequiresPermissions("business:order:edit")
    @Log(title = "订单修改地址/备注", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAddressOrRemark")
    @ResponseBody
    public AjaxResult updateAddressOrRemark(Long orderID, String content, Integer type)
    {
        BizOrder bizOrder = bizOrderService.selectBizOrderById(orderID);
        if (bizOrder == null) return toAjax(0);
        if (type == 0) {
            bizOrder.setAddressDetail(content);
        } else {
            bizOrder.setRemark(content);
        }
        bizOrder.setUpdateBy(ShiroUtils.getLoginName());
        bizOrder.setUpdateTime(new Date());
        return toAjax(bizOrderService.updateBizOrder(bizOrder));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("business:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bizOrderService.deleteBizOrderByIds(ids));
    }
}
