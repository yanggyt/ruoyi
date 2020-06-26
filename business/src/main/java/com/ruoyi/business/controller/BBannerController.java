package com.ruoyi.business.controller;

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
import com.ruoyi.business.domain.BBanner;
import com.ruoyi.business.service.IBBannerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 轮播图Controller
 *
 * @author anjie
 * @date 2020-06-21
 */
@Controller
@RequestMapping("/business/banner")
public class BBannerController extends BaseController
{
    private String prefix = "business/banner";

    @Autowired
    private IBBannerService bBannerService;

    @RequiresPermissions("business:banner:view")
    @GetMapping()
    public String banner()
    {
        return prefix + "/banner";
    }

    /**
     * 查询轮播图列表
     */
    @RequiresPermissions("business:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BBanner bBanner)
    {
        startPage();
        List<BBanner> list = bBannerService.selectBBannerList(bBanner);
        return getDataTable(list);
    }

    /**
     * 导出轮播图列表
     */
    @RequiresPermissions("business:banner:export")
    @Log(title = "轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BBanner bBanner)
    {
        List<BBanner> list = bBannerService.selectBBannerList(bBanner);
        ExcelUtil<BBanner> util = new ExcelUtil<BBanner>(BBanner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存轮播图
     */
    @RequiresPermissions("business:banner:add")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BBanner bBanner)
    {
        return toAjax(bBannerService.insertBBanner(bBanner));
    }

    /**
     * 修改轮播图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BBanner bBanner = bBannerService.selectBBannerById(id);
        mmap.put("bBanner", bBanner);
        return prefix + "/edit";
    }

    /**
     * 修改保存轮播图
     */
    @RequiresPermissions("business:banner:edit")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BBanner bBanner)
    {
        return toAjax(bBannerService.updateBBanner(bBanner));
    }

    /**
     * 删除轮播图
     */
    @RequiresPermissions("business:banner:remove")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bBannerService.deleteBBannerByIds(ids));
    }
}
