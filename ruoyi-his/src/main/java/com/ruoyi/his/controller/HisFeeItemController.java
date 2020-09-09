package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.his.domain.HisRegistrationTemplate;
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
import com.ruoyi.his.domain.HisFeeItem;
import com.ruoyi.his.service.IHisFeeItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用类型Controller
 * 
 * @author bend
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/his/feeItem")
public class HisFeeItemController extends BaseController
{
    private String prefix = "his/feeItem";

    @Autowired
    private IHisFeeItemService hisFeeItemService;

    @RequiresPermissions("his:feeItem:view")
    @GetMapping()
    public String feeItem()
    {
        return prefix + "/feeItem";
    }

    /**
     * 查询费用类型列表
     */
    @RequiresPermissions("his:feeItem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisFeeItem hisFeeItem)
    {
        startPage();
        List<HisFeeItem> list = hisFeeItemService.selectHisFeeItemList(hisFeeItem);
        return getDataTable(list);
    }

    /**
     * 导出费用类型列表
     */
    @RequiresPermissions("his:feeItem:export")
    @Log(title = "费用类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisFeeItem hisFeeItem)
    {
        List<HisFeeItem> list = hisFeeItemService.selectHisFeeItemList(hisFeeItem);
        ExcelUtil<HisFeeItem> util = new ExcelUtil<HisFeeItem>(HisFeeItem.class);
        return util.exportExcel(list, "feeItem");
    }

    /**
     * 新增费用类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存费用类型
     */
    @RequiresPermissions("his:feeItem:add")
    @Log(title = "费用类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisFeeItem hisFeeItem)
    {
        return toAjax(hisFeeItemService.insertHisFeeItem(hisFeeItem));
    }

    /**
     * 修改费用类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisFeeItem hisFeeItem = hisFeeItemService.selectHisFeeItemById(id);
        mmap.put("hisFeeItem", hisFeeItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存费用类型
     */
    @RequiresPermissions("his:feeItem:edit")
    @Log(title = "费用类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisFeeItem hisFeeItem)
    {
        return toAjax(hisFeeItemService.updateHisFeeItem(hisFeeItem));
    }

    /**
     * 删除费用类型
     */
    @RequiresPermissions("his:feeItem:remove")
    @Log(title = "费用类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisFeeItemService.deleteHisFeeItemByIds(ids));
    }

    /**
     * 状态修改
     */
    @Log(title = "费用类型管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("his:feeItem:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(HisFeeItem hisFeeItem)
    {
        return toAjax(hisFeeItemService.changeStatus(hisFeeItem));
    }
}
