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
import com.ruoyi.system.domain.Shuini;
import com.ruoyi.system.service.IShuiniService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水泥浆Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/system/Shuini")
public class ShuiniController extends BaseController
{
    private String prefix = "system/Shuini";

    @Autowired
    private IShuiniService shuiniService;

    @RequiresPermissions("system:Shuini:view")
    @GetMapping()
    public String Shuini()
    {
        return prefix + "/Shuini";
    }

    /**
     * 查询水泥浆列表
     */
    @RequiresPermissions("system:Shuini:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Shuini shuini)
    {
        startPage();
        List<Shuini> list = shuiniService.selectShuiniList(shuini);
        return getDataTable(list);
    }

    /**
     * 导出水泥浆列表
     */
    @RequiresPermissions("system:Shuini:export")
    @Log(title = "水泥浆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Shuini shuini)
    {
        List<Shuini> list = shuiniService.selectShuiniList(shuini);
        ExcelUtil<Shuini> util = new ExcelUtil<Shuini>(Shuini.class);
        return util.exportExcel(list, "水泥浆数据");
    }

    /**
     * 新增水泥浆
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存水泥浆
     */
    @RequiresPermissions("system:Shuini:add")
    @Log(title = "水泥浆", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Shuini shuini)
    {
        return toAjax(shuiniService.insertShuini(shuini));
    }

    /**
     * 修改水泥浆
     */
    @GetMapping("/edit/{liquidNumber}")
    public String edit(@PathVariable("liquidNumber") Long liquidNumber, ModelMap mmap)
    {
        Shuini shuini = shuiniService.selectShuiniById(liquidNumber);
        mmap.put("shuini", shuini);
        return prefix + "/edit";
    }

    /**
     * 修改保存水泥浆
     */
    @RequiresPermissions("system:Shuini:edit")
    @Log(title = "水泥浆", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Shuini shuini)
    {
        return toAjax(shuiniService.updateShuini(shuini));
    }

    /**
     * 删除水泥浆
     */
    @RequiresPermissions("system:Shuini:remove")
    @Log(title = "水泥浆", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(shuiniService.deleteShuiniByIds(ids));
    }
}
