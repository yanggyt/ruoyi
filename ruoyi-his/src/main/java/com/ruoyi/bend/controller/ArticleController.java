package com.ruoyi.bend.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bend.domain.Article;
import com.ruoyi.bend.service.IArticleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内容管理Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/article")
public class ArticleController extends BaseController
{
    private String prefix = "bend/article";

    @Autowired
    private IArticleService articleService;

    @RequiresPermissions("bend:article:view")
    @GetMapping()
    public String article()
    {
        return prefix + "/article";
    }

    /**
     * 查询内容管理列表
     */
    @RequiresPermissions("bend:article:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出内容管理列表
     */
    @RequiresPermissions("bend:article:export")
    @Log(title = "内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 草稿箱 页面
     */
    @RequiresPermissions("bend:article:view")
    @GetMapping("/draft")
    public String draftArticle(@RequestParam(value = "articleStatus")Integer articleStatus, ModelMap mmap)
    {
        mmap.put("articleStatus", articleStatus);
        return prefix + "/draft";
    }

    /**
     * 查询草稿箱内容列表
     * @param article 内容
     * @return 列表
     */
    @RequiresPermissions("bend:article:draft")
    @PostMapping("/draft/list")
    @ResponseBody
    public TableDataInfo draftList(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }


    /**
     * 新增内容管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存内容管理
     */
    @RequiresPermissions("bend:article:add")
    @Log(title = "内容管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Article article)
    {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改内容管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Article article = articleService.selectArticleById(id);
        mmap.put("article", article);
        return prefix + "/edit";
    }

    /**
     * 修改保存内容管理
     */
    @RequiresPermissions("bend:article:edit")
    @Log(title = "内容管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除内容管理
     */
    @RequiresPermissions("bend:article:remove")
    @Log(title = "内容管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(articleService.deleteArticleByIds(ids));
    }
}
