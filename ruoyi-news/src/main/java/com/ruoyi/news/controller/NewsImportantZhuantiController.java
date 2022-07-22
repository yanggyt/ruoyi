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
import com.ruoyi.news.domain.NewsImportantZhuanti;
import com.ruoyi.news.service.INewsImportantZhuantiService;
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
@RequestMapping("/news/zhuanti")
public class NewsImportantZhuantiController extends BaseController
{
    private String prefix = "news/zhuanti";

    @Autowired
    private INewsImportantZhuantiService newsImportantZhuantiService;

    @RequiresPermissions("news:zhuanti:view")
    @GetMapping()
    public String zhuanti()
    {
        return prefix + "/zhuanti";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:zhuanti:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsImportantZhuanti newsImportantZhuanti)
    {
        startPage();
        List<NewsImportantZhuanti> list = newsImportantZhuantiService.selectNewsImportantZhuantiList(newsImportantZhuanti);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:zhuanti:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsImportantZhuanti newsImportantZhuanti)
    {
        List<NewsImportantZhuanti> list = newsImportantZhuantiService.selectNewsImportantZhuantiList(newsImportantZhuanti);
        ExcelUtil<NewsImportantZhuanti> util = new ExcelUtil<NewsImportantZhuanti>(NewsImportantZhuanti.class);
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
    @RequiresPermissions("news:zhuanti:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsImportantZhuanti newsImportantZhuanti)
    {
        return toAjax(newsImportantZhuantiService.insertNewsImportantZhuanti(newsImportantZhuanti));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:zhuanti:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsImportantZhuanti newsImportantZhuanti = newsImportantZhuantiService.selectNewsImportantZhuantiById(id);
        mmap.put("newsImportantZhuanti", newsImportantZhuanti);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:zhuanti:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsImportantZhuanti newsImportantZhuanti)
    {
        return toAjax(newsImportantZhuantiService.updateNewsImportantZhuanti(newsImportantZhuanti));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:zhuanti:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsImportantZhuantiService.deleteNewsImportantZhuantiByIds(ids));
    }
}
