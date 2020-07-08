package com.ruoyi.business.controller;

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
import com.ruoyi.business.domain.BProduct;
import com.ruoyi.business.service.IBProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品Controller
 *
 * @author anjie
 * @date 2020-06-21
 */
@Controller
@RequestMapping("/business/product")
public class BProductController extends BaseController
{
    private String prefix = "business/product";

    @Autowired
    private IBProductService bProductService;

    @RequiresPermissions("business:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("business:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BProduct bProduct)
    {
        startPage();
        List<BProduct> list = bProductService.selectBProductList(bProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("business:product:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BProduct bProduct)
    {
        List<BProduct> list = bProductService.selectBProductList(bProduct);
        ExcelUtil<BProduct> util = new ExcelUtil<BProduct>(BProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("business:product:add")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BProduct bProduct)
    {
        return toAjax(bProductService.insertBProduct(bProduct));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BProduct bProduct = bProductService.selectBProductById(id);
        mmap.put("bProduct", bProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("business:product:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BProduct bProduct)
    {
        return toAjax(bProductService.updateBProduct(bProduct));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("business:product:remove")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bProductService.deleteBProductByIds(ids));
    }
}
