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
import com.ruoyi.news.domain.NewsProjectLearning;
import com.ruoyi.news.service.INewsProjectLearningService;
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
@RequestMapping("/news/learning")
public class NewsProjectLearningController extends BaseController
{
    private String prefix = "news/learning";

    @Autowired
    private INewsProjectLearningService newsProjectLearningService;

    @RequiresPermissions("news:learning:view")
    @GetMapping()
    public String learning()
    {
        return prefix + "/learning";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:learning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsProjectLearning newsProjectLearning)
    {
        startPage();
        List<NewsProjectLearning> list = newsProjectLearningService.selectNewsProjectLearningList(newsProjectLearning);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:learning:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsProjectLearning newsProjectLearning)
    {
        List<NewsProjectLearning> list = newsProjectLearningService.selectNewsProjectLearningList(newsProjectLearning);
        ExcelUtil<NewsProjectLearning> util = new ExcelUtil<NewsProjectLearning>(NewsProjectLearning.class);
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
    @RequiresPermissions("news:learning:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsProjectLearning newsProjectLearning)
    {
        return toAjax(newsProjectLearningService.insertNewsProjectLearning(newsProjectLearning));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:learning:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsProjectLearning newsProjectLearning = newsProjectLearningService.selectNewsProjectLearningById(id);
        mmap.put("newsProjectLearning", newsProjectLearning);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:learning:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsProjectLearning newsProjectLearning)
    {
        return toAjax(newsProjectLearningService.updateNewsProjectLearning(newsProjectLearning));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:learning:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsProjectLearningService.deleteNewsProjectLearningByIds(ids));
    }
}
