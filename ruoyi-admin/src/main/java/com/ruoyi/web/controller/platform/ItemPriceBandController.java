package com.ruoyi.province.platform.controller;

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
import com.ruoyi.province.platform.domain.ItemPriceBand;
import com.ruoyi.province.platform.service.IItemPriceBandService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;

/**
 * 商品价格带Controller
 * 
 * @author dalin
 * @date 2020-12-24
 */
@Controller
@RequestMapping("/platform/itempriceband")
public class ItemPriceBandController extends BaseController
{
    private String prefix = "platform/itempriceband";

    @Autowired
    private IItemPriceBandService itemPriceBandService;

    @RequiresPermissions("platform:itempriceband:view")
    @GetMapping()
    public String itempriceband()
    {
        return prefix + "/itempriceband";
    }

    /**
     * 查询商品价格带列表
     */
    @RequiresPermissions("platform:itempriceband:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ItemPriceBand itemPriceBand)
    {
        startPage();
        List<ItemPriceBand> list = itemPriceBandService.selectItemPriceBandList(itemPriceBand);
        return getDataTable(list);
    }

    /**
     * 导出商品价格带列表
     */
    @RequiresPermissions("platform:itempriceband:export")
    @Log(title = "商品价格带", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ItemPriceBand itemPriceBand)
    {
        List<ItemPriceBand> list = itemPriceBandService.selectItemPriceBandList(itemPriceBand);
        ExcelUtil<ItemPriceBand> util = new ExcelUtil<ItemPriceBand>(ItemPriceBand.class);
        return util.exportExcel(list, "itempriceband");
    }

    /**
     * itemPriceBand
      * 校验商品价格带名称 是否重复
      */
    @PostMapping("/checkItemPriceBandUnique")
    @ResponseBody
    public String checkItemPriceBandUnique(ItemPriceBand itemPriceBand)
    {
        return itemPriceBandService.checkItemPriceBandUnique(itemPriceBand);
    }

    /**
     * 新增商品价格带
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
      // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/add";
    }

    /**
     * 新增保存商品价格带
     */
    @RequiresPermissions("platform:itempriceband:add")
    @Log(title = "商品价格带", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ItemPriceBand itemPriceBand)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(itemPriceBandService.checkItemPriceBandUnique(itemPriceBand)))
        {
            return error("修改单据'" + itemPriceBand.getPriceBandName() + "'失败，名称已存在");
        }

    // 取身份信息
        itemPriceBand.setCreateBy( ShiroUtils.getLoginName() );
        itemPriceBand.setCreateTime(DateUtils.getNowDate() );

        return toAjax(itemPriceBandService.insertItemPriceBand(itemPriceBand));
    }

    /**
     * 修改商品价格带
     */
    @GetMapping("/edit/{priceBandId}")
    public String edit(@PathVariable("priceBandId") Long priceBandId, ModelMap mmap)
    {
        ItemPriceBand itemPriceBand = itemPriceBandService.selectItemPriceBandById(priceBandId);
        mmap.put("itemPriceBand", itemPriceBand);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存商品价格带
     */
    @RequiresPermissions("platform:itempriceband:edit")
    @Log(title = "商品价格带", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ItemPriceBand itemPriceBand)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(itemPriceBandService.checkItemPriceBandUnique(itemPriceBand)))
        {
            return error("修改单据'" + itemPriceBand.getPriceBandName() + "'失败，名称已存在");
        }

        itemPriceBand.setUpdateBy( ShiroUtils.getLoginName() );
        itemPriceBand.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(itemPriceBandService.updateItemPriceBand(itemPriceBand));
    }

    /**
     * 删除商品价格带
     */
    @RequiresPermissions("platform:itempriceband:remove")
    @Log(title = "商品价格带", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(itemPriceBandService.deleteItemPriceBandByIds(ids));
    }
}
