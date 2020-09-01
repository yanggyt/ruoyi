package com.ruoyi.bend.controller;

import com.ruoyi.bend.domain.ScrcuOnlineOrderDetails;
import com.ruoyi.bend.domain.ScrcuOnlineOrders;
import com.ruoyi.bend.service.IScrcuOnlineOrderDetailsService;
import com.ruoyi.bend.service.IScrcuOnlineOrdersService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收单列表Controller
 * 
 * @author bend
 * @date 2020-09-01
 */
@Controller
@RequestMapping("/bend/onlineOrders")
public class ScrcuOnlineOrdersController extends BaseController
{
    private String prefix = "bend/onlineOrders";

    @Autowired
    private IScrcuOnlineOrdersService scrcuOnlineOrdersService;

    @Resource
    private IScrcuOnlineOrderDetailsService orderDetailsService;


    @RequiresPermissions("bend:onlineOrders:view")
    @GetMapping()
    public String onlineOrders()
    {
        return prefix + "/onlineOrders";
    }

    /**
     * 查询收单列表列表
     */
    @RequiresPermissions("bend:onlineOrders:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        startPage();
        List<ScrcuOnlineOrders> list = scrcuOnlineOrdersService.selectScrcuOnlineOrdersList(scrcuOnlineOrders);
        return getDataTable(list);
    }

    /**
     * 导出收单列表列表
     */
    @RequiresPermissions("bend:onlineOrders:export")
    @Log(title = "收单列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        List<ScrcuOnlineOrders> list = scrcuOnlineOrdersService.selectScrcuOnlineOrdersList(scrcuOnlineOrders);
        ExcelUtil<ScrcuOnlineOrders> util = new ExcelUtil<ScrcuOnlineOrders>(ScrcuOnlineOrders.class);
        return util.exportExcel(list, "onlineOrders");
    }

    /**
     * 新增收单列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收单列表
     */
    @RequiresPermissions("bend:onlineOrders:add")
    @Log(title = "收单列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        return toAjax(scrcuOnlineOrdersService.insertScrcuOnlineOrders(scrcuOnlineOrders));
    }

    /**
     * 修改收单列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ScrcuOnlineOrders scrcuOnlineOrders = scrcuOnlineOrdersService.selectScrcuOnlineOrdersById(id);
        mmap.put("scrcuOnlineOrders", scrcuOnlineOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存收单列表
     */
    @RequiresPermissions("bend:onlineOrders:edit")
    @Log(title = "收单列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ScrcuOnlineOrders scrcuOnlineOrders)
    {
        return toAjax(scrcuOnlineOrdersService.updateScrcuOnlineOrders(scrcuOnlineOrders));
    }

    /**
     * 删除收单列表
     */
    @RequiresPermissions("bend:onlineOrders:remove")
    @Log(title = "收单列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(scrcuOnlineOrdersService.deleteScrcuOnlineOrdersByIds(ids));
    }

    /**
     * 收单详情页面
     * @param orderNumber 收单单号
     */
    @RequiresPermissions("bend:onlineOrders:detail")
    @GetMapping("/detail/{orderNumber}")
    public String onlineOrdersDetail(@PathVariable("orderNumber") String orderNumber, ModelMap mmap)
    {
        if (StringUtils.isNotEmpty(orderNumber))
        {
            ScrcuOnlineOrders scrcuOnlineOrders = new ScrcuOnlineOrders();
            scrcuOnlineOrders.setOrderNumber(orderNumber);
            ScrcuOnlineOrders onlineOrders = scrcuOnlineOrdersService.selectScrcuOnlineOrders(scrcuOnlineOrders);
            mmap.put("onlineOrders",onlineOrders);
        }
        return prefix + "/onlineOrderDetails";
    }


    /**
     * 收单详情列表
     * @param detail 收单详情
     * @return 列表
     */
    @RequiresPermissions("bend:onlineOrders:list")
    @PostMapping("/detail/list")
    @ResponseBody
    public TableDataInfo onlineOrdersDetailList(ScrcuOnlineOrderDetails detail)
    {
        startPage();
        List<ScrcuOnlineOrderDetails> list = orderDetailsService.selectScrcuOnlineOrderDetailsList(detail);
        return getDataTable(list);
    }

}
