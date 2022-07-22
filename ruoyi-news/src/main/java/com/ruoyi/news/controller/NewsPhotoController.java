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
import com.ruoyi.news.domain.NewsPhoto;
import com.ruoyi.news.service.INewsPhotoService;
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
@RequestMapping("/news/photo")
public class NewsPhotoController extends BaseController
{
    private String prefix = "news/photo";

    @Autowired
    private INewsPhotoService newsPhotoService;

    @RequiresPermissions("news:photo:view")
    @GetMapping()
    public String photo()
    {
        return prefix + "/photo";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:photo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsPhoto newsPhoto)
    {
        startPage();
        List<NewsPhoto> list = newsPhotoService.selectNewsPhotoList(newsPhoto);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:photo:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsPhoto newsPhoto)
    {
        List<NewsPhoto> list = newsPhotoService.selectNewsPhotoList(newsPhoto);
        ExcelUtil<NewsPhoto> util = new ExcelUtil<NewsPhoto>(NewsPhoto.class);
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
    @RequiresPermissions("news:photo:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsPhoto newsPhoto)
    {
        return toAjax(newsPhotoService.insertNewsPhoto(newsPhoto));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:photo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsPhoto newsPhoto = newsPhotoService.selectNewsPhotoById(id);
        mmap.put("newsPhoto", newsPhoto);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:photo:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsPhoto newsPhoto)
    {
        return toAjax(newsPhotoService.updateNewsPhoto(newsPhoto));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:photo:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsPhotoService.deleteNewsPhotoByIds(ids));
    }
}
