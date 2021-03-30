package com.sinosoft.activity.controller;


import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.domain.DrawPrizeInfo;
import com.sinosoft.activity.service.IDrawConfigService;
import com.sinosoft.activity.service.IDrawPrizeInfoService;
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

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储奖项配置信息Controller
 *
 * @author ruoyi
 * @date 2021-03-26
 */
@Controller
@RequestMapping("/activity/config")
public class DrawConfigController extends BaseController
{
    private String prefix = "activity/info";

    @Autowired
    private IDrawConfigService drawConfigService;

    @Autowired
    private IDrawPrizeInfoService iDrawPrizeInfoService;
    @RequiresPermissions("activity:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/configList";
    }

    /**
     * 查询存储奖项配置信息列表
     */
    @RequiresPermissions("activity:config:list")
    @PostMapping("/{DRAWCODE}/list")
    @ResponseBody
    public TableDataInfo list(DrawConfig drawConfig)
    {
        startPage();
        List<DrawConfig> list = drawConfigService.selectDrawConfigList(drawConfig);
        return getDataTable(list);
    }

    /**
     * 导出存储奖项配置信息列表
     */
    @RequiresPermissions("activity:config:export")
    @Log(title = "存储奖项配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawConfig drawConfig)
    {
        List<DrawConfig> list = drawConfigService.selectDrawConfigList(drawConfig);
        ExcelUtil<DrawConfig> util = new ExcelUtil<DrawConfig>(DrawConfig.class);
        return util.exportExcel(list, "config");
    }

    /**
     * 新增存储奖项配置信息
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id")String DRAWCODE ,ModelMap mapp)
    {
        mapp.put("DRAWCODE",DRAWCODE);
        return prefix + "/configAdd";
    }

    /**
     * 新增保存存储奖项配置信息
     */
    @RequiresPermissions("activity:config:add")
    @Log(title = "存储奖项配置信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawConfig drawConfig)
    {
        drawConfig.setCREATETIMESTAMP(new Date());
        return toAjax(drawConfigService.insertDrawConfig(drawConfig));
    }

    /**
     * 修改存储奖项配置信息
     */
    @GetMapping("/edit/{DRAWCONFIGID}")
    public String edit(@PathVariable("DRAWCONFIGID") String DRAWCONFIGID, ModelMap mmap)
    {
        DrawConfig drawConfig = drawConfigService.selectDrawConfigById(DRAWCONFIGID);
        mmap.put("drawConfig", drawConfig);
        return prefix + "/configEdit";
    }

    /**
     * 修改保存存储奖项配置信息
     */
    @RequiresPermissions("activity:config:edit")
    @Log(title = "存储奖项配置信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawConfig drawConfig)
    {
        drawConfig.setLASTUPDATETIMESTAMP(new Date());
        return toAjax(drawConfigService.updateDrawConfig(drawConfig));
    }

    /**
     * 删除存储奖项配置信息
     */
    @RequiresPermissions("activity:config:remove")
    @Log(title = "存储奖项配置信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawConfigService.deleteDrawConfigByIds(ids));
    }


    /**
     * 获取存储奖品的基础信息对象
     *
     * @return
     */
    @GetMapping("/prizeInfo")
    @ResponseBody
    public List<DrawPrizeInfo> selectDrawPrizeInfoList(DrawPrizeInfo drawPrizeInfo) {

        return iDrawPrizeInfoService.selectDrawPrizeInfoList(drawPrizeInfo);
    }
}
