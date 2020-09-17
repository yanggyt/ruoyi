package com.ruoyi.business.controller;

import java.util.Date;
import java.util.List;

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
import com.ruoyi.business.domain.BizProductType;
import com.ruoyi.business.service.IBizProductTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品分类Controller
 * 
 * @author ruoyi
 * @date 2020-09-05
 */
@Controller
@RequestMapping("/business/productType")
public class BizProductTypeController extends BaseController
{
    private String prefix = "business/productType";

    @Autowired
    private IBizProductTypeService bizProductTypeService;

    @RequiresPermissions("business:productType:view")
    @GetMapping()
    public String productType()
    {
        return prefix + "/productType";
    }

    /**
     * 查询产品分类列表
     */
    @RequiresPermissions("business:productType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizProductType bizProductType)
    {
        startPage();
        List<BizProductType> list = bizProductTypeService.selectBizProductTypeList(bizProductType);
        return getDataTable(list);
    }

    /**
     * 导出产品分类列表
     */
    @RequiresPermissions("business:productType:export")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizProductType bizProductType)
    {
        List<BizProductType> list = bizProductTypeService.selectBizProductTypeList(bizProductType);
        ExcelUtil<BizProductType> util = new ExcelUtil<BizProductType>(BizProductType.class);
        return util.exportExcel(list, "productType");
    }

    /**
     * 新增产品分类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品分类
     */
    @RequiresPermissions("business:productType:add")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizProductType bizProductType)
    {
        bizProductType.setCreateBy(ShiroUtils.getLoginName());
        bizProductType.setCreateTime(new Date());
        bizProductType.setProductTypeCode("BPT" + DateUtils.getMilliTime());
        return toAjax(bizProductTypeService.insertBizProductType(bizProductType));
    }

    /**
     * 修改产品分类
     */
    @GetMapping("/edit/{productTypeId}")
    public String edit(@PathVariable(value = "productTypeId", required = true) Long productTypeId, ModelMap mmap)
    {
        BizProductType bizProductType = bizProductTypeService.selectBizProductTypeById(productTypeId);
        mmap.put("bizProductType", bizProductType);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品分类
     */
    @RequiresPermissions("business:productType:edit")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizProductType bizProductType)
    {
        bizProductType.setUpdateBy(ShiroUtils.getLoginName());
        bizProductType.setUpdateTime(new Date());
        return toAjax(bizProductTypeService.updateBizProductType(bizProductType));
    }

    /**
     * 删除产品分类
     */
    @RequiresPermissions("business:productType:remove")
    @Log(title = "产品分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bizProductTypeService.deleteBizProductTypeByIds(ids));
    }
}
