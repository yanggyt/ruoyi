package com.ruoyi.web.controller.platform;

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
import com.ruoyi.province.platform.domain.ItemParamters;
import com.ruoyi.province.platform.service.IItemParamtersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;

/**
 * 商品参数Controller
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Controller
@RequestMapping("/platform/itemparamters")
public class ItemParamtersController extends BaseController
{
    private String prefix = "platform/itemparamters";

    @Autowired
    private IItemParamtersService itemParamtersService;

    @RequiresPermissions("platform:itemparamters:view")
    @GetMapping()
    public String itemparamters()
    {
        return prefix + "/itemparamters";
    }

    /**
     * 查询商品参数列表
     */
    @RequiresPermissions("platform:itemparamters:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ItemParamters itemParamters)
    {
        startPage();
        List<ItemParamters> list = itemParamtersService.selectItemParamtersList(itemParamters);
        return getDataTable(list);
    }

    /**
     * 导出商品参数列表
     */
    @RequiresPermissions("platform:itemparamters:export")
    @Log(title = "商品参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ItemParamters itemParamters)
    {
        List<ItemParamters> list = itemParamtersService.selectItemParamtersList(itemParamters);
        ExcelUtil<ItemParamters> util = new ExcelUtil<ItemParamters>(ItemParamters.class);
        return util.exportExcel(list, "itemparamters");
    }

    /**
     * itemParamters
      * 校验商品参数名称 是否重复
      */
    @PostMapping("/checkItemParamtersUnique")
    @ResponseBody
    public String checkItemParamtersUnique(ItemParamters itemParamters)
    {
        return itemParamtersService.checkItemParamtersUnique(itemParamters);
    }

    /**
     * 新增商品参数
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
      // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/add";
    }

    /**
     * 新增保存商品参数
     */
    @RequiresPermissions("platform:itemparamters:add")
    @Log(title = "商品参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ItemParamters itemParamters)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(itemParamtersService.checkItemParamtersUnique(itemParamters)))
        {
            return error("修改单据'" + itemParamters.getItemParamtersName() + "'失败，名称已存在");
        }

    // 取身份信息
        itemParamters.setCreateBy( ShiroUtils.getLoginName() );
        itemParamters.setCreateTime(DateUtils.getNowDate() );

        return toAjax(itemParamtersService.insertItemParamters(itemParamters));
    }

    /**
     * 修改商品参数
     */
    @GetMapping("/edit/{itemParamtersId}")
    public String edit(@PathVariable("itemParamtersId") Long itemParamtersId, ModelMap mmap)
    {
        ItemParamters itemParamters = itemParamtersService.selectItemParamtersById(itemParamtersId);
        mmap.put("itemParamters", itemParamters);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存商品参数
     */
    @RequiresPermissions("platform:itemparamters:edit")
    @Log(title = "商品参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ItemParamters itemParamters)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(itemParamtersService.checkItemParamtersUnique(itemParamters)))
        {
            return error("修改单据'" + itemParamters.getItemParamtersName() + "'失败，名称已存在");
        }


        itemParamters.setUpdateBy( ShiroUtils.getLoginName() );
        itemParamters.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(itemParamtersService.updateItemParamters(itemParamters));
    }



    /**
     * 删除商品参数
     */
    @RequiresPermissions("platform:itemparamters:remove")
    @Log(title = "商品参数", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(itemParamtersService.deleteItemParamtersByIds(ids));
    }
}
