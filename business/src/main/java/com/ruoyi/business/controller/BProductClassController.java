package com.ruoyi.business.controller;

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
import com.ruoyi.business.domain.BProductClass;
import com.ruoyi.business.service.IBProductClassService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 商品分类Controller
 *
 * @author anjie
 * @date 2020-06-21
 */
@Controller
@RequestMapping("/business/businessclass")
public class BProductClassController extends BaseController
{
    private String prefix = "business/businessclass";

    @Autowired
    private IBProductClassService bProductClassService;

    @RequiresPermissions("business:businessclass:view")
    @GetMapping()
    public String businessclass()
    {
        return prefix + "/businessclass";
    }

    /**
     * 查询商品分类树列表
     */
    @RequiresPermissions("business:businessclass:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BProductClass> list(BProductClass bProductClass)
    {
        List<BProductClass> list = bProductClassService.selectBProductClassList(bProductClass);
        return list;
    }

    /**
     * 导出商品分类列表
     */
    @RequiresPermissions("business:businessclass:export")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BProductClass bProductClass)
    {
        List<BProductClass> list = bProductClassService.selectBProductClassList(bProductClass);
        ExcelUtil<BProductClass> util = new ExcelUtil<BProductClass>(BProductClass.class);
        return util.exportExcel(list, "businessclass");
    }

    /**
     * 新增商品分类
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bProductClass", bProductClassService.selectBProductClassById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存商品分类
     */
    @RequiresPermissions("business:businessclass:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BProductClass bProductClass)
    {
        return toAjax(bProductClassService.insertBProductClass(bProductClass));
    }

    /**
     * 修改商品分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BProductClass bProductClass = bProductClassService.selectBProductClassById(id);
        mmap.put("bProductClass", bProductClass);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品分类
     */
    @RequiresPermissions("business:businessclass:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BProductClass bProductClass)
    {
        return toAjax(bProductClassService.updateBProductClass(bProductClass));
    }

    /**
     * 删除
     */
    @RequiresPermissions("business:businessclass:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(bProductClassService.deleteBProductClassById(id));
    }

    /**
     * 选择商品分类树
     */
    @GetMapping(value = { "/selectBusinessclassTree/{id}", "/selectBusinessclassTree/" })
    public String selectBusinessclassTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bProductClass", bProductClassService.selectBProductClassById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载商品分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = bProductClassService.selectBProductClassTree();
        return ztrees;
    }
}
