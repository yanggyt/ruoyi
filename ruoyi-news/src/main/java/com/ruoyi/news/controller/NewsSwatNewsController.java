package com.ruoyi.news.controller;

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
import com.ruoyi.news.domain.NewsSwatNews;
import com.ruoyi.news.service.INewsSwatNewsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Controller
@RequestMapping("/news/news")
public class NewsSwatNewsController extends BaseController
{
    private String prefix = "news/news";

    @Autowired
    private INewsSwatNewsService newsSwatNewsService;

    @RequiresPermissions("news:news:view")
    @GetMapping()
    public String news()
    {
        return prefix + "/news";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:news:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsSwatNews newsSwatNews)
    {
        startPage();
        List<NewsSwatNews> list = newsSwatNewsService.selectNewsSwatNewsList(newsSwatNews);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:news:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsSwatNews newsSwatNews)
    {
        List<NewsSwatNews> list = newsSwatNewsService.selectNewsSwatNewsList(newsSwatNews);
        ExcelUtil<NewsSwatNews> util = new ExcelUtil<NewsSwatNews>(NewsSwatNews.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("news:news:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsSwatNews newsSwatNews)
    {
        return toAjax(newsSwatNewsService.insertNewsSwatNews(newsSwatNews));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:news:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsSwatNews newsSwatNews = newsSwatNewsService.selectNewsSwatNewsById(id);
        mmap.put("newsSwatNews", newsSwatNews);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:news:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsSwatNews newsSwatNews)
    {
        return toAjax(newsSwatNewsService.updateNewsSwatNews(newsSwatNews));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:news:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsSwatNewsService.deleteNewsSwatNewsByIds(ids));
    }
}
