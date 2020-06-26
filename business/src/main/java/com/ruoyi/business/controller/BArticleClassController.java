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
import com.ruoyi.business.domain.BArticleClass;
import com.ruoyi.business.service.IBArticleClassService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 文章分类Controller
 *
 * @author anjie
 * @date 2020-06-26
 */
@Controller
@RequestMapping("/business/articleclass")
public class BArticleClassController extends BaseController
{
    private String prefix = "business/articleclass";

    @Autowired
    private IBArticleClassService bArticleClassService;

    @RequiresPermissions("business:articleclass:view")
    @GetMapping()
    public String articleclass()
    {
        return prefix + "/articleclass";
    }

    /**
     * 查询文章分类树列表
     */
    @RequiresPermissions("business:articleclass:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BArticleClass> list(BArticleClass bArticleClass)
    {
        List<BArticleClass> list = bArticleClassService.selectBArticleClassList(bArticleClass);
        return list;
    }

    /**
     * 导出文章分类列表
     */
    @RequiresPermissions("business:articleclass:export")
    @Log(title = "文章分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BArticleClass bArticleClass)
    {
        List<BArticleClass> list = bArticleClassService.selectBArticleClassList(bArticleClass);
        ExcelUtil<BArticleClass> util = new ExcelUtil<BArticleClass>(BArticleClass.class);
        return util.exportExcel(list, "articleclass");
    }

    /**
     * 新增文章分类
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bArticleClass", bArticleClassService.selectBArticleClassById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存文章分类
     */
    @RequiresPermissions("business:articleclass:add")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BArticleClass bArticleClass)
    {
        return toAjax(bArticleClassService.insertBArticleClass(bArticleClass));
    }

    /**
     * 修改文章分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BArticleClass bArticleClass = bArticleClassService.selectBArticleClassById(id);
        mmap.put("bArticleClass", bArticleClass);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章分类
     */
    @RequiresPermissions("business:articleclass:edit")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BArticleClass bArticleClass)
    {
        return toAjax(bArticleClassService.updateBArticleClass(bArticleClass));
    }

    /**
     * 删除
     */
    @RequiresPermissions("business:articleclass:remove")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(bArticleClassService.deleteBArticleClassById(id));
    }

    /**
     * 选择文章分类树
     */
    @GetMapping(value = { "/selectArticleclassTree/{id}", "/selectArticleclassTree/" })
    public String selectArticleclassTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bArticleClass", bArticleClassService.selectBArticleClassById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载文章分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = bArticleClassService.selectBArticleClassTree();
        return ztrees;
    }
}
