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
import com.ruoyi.news.domain.NewsNotification;
import com.ruoyi.news.service.INewsNotificationService;
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
@RequestMapping("/news/notification")
public class NewsNotificationController extends BaseController
{
    private String prefix = "news/notification";

    @Autowired
    private INewsNotificationService newsNotificationService;

    @RequiresPermissions("news:notification:view")
    @GetMapping()
    public String notification()
    {
        return prefix + "/notification";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:notification:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsNotification newsNotification)
    {
        startPage();
        List<NewsNotification> list = newsNotificationService.selectNewsNotificationList(newsNotification);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:notification:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsNotification newsNotification)
    {
        List<NewsNotification> list = newsNotificationService.selectNewsNotificationList(newsNotification);
        ExcelUtil<NewsNotification> util = new ExcelUtil<NewsNotification>(NewsNotification.class);
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
    @RequiresPermissions("news:notification:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsNotification newsNotification)
    {
        return toAjax(newsNotificationService.insertNewsNotification(newsNotification));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:notification:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsNotification newsNotification = newsNotificationService.selectNewsNotificationById(id);
        mmap.put("newsNotification", newsNotification);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:notification:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsNotification newsNotification)
    {
        return toAjax(newsNotificationService.updateNewsNotification(newsNotification));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:notification:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsNotificationService.deleteNewsNotificationByIds(ids));
    }
}
