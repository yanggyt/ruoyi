package com.ruoyi.content.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.content.domain.ArticleAdInfo;
import com.ruoyi.content.domain.CmsArticleAdInfo;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.ICmsArticleAdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 文章广告Controller
 *
 * @author ruoyi
 * @date 2021-03-23
 */
@Controller
@RequestMapping("/content/adverts")
public class AdvertisementController extends BaseController {

    private String prefix = "content/adverts";

    @Autowired
    private ICmsArticleAdInfoService cmsArticleAdInfoService;

    @GetMapping()
    public String adverts() {
        return prefix + "/adverts";
    }

    /**
     * 查询所有广告列表（本程序调用）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryAdvertisements")
    @ResponseBody
    public Message queryAdvertisements(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        Map<String, Object> adMap = new HashMap<>();
        String companyId = "1";
        // 广告列表信息
        List<ArticleAdInfo> advertiseAdList = cmsArticleAdInfoService.queryAdByCompanyId(companyId);
        adMap.put("advertiseAdList", advertiseAdList);
        msg.setInfo("成功");
        msg.setObject(adMap);
        msg.setResult(true);
        return msg;
    }

    /**
     * 查询文章广告列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsArticleAdInfo cmsArticleAdInfo) {
        startPage();
        List<CmsArticleAdInfo> list = cmsArticleAdInfoService.selectCmsArticleAdInfoList(cmsArticleAdInfo);
        return getDataTable(list);
    }

    /**
     * 导出文章广告列表
     */
    @Log(title = "文章广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsArticleAdInfo cmsArticleAdInfo) {
        List<CmsArticleAdInfo> list = cmsArticleAdInfoService.selectCmsArticleAdInfoList(cmsArticleAdInfo);
        ExcelUtil<CmsArticleAdInfo> util = new ExcelUtil<CmsArticleAdInfo>(CmsArticleAdInfo.class);
        return util.exportExcel(list, "adverts");
    }

    /**
     * 新增文章广告
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文章广告
     */
    @Log(title = "文章广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("addImg") MultipartFile[] files, CmsArticleAdInfo cmsArticleAdInfo) {
        MultipartFile file = files[0];
        return toAjax(cmsArticleAdInfoService.insertCmsArticleAdInfo(file, cmsArticleAdInfo));
    }

    /**
     * 修改文章广告
     */
    @RequestMapping("/edit/{adId}")
    public String edit(@PathVariable("adId") Long adId, ModelMap mmap) {
        CmsArticleAdInfo cmsArticleAdInfo = cmsArticleAdInfoService.selectCmsArticleAdInfoById(adId);
        mmap.put("cmsArticleAdInfo", cmsArticleAdInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章广告
     */
    @Log(title = "文章广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("addImg") MultipartFile[] files, CmsArticleAdInfo cmsArticleAdInfo) {
        MultipartFile file = null;
        if (files != null && files.length > 0) {
            file = files[0];
        }
        return toAjax(cmsArticleAdInfoService.updateCmsArticleAdInfo(file, cmsArticleAdInfo));
    }

    /**
     * 删除文章广告
     */
    @Log(title = "文章广告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmsArticleAdInfoService.deleteCmsArticleAdInfoByIds(ids));
    }
}
