package com.ruoyi.his.controller;

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
import com.ruoyi.his.domain.HisWechatProvider;
import com.ruoyi.his.service.IHisWechatProviderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信服务商Controller
 * 
 * @author bend
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/his/wechatProvider")
public class HisWechatProviderController extends BaseController
{
    private String prefix = "his/wechatProvider";

    @Autowired
    private IHisWechatProviderService hisWechatProviderService;

    @RequiresPermissions("his:wechatProvider:view")
    @GetMapping()
    public String wechatProvider()
    {
        return prefix + "/wechatProvider";
    }

    /**
     * 查询微信服务商列表
     */
    @RequiresPermissions("his:wechatProvider:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisWechatProvider hisWechatProvider)
    {
        startPage();
        List<HisWechatProvider> list = hisWechatProviderService.selectHisWechatProviderList(hisWechatProvider);
        return getDataTable(list);
    }

    /**
     * 导出微信服务商列表
     */
    @RequiresPermissions("his:wechatProvider:export")
    @Log(title = "微信服务商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisWechatProvider hisWechatProvider)
    {
        List<HisWechatProvider> list = hisWechatProviderService.selectHisWechatProviderList(hisWechatProvider);
        ExcelUtil<HisWechatProvider> util = new ExcelUtil<HisWechatProvider>(HisWechatProvider.class);
        return util.exportExcel(list, "wechatProvider");
    }

    /**
     * 新增微信服务商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存微信服务商
     */
    @RequiresPermissions("his:wechatProvider:add")
    @Log(title = "微信服务商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisWechatProvider hisWechatProvider)
    {
        return toAjax(hisWechatProviderService.insertHisWechatProvider(hisWechatProvider));
    }

    /**
     * 修改微信服务商
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisWechatProvider hisWechatProvider = hisWechatProviderService.selectHisWechatProviderById(id);
        mmap.put("hisWechatProvider", hisWechatProvider);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信服务商
     */
    @RequiresPermissions("his:wechatProvider:edit")
    @Log(title = "微信服务商", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisWechatProvider hisWechatProvider)
    {
        return toAjax(hisWechatProviderService.updateHisWechatProvider(hisWechatProvider));
    }

    /**
     * 删除微信服务商
     */
    @RequiresPermissions("his:wechatProvider:remove")
    @Log(title = "微信服务商", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisWechatProviderService.deleteHisWechatProviderByIds(ids));
    }
}
