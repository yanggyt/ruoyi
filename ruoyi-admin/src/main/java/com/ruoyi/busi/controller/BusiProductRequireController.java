package com.ruoyi.busi.controller;

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
import com.ruoyi.busi.domain.BusiProductRequire;
import com.ruoyi.busi.service.IBusiProductRequireService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品需求Controller
 * 
 * @author WangCL
 * @date 2021-12-22
 */
@Controller
@RequestMapping("/busi/productRequire")
public class BusiProductRequireController extends BaseController
{
    private String prefix = "busi/productRequire";

    @Autowired
    private IBusiProductRequireService busiProductRequireService;

    @RequiresPermissions("busi:productRequire:view")
    @GetMapping()
    public String productRequire()
    {
        return prefix + "/productRequire";
    }

    /**
     * 查询产品需求列表
     */
    @RequiresPermissions("busi:productRequire:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiProductRequire busiProductRequire)
    {
        startPage();
        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(busiProductRequire);
        return getDataTable(list);
    }

    /**
     * 导出产品需求列表
     */
    @RequiresPermissions("busi:productRequire:export")
    @Log(title = "产品需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiProductRequire busiProductRequire)
    {
        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(busiProductRequire);
        ExcelUtil<BusiProductRequire> util = new ExcelUtil<BusiProductRequire>(BusiProductRequire.class);
        return util.exportExcel(list, "产品需求数据");
    }

    /**
     * 新增产品需求
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品需求
     */
    @RequiresPermissions("busi:productRequire:add")
    @Log(title = "产品需求", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiProductRequire busiProductRequire)
    {
        return toAjax(busiProductRequireService.insertBusiProductRequire(busiProductRequire));
    }

    /**
     * 修改产品需求
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiProductRequire busiProductRequire = busiProductRequireService.selectBusiProductRequireById(id);
        mmap.put("busiProductRequire", busiProductRequire);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品需求
     */
    @RequiresPermissions("busi:productRequire:edit")
    @Log(title = "产品需求", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiProductRequire busiProductRequire)
    {
        return toAjax(busiProductRequireService.updateBusiProductRequire(busiProductRequire));
    }

    /**
     * 删除产品需求
     */
    @RequiresPermissions("busi:productRequire:remove")
    @Log(title = "产品需求", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiProductRequireService.deleteBusiProductRequireByIds(ids));
    }
}
