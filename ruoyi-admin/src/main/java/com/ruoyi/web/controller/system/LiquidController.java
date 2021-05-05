package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.Liquid;
import com.ruoyi.system.service.ILiquidService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 液体数据Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/system/liquid")
public class LiquidController extends BaseController
{
    private String prefix = "system/liquid";

    @Autowired
    private ILiquidService liquidService;

    @RequiresPermissions("system:liquid:view")
    @GetMapping()
    public String liquid()
    {
        return prefix + "/liquid";
    }

    /**
     * 查询液体数据列表
     */
    @RequiresPermissions("system:liquid:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Liquid liquid)
    {
        startPage();
        List<Liquid> list = liquidService.selectLiquidList(liquid);
        return getDataTable(list);
    }

    /**
     * 导出液体数据列表
     */
    @RequiresPermissions("system:liquid:export")
    @Log(title = "液体数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Liquid liquid)
    {
        List<Liquid> list = liquidService.selectLiquidList(liquid);
        ExcelUtil<Liquid> util = new ExcelUtil<Liquid>(Liquid.class);
        return util.exportExcel(list, "液体数据数据");
    }

    /**
     * 新增液体数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存液体数据
     */
    @RequiresPermissions("system:liquid:add")
    @Log(title = "液体数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Liquid liquid)
    {
        return toAjax(liquidService.insertLiquid(liquid));
    }

    /**
     * 修改液体数据
     */
    @GetMapping("/edit/{liquidNumber}")
    public String edit(@PathVariable("liquidNumber") Long liquidNumber, ModelMap mmap)
    {
        Liquid liquid = liquidService.selectLiquidById(liquidNumber);
        mmap.put("liquid", liquid);
        return prefix + "/edit";
    }

    /**
     * 修改保存液体数据
     */
    @RequiresPermissions("system:liquid:edit")
    @Log(title = "液体数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Liquid liquid)
    {
        return toAjax(liquidService.updateLiquid(liquid));
    }

    /**
     * 删除液体数据
     */
    @RequiresPermissions("system:liquid:remove")
    @Log(title = "液体数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(liquidService.deleteLiquidByIds(ids));
    }
}
