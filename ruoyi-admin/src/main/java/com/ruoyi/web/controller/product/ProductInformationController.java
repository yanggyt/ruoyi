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
import com.ruoyi.product.domain.ProductInformation;
import com.ruoyi.product.service.IProductInformationService;
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
@RequestMapping("/productInformation/information")
public class ProductInformationController extends BaseController
{
    private String prefix = "product/information";

    @Autowired
    private IProductInformationService productInformationService;

    @RequiresPermissions("product:information:view")
    @GetMapping()
    public String information()
    {
        return prefix + "/information";
    }

    /**
     * 查询product列表
     */
    @RequiresPermissions("product:information:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductInformation productInformation)
    {
        startPage();
        List<ProductInformation> list = productInformationService.selectProductInformationList(productInformation);
        return getDataTable(list);
    }

    /**
     * 导出product列表
     */
    @RequiresPermissions("product:information:export")
    @Log(title = "product", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductInformation productInformation)
    {
        List<ProductInformation> list = productInformationService.selectProductInformationList(productInformation);
        ExcelUtil<ProductInformation> util = new ExcelUtil<ProductInformation>(ProductInformation.class);
        return util.exportExcel(list, "information");
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
    @RequiresPermissions("product:information:add")
    @Log(title = "product", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductInformation productInformation)
    {
        return toAjax(productInformationService.insertProductInformation(productInformation));
    }

    /**
     * 修改product
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProductInformation productInformation = productInformationService.selectProductInformationById(id);
        mmap.put("productInformation", productInformation);
        return prefix + "/edit";
    }

    /**
     * 修改保存product
     */
    @RequiresPermissions("product:information:edit")
    @Log(title = "product", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProductInformation productInformation)
    {
        return toAjax(productInformationService.updateProductInformation(productInformation));
    }

    /**
     * 删除product
     */
    @RequiresPermissions("product:information:remove")
    @Log(title = "product", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(productInformationService.deleteProductInformationByIds(ids));
    }
}
