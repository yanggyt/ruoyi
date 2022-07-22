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
import com.ruoyi.news.domain.NewsImportantTuwen;
import com.ruoyi.news.service.INewsImportantTuwenService;
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
@RequestMapping("/news/tuwen")
public class NewsImportantTuwenController extends BaseController
{
    private String prefix = "news/tuwen";

    @Autowired
    private INewsImportantTuwenService newsImportantTuwenService;

    @RequiresPermissions("news:tuwen:view")
    @GetMapping()
    public String tuwen()
    {
        return prefix + "/tuwen";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:tuwen:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsImportantTuwen newsImportantTuwen)
    {
        startPage();
        List<NewsImportantTuwen> list = newsImportantTuwenService.selectNewsImportantTuwenList(newsImportantTuwen);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:tuwen:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsImportantTuwen newsImportantTuwen)
    {
        List<NewsImportantTuwen> list = newsImportantTuwenService.selectNewsImportantTuwenList(newsImportantTuwen);
        ExcelUtil<NewsImportantTuwen> util = new ExcelUtil<NewsImportantTuwen>(NewsImportantTuwen.class);
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
    @RequiresPermissions("news:tuwen:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsImportantTuwen newsImportantTuwen)
    {
        return toAjax(newsImportantTuwenService.insertNewsImportantTuwen(newsImportantTuwen));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:tuwen:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsImportantTuwen newsImportantTuwen = newsImportantTuwenService.selectNewsImportantTuwenById(id);
        mmap.put("newsImportantTuwen", newsImportantTuwen);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:tuwen:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsImportantTuwen newsImportantTuwen)
    {
        return toAjax(newsImportantTuwenService.updateNewsImportantTuwen(newsImportantTuwen));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:tuwen:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsImportantTuwenService.deleteNewsImportantTuwenByIds(ids));
    }
}
