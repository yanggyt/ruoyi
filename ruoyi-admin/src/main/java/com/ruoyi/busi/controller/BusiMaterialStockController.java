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
import com.ruoyi.busi.domain.BusiMaterialStock;
import com.ruoyi.busi.service.IBusiMaterialStockService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料库存Controller
 * 
 * @author WangCL
 * @date 2021-12-25
 */
@Controller
@RequestMapping("/busi/materialtock")
public class BusiMaterialStockController extends BaseController
{
    private String prefix = "busi/materialtock";

    @Autowired
    private IBusiMaterialStockService busiMaterialStockService;

    @RequiresPermissions("busi:materialtock:view")
    @GetMapping()
    public String materialtock()
    {
        return prefix + "/materialtock";
    }

    /**
     * 查询物料库存列表
     */
    @RequiresPermissions("busi:materialtock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiMaterialStock busiMaterialStock)
    {
        startPage();
        List<BusiMaterialStock> list = busiMaterialStockService.selectBusiMaterialStockList(busiMaterialStock);
        return getDataTable(list);
    }

    /**
     * 导出物料库存列表
     */
    @RequiresPermissions("busi:materialtock:export")
    @Log(title = "物料库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiMaterialStock busiMaterialStock)
    {
        List<BusiMaterialStock> list = busiMaterialStockService.selectBusiMaterialStockList(busiMaterialStock);
        ExcelUtil<BusiMaterialStock> util = new ExcelUtil<BusiMaterialStock>(BusiMaterialStock.class);
        return util.exportExcel(list, "物料库存数据");
    }

    /**
     * 新增物料库存
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物料库存
     */
    @RequiresPermissions("busi:materialtock:add")
    @Log(title = "物料库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiMaterialStock busiMaterialStock)
    {
        return toAjax(busiMaterialStockService.insertBusiMaterialStock(busiMaterialStock));
    }

    /**
     * 修改物料库存
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiMaterialStock busiMaterialStock = busiMaterialStockService.selectBusiMaterialStockById(id);
        mmap.put("busiMaterialStock", busiMaterialStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料库存
     */
    @RequiresPermissions("busi:materialtock:edit")
    @Log(title = "物料库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiMaterialStock busiMaterialStock)
    {
        return toAjax(busiMaterialStockService.updateBusiMaterialStock(busiMaterialStock));
    }

    /**
     * 删除物料库存
     */
    @RequiresPermissions("busi:materialtock:remove")
    @Log(title = "物料库存", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiMaterialStockService.deleteBusiMaterialStockByIds(ids));
    }
}
