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
import com.ruoyi.business.domain.BArticle;
import com.ruoyi.business.service.IBArticleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章Controller
 *
 * @author anjie
 * @date 2020-06-26
 */
@Controller
@RequestMapping("/business/article")
public class BArticleController extends BaseController
{
    private String prefix = "business/article";

    @Autowired
    private IBArticleService bArticleService;

    @RequiresPermissions("business:article:view")
    @GetMapping()
    public String article()
    {
        return prefix + "/article";
    }

    /**
     * 查询文章列表
     */
    @RequiresPermissions("business:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BArticle bArticle)
    {
        startPage();
        List<BArticle> list = bArticleService.selectBArticleList(bArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @RequiresPermissions("business:article:export")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BArticle bArticle)
    {
        List<BArticle> list = bArticleService.selectBArticleList(bArticle);
        ExcelUtil<BArticle> util = new ExcelUtil<BArticle>(BArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 新增文章
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文章
     */
    @RequiresPermissions("business:article:add")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BArticle bArticle)
    {
        return toAjax(bArticleService.insertBArticle(bArticle));
    }

    /**
     * 修改文章
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BArticle bArticle = bArticleService.selectBArticleById(id);
        mmap.put("bArticle", bArticle);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章
     */
    @RequiresPermissions("business:article:edit")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BArticle bArticle)
    {
        return toAjax(bArticleService.updateBArticle(bArticle));
    }

    /**
     * 删除文章
     */
    @RequiresPermissions("business:article:remove")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bArticleService.deleteBArticleByIds(ids));
    }
}
