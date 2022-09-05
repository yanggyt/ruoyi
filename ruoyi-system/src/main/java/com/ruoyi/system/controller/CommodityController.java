package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Commodity;
import com.ruoyi.system.service.ICommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CC码Controller
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
@Controller
@RequestMapping("/system/commodity")
public class CommodityController extends BaseController
{
    private String prefix = "system/commodity";

    @Autowired
    private ICommodityService commodityService;

    @RequiresPermissions("system:commodity:view")
    @GetMapping()
    public String commodity()
    {
        return prefix + "/commodity";
    }

    /**
     * 查询CC码列表
     */
    @RequiresPermissions("system:commodity:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Commodity commodity)
    {
        startPage();
        List<Commodity> list = commodityService.selectCommodityList(commodity);
        return getDataTable(list);
    }

    /**
     * 导出CC码列表
     */
    @RequiresPermissions("system:commodity:export")
    @Log(title = "CC码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Commodity commodity)
    {
        List<Commodity> list = commodityService.selectCommodityList(commodity);
        ExcelUtil<Commodity> util = new ExcelUtil<Commodity>(Commodity.class);
        return util.exportExcel(list, "CC码数据");
    }

    /**
     * 新增CC码
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存CC码
     */
    @RequiresPermissions("system:commodity:add")
    @Log(title = "CC码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Commodity commodity)
    {
        return toAjax(commodityService.insertCommodity(commodity));
    }

    /**
     * 修改CC码
     */
    @GetMapping("/edit/{commodityNo}")
    public String edit(@PathVariable("commodityNo") Long commodityNo, ModelMap mmap)
    {
        Commodity commodity = commodityService.selectCommodityByCommodityNo(commodityNo);
        mmap.put("commodity", commodity);
        return prefix + "/edit";
    }

    /**
     * 修改保存CC码
     */
    @RequiresPermissions("system:commodity:edit")
    @Log(title = "CC码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Commodity commodity)
    {
        return toAjax(commodityService.updateCommodity(commodity));
    }

    /**
     * 删除CC码
     */
    @RequiresPermissions("system:commodity:remove")
    @Log(title = "CC码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commodityService.deleteCommodityByCommodityNos(ids));
    }
}
