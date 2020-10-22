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
import com.ruoyi.front.domain.HomePageCarousel;
import com.ruoyi.front.service.IHomePageCarouselService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 首页轮播图Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/carousel")
public class HomePageCarouselController extends BaseController
{
    private String prefix = "front/carousel";

    @Autowired
    private IHomePageCarouselService homePageCarouselService;

    @RequiresPermissions("front:carousel:view")
    @GetMapping()
    public String carousel()
    {
        return prefix + "/carousel";
    }

    /**
     * 查询首页轮播图列表
     */
    @RequiresPermissions("front:carousel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HomePageCarousel homePageCarousel)
    {
        startPage();
        List<HomePageCarousel> list = homePageCarouselService.selectHomePageCarouselList(homePageCarousel);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播图列表
     */
    @RequiresPermissions("front:carousel:export")
    @Log(title = "首页轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HomePageCarousel homePageCarousel)
    {
        List<HomePageCarousel> list = homePageCarouselService.selectHomePageCarouselList(homePageCarousel);
        ExcelUtil<HomePageCarousel> util = new ExcelUtil<HomePageCarousel>(HomePageCarousel.class);
        return util.exportExcel(list, "carousel");
    }

    /**
     * 新增首页轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页轮播图
     */
    @RequiresPermissions("front:carousel:add")
    @Log(title = "首页轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HomePageCarousel homePageCarousel)
    {
        return toAjax(homePageCarouselService.insertHomePageCarousel(homePageCarousel));
    }

    /**
     * 修改首页轮播图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HomePageCarousel homePageCarousel = homePageCarouselService.selectHomePageCarouselById(id);
        mmap.put("homePageCarousel", homePageCarousel);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页轮播图
     */
    @RequiresPermissions("front:carousel:edit")
    @Log(title = "首页轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HomePageCarousel homePageCarousel)
    {
        return toAjax(homePageCarouselService.updateHomePageCarousel(homePageCarousel));
    }

    /**
     * 删除首页轮播图
     */
    @RequiresPermissions("front:carousel:remove")
    @Log(title = "首页轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(homePageCarouselService.deleteHomePageCarouselByIds(ids));
    }
}
