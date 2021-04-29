package com.ruoyi.web.controller.product;

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
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.service.IProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * productController
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/product/product")
public class ProductController extends BaseController
{
    private String prefix = "product/product";

    @Autowired
    private IProductService productService;

    @RequiresPermissions("product:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询product列表
     */
    @RequiresPermissions("product:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Product product)
    {
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    /**
     * 导出product列表
     */
    @RequiresPermissions("product:product:export")
    @Log(title = "product", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Product product)
    {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增product
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存product
     */
    @RequiresPermissions("product:product:add")
    @Log(title = "product", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Product product)
    {
        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改product
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Product product = productService.selectProductById(id);
        mmap.put("product", product);
        return prefix + "/edit";
    }

    /**
     * 修改保存product
     */
    @RequiresPermissions("product:product:edit")
    @Log(title = "product", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Product product)
    {
        return toAjax(productService.updateProduct(product));
    }

    /**
     * 删除product
     */
    @RequiresPermissions("product:product:remove")
    @Log(title = "product", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(productService.deleteProductByIds(ids));
    }
}
