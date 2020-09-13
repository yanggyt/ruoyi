package com.ruoyi.business.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.business.domain.BizProductType;
import com.ruoyi.business.service.IBizProductTypeService;
import com.ruoyi.common.utils.DateUtils;
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
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品Controller
 * 
 * @author ruoyi
 * @date 2020-09-06
 */
@Controller
@RequestMapping("/business/product")
public class BizProductController extends BaseController
{
    private String prefix = "business/product";

    @Autowired
    private IBizProductService bizProductService;

    @Autowired
    private IBizProductTypeService bizProductTypeService;

    @RequiresPermissions("business:product:view")
    @GetMapping()
    public String product(ModelMap mmap)
    {
        mmap.put("productTypeList", bizProductTypeService.selectBizProductTypeList(new BizProductType()));
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("business:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizProduct bizProduct)
    {
        startPage();
        List<BizProduct> list = bizProductService.selectBizProductList(bizProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("business:product:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizProduct bizProduct)
    {
        List<BizProduct> list = bizProductService.selectBizProductList(bizProduct);
        ExcelUtil<BizProduct> util = new ExcelUtil<BizProduct>(BizProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("productTypeList", bizProductTypeService.selectBizProductTypeList(new BizProductType()));
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("business:product:add")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizProduct bizProduct)
    {
        Date now = new Date();
        bizProduct.setCreateBy(ShiroUtils.getLoginName());
        bizProduct.setCreateTime(now);
        bizProduct.setProductCode("BPD" + DateUtils.dateTimeNow("YYYYMMDDHHMMSSSSS"));
        //如果上架设置上架时间
        if (bizProduct.getOnlineStatus() == 1) {
            bizProduct.setOnlineTime(now);
        }
        return toAjax(bizProductService.insertBizProduct(bizProduct));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable(value = "productId", required = true) Long productId, ModelMap mmap)
    {
        BizProduct bizProduct = bizProductService.selectBizProductById(productId);
        mmap.put("bizProduct", bizProduct);
        mmap.put("productTypeList", bizProductTypeService.selectBizProductTypeList(new BizProductType()));
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("business:product:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizProduct bizProduct)
    {
        bizProduct.setUpdateBy(ShiroUtils.getLoginName());
        bizProduct.setUpdateTime(new Date());
        return toAjax(bizProductService.updateBizProduct(bizProduct));
    }

    /**
     * 上架下架产品
     */
    @RequiresPermissions("business:product:edit")
    @Log(title = "产品状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    @ResponseBody
    public AjaxResult editStatus(@PathVariable(value = "productId", required = true) Long productID)
    {
        return toAjax(bizProductService.updateBizProductStatus(productID));
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
        return toAjax(bizProductService.deleteBizProductByIds(ids));
    }
}
