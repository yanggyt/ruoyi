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
import com.ruoyi.busi.domain.BusiProductStock;
import com.ruoyi.busi.service.IBusiProductStockService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成品库存Controller
 * 
 * @author WangCL
 * @date 2022-01-08
 */
@Controller
@RequestMapping("/busi/productStock")
public class BusiProductStockController extends BaseController
{
    private String prefix = "busi/productStock";

    @Autowired
    private IBusiProductStockService busiProductStockService;

    @RequiresPermissions("busi:productStock:view")
    @GetMapping()
    public String productStock()
    {
        return prefix + "/productStock";
    }

    /**
     * 查询成品库存列表
     */
    @RequiresPermissions("busi:productStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiProductStock busiProductStock)
    {
        startPage();
        List<BusiProductStock> list = busiProductStockService.selectBusiProductStockList(busiProductStock);
        return getDataTable(list);
    }

    /**
     * 导出成品库存列表
     */
    @RequiresPermissions("busi:productStock:export")
    @Log(title = "成品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiProductStock busiProductStock)
    {
        List<BusiProductStock> list = busiProductStockService.selectBusiProductStockList(busiProductStock);
        ExcelUtil<BusiProductStock> util = new ExcelUtil<BusiProductStock>(BusiProductStock.class);
        return util.exportExcel(list, "成品库存数据");
    }

    /**
     * 新增成品库存
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成品库存
     */
    @RequiresPermissions("busi:productStock:add")
    @Log(title = "成品库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiProductStock busiProductStock)
    {
        return toAjax(busiProductStockService.insertBusiProductStock(busiProductStock));
    }

    /**
     * 修改成品库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiProductStock busiProductStock = busiProductStockService.selectBusiProductStockById(id);
        mmap.put("busiProductStock", busiProductStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品库存
     */
    @RequiresPermissions("busi:productStock:edit")
    @Log(title = "成品库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiProductStock busiProductStock)
    {
        return toAjax(busiProductStockService.updateBusiProductStock(busiProductStock));
    }

    /**
     * 删除成品库存
     */
    @RequiresPermissions("busi:productStock:remove")
    @Log(title = "成品库存", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiProductStockService.deleteBusiProductStockByIds(ids));
    }
}
