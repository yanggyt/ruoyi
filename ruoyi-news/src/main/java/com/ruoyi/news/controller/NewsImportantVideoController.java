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
import com.ruoyi.news.domain.NewsImportantVideo;
import com.ruoyi.news.service.INewsImportantVideoService;
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
@RequestMapping("/news/video")
public class NewsImportantVideoController extends BaseController
{
    private String prefix = "news/video";

    @Autowired
    private INewsImportantVideoService newsImportantVideoService;

    @RequiresPermissions("news:video:view")
    @GetMapping()
    public String video()
    {
        return prefix + "/video";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:video:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsImportantVideo newsImportantVideo)
    {
        startPage();
        List<NewsImportantVideo> list = newsImportantVideoService.selectNewsImportantVideoList(newsImportantVideo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:video:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsImportantVideo newsImportantVideo)
    {
        List<NewsImportantVideo> list = newsImportantVideoService.selectNewsImportantVideoList(newsImportantVideo);
        ExcelUtil<NewsImportantVideo> util = new ExcelUtil<NewsImportantVideo>(NewsImportantVideo.class);
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
    @RequiresPermissions("news:video:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsImportantVideo newsImportantVideo)
    {
        return toAjax(newsImportantVideoService.insertNewsImportantVideo(newsImportantVideo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:video:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsImportantVideo newsImportantVideo = newsImportantVideoService.selectNewsImportantVideoById(id);
        mmap.put("newsImportantVideo", newsImportantVideo);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:video:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsImportantVideo newsImportantVideo)
    {
        return toAjax(newsImportantVideoService.updateNewsImportantVideo(newsImportantVideo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:video:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsImportantVideoService.deleteNewsImportantVideoByIds(ids));
    }
}
