package com.ruoyi.content.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.content.domain.CmsPicAdInfo;
import com.ruoyi.content.service.ICmsPicAdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片广告Controller
 *
 * @author liushenlu
 * @date 2021-03-25
 */
@Controller
@RequestMapping("/content/picAdverts")
public class CmsPicAdInfoController extends BaseController {

    private String prefix = "content/picAdverts";

    @Autowired
    private ICmsPicAdInfoService cmsPicAdInfoService;

    @GetMapping()
    public String picAdverts() {
        return prefix + "/picAdverts";
    }

    /**
     * 查询图片广告列表
     */
    @PostMapping("/cardInfo")
    @ResponseBody
    public List<CmsPicAdInfo> queryCardInfo(CmsPicAdInfo cmsPicAdInfo) {
        List<CmsPicAdInfo> list = cmsPicAdInfoService.selectCmsPicAdInfoList(cmsPicAdInfo);
        return list;
    }

    /**
     * 查询图片广告列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsPicAdInfo cmsPicAdInfo) {
        startPage();
        List<CmsPicAdInfo> list = cmsPicAdInfoService.selectCmsPicAdInfoList(cmsPicAdInfo);
        return getDataTable(list);
    }

    /**
     * 导出图片广告列表
     */
    @Log(title = "图片广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsPicAdInfo cmsPicAdInfo) {
        List<CmsPicAdInfo> list = cmsPicAdInfoService.selectCmsPicAdInfoList(cmsPicAdInfo);
        ExcelUtil<CmsPicAdInfo> util = new ExcelUtil<CmsPicAdInfo>(CmsPicAdInfo.class);
        return util.exportExcel(list, "picAdverts");
    }

    /**
     * 新增图片广告
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存图片广告
     */
    @Log(title = "图片广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsPicAdInfo cmsPicAdInfo) {
        return toAjax(cmsPicAdInfoService.insertCmsPicAdInfo(cmsPicAdInfo));
    }

    /**
     * 修改图片广告
     */
    @GetMapping("/edit/{picAdId}")
    public String edit(@PathVariable("picAdId") Long picAdId, ModelMap mmap) {
        CmsPicAdInfo cmsPicAdInfo = cmsPicAdInfoService.selectCmsPicAdInfoById(picAdId);
        mmap.put("cmsPicAdInfo", cmsPicAdInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存图片广告
     */
    @Log(title = "图片广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsPicAdInfo cmsPicAdInfo) {
        return toAjax(cmsPicAdInfoService.updateCmsPicAdInfo(cmsPicAdInfo));
    }

    /**
     * 删除图片广告
     */
    @Log(title = "图片广告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmsPicAdInfoService.deleteCmsPicAdInfoByIds(ids));
    }
}
