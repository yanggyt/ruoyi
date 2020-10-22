package com.ruoyi.web.controller.front;

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
import com.ruoyi.front.domain.NewsInformation;
import com.ruoyi.front.service.INewsInformationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻动态Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/information")
public class NewsInformationController extends BaseController
{
    private String prefix = "front/information";

    @Autowired
    private INewsInformationService newsInformationService;

    @RequiresPermissions("front:information:view")
    @GetMapping()
    public String information()
    {
        return prefix + "/information";
    }

    /**
     * 查询新闻动态列表
     */
    @RequiresPermissions("front:information:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsInformation newsInformation)
    {
        startPage();
        List<NewsInformation> list = newsInformationService.selectNewsInformationList(newsInformation);
        return getDataTable(list);
    }

    /**
     * 导出新闻动态列表
     */
    @RequiresPermissions("front:information:export")
    @Log(title = "新闻动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsInformation newsInformation)
    {
        List<NewsInformation> list = newsInformationService.selectNewsInformationList(newsInformation);
        ExcelUtil<NewsInformation> util = new ExcelUtil<NewsInformation>(NewsInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 新增新闻动态
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存新闻动态
     */
    @RequiresPermissions("front:information:add")
    @Log(title = "新闻动态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsInformation newsInformation)
    {
        return toAjax(newsInformationService.insertNewsInformation(newsInformation));
    }

    /**
     * 修改新闻动态
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsInformation newsInformation = newsInformationService.selectNewsInformationById(id);
        mmap.put("newsInformation", newsInformation);
        return prefix + "/edit";
    }

    /**
     * 修改保存新闻动态
     */
    @RequiresPermissions("front:information:edit")
    @Log(title = "新闻动态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsInformation newsInformation)
    {
        return toAjax(newsInformationService.updateNewsInformation(newsInformation));
    }

    /**
     * 删除新闻动态
     */
    @RequiresPermissions("front:information:remove")
    @Log(title = "新闻动态", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsInformationService.deleteNewsInformationByIds(ids));
    }
}
