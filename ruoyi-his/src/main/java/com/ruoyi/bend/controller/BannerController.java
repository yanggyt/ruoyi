package com.ruoyi.bend.controller;

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
import com.ruoyi.bend.domain.Banner;
import com.ruoyi.bend.service.IBannerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 首页管理Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/banner")
public class BannerController extends BaseController
{
    private String prefix = "bend/banner";

    @Autowired
    private IBannerService bannerService;

    @RequiresPermissions("bend:banner:view")
    @GetMapping()
    public String banner()
    {
        return prefix + "/banner";
    }

    /**
     * 查询首页管理列表
     */
    @RequiresPermissions("bend:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Banner banner)
    {
        startPage();
        List<Banner> list = bannerService.selectBannerList(banner);
        return getDataTable(list);
    }

    /**
     * 导出首页管理列表
     */
    @RequiresPermissions("bend:banner:export")
    @Log(title = "首页管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Banner banner)
    {
        List<Banner> list = bannerService.selectBannerList(banner);
        ExcelUtil<Banner> util = new ExcelUtil<Banner>(Banner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增首页管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页管理
     */
    @RequiresPermissions("bend:banner:add")
    @Log(title = "首页管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Banner banner)
    {
        return toAjax(bannerService.insertBanner(banner));
    }

    /**
     * 修改首页管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Banner banner = bannerService.selectBannerById(id);
        mmap.put("banner", banner);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页管理
     */
    @RequiresPermissions("bend:banner:edit")
    @Log(title = "首页管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Banner banner)
    {
        return toAjax(bannerService.updateBanner(banner));
    }

    /**
     * 删除首页管理
     */
    @RequiresPermissions("bend:banner:remove")
    @Log(title = "首页管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bannerService.deleteBannerByIds(ids));
    }
}
