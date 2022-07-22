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
import com.ruoyi.news.domain.NewsImportantFalv;
import com.ruoyi.news.service.INewsImportantFalvService;
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
@RequestMapping("/news/falv")
public class NewsImportantFalvController extends BaseController
{
    private String prefix = "news/falv";

    @Autowired
    private INewsImportantFalvService newsImportantFalvService;

    @RequiresPermissions("news:falv:view")
    @GetMapping()
    public String falv()
    {
        return prefix + "/falv";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:falv:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsImportantFalv newsImportantFalv)
    {
        startPage();
        List<NewsImportantFalv> list = newsImportantFalvService.selectNewsImportantFalvList(newsImportantFalv);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:falv:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsImportantFalv newsImportantFalv)
    {
        List<NewsImportantFalv> list = newsImportantFalvService.selectNewsImportantFalvList(newsImportantFalv);
        ExcelUtil<NewsImportantFalv> util = new ExcelUtil<NewsImportantFalv>(NewsImportantFalv.class);
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
    @RequiresPermissions("news:falv:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsImportantFalv newsImportantFalv)
    {
        return toAjax(newsImportantFalvService.insertNewsImportantFalv(newsImportantFalv));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:falv:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsImportantFalv newsImportantFalv = newsImportantFalvService.selectNewsImportantFalvById(id);
        mmap.put("newsImportantFalv", newsImportantFalv);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:falv:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsImportantFalv newsImportantFalv)
    {
        return toAjax(newsImportantFalvService.updateNewsImportantFalv(newsImportantFalv));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:falv:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsImportantFalvService.deleteNewsImportantFalvByIds(ids));
    }
}
