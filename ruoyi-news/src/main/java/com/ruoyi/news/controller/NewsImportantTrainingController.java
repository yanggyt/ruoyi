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
import com.ruoyi.news.domain.NewsImportantTraining;
import com.ruoyi.news.service.INewsImportantTrainingService;
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
@RequestMapping("/news/training")
public class NewsImportantTrainingController extends BaseController
{
    private String prefix = "news/training";

    @Autowired
    private INewsImportantTrainingService newsImportantTrainingService;

    @RequiresPermissions("news:training:view")
    @GetMapping()
    public String training()
    {
        return prefix + "/training";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:training:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsImportantTraining newsImportantTraining)
    {
        startPage();
        List<NewsImportantTraining> list = newsImportantTrainingService.selectNewsImportantTrainingList(newsImportantTraining);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:training:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsImportantTraining newsImportantTraining)
    {
        List<NewsImportantTraining> list = newsImportantTrainingService.selectNewsImportantTrainingList(newsImportantTraining);
        ExcelUtil<NewsImportantTraining> util = new ExcelUtil<NewsImportantTraining>(NewsImportantTraining.class);
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
    @RequiresPermissions("news:training:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsImportantTraining newsImportantTraining)
    {
        return toAjax(newsImportantTrainingService.insertNewsImportantTraining(newsImportantTraining));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:training:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsImportantTraining newsImportantTraining = newsImportantTrainingService.selectNewsImportantTrainingById(id);
        mmap.put("newsImportantTraining", newsImportantTraining);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:training:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsImportantTraining newsImportantTraining)
    {
        return toAjax(newsImportantTrainingService.updateNewsImportantTraining(newsImportantTraining));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:training:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsImportantTrainingService.deleteNewsImportantTrainingByIds(ids));
    }
}
