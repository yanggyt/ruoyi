package com.sinosoft.activity.controller;

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
import com.sinosoft.activity.domain.DrawPrizeInfo;
import com.sinosoft.activity.service.IDrawPrizeInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储奖品的基础信息Controller
 * 
 * @author dy
 * @date 2021-03-25
 */
@Controller
@RequestMapping("/activity/prize")
public class DrawPrizeInfoController extends BaseController
{
    private String prefix = "activity/prizeInfo";

    @Autowired
    private IDrawPrizeInfoService drawPrizeInfoService;

    @RequiresPermissions("activity:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询存储奖品的基础信息列表
     */
    @RequiresPermissions("activity:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawPrizeInfo drawPrizeInfo)
    {
        startPage();
        List<DrawPrizeInfo> list = drawPrizeInfoService.selectDrawPrizeInfoList(drawPrizeInfo);
        return getDataTable(list);
    }

    /**
     * 导出存储奖品的基础信息列表
     */
    @RequiresPermissions("activity:info:export")
    @Log(title = "存储奖品的基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawPrizeInfo drawPrizeInfo)
    {
        List<DrawPrizeInfo> list = drawPrizeInfoService.selectDrawPrizeInfoList(drawPrizeInfo);
        ExcelUtil<DrawPrizeInfo> util = new ExcelUtil<DrawPrizeInfo>(DrawPrizeInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 新增存储奖品的基础信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存存储奖品的基础信息
     */
    @RequiresPermissions("activity:info:add")
    @Log(title = "存储奖品的基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawPrizeInfo drawPrizeInfo)
    {
        return toAjax(drawPrizeInfoService.insertDrawPrizeInfo(drawPrizeInfo));
    }

    /**
     * 修改存储奖品的基础信息
     */
    @GetMapping("/edit/{PRIZEID}")
    public String edit(@PathVariable("PRIZEID") String PRIZEID, ModelMap mmap)
    {
        DrawPrizeInfo drawPrizeInfo = drawPrizeInfoService.selectDrawPrizeInfoById(PRIZEID);
        mmap.put("drawPrizeInfo", drawPrizeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存存储奖品的基础信息
     */
    @RequiresPermissions("activity:info:edit")
    @Log(title = "存储奖品的基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawPrizeInfo drawPrizeInfo)
    {
        return toAjax(drawPrizeInfoService.updateDrawPrizeInfo(drawPrizeInfo));
    }

    /**
     * 删除存储奖品的基础信息
     */
    @RequiresPermissions("activity:info:remove")
    @Log(title = "存储奖品的基础信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawPrizeInfoService.deleteDrawPrizeInfoByIds(ids));
    }
}
