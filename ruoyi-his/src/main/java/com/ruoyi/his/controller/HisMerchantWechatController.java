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
import com.ruoyi.his.domain.HisMerchantWechat;
import com.ruoyi.his.service.IHisMerchantWechatService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 特约商户Controller
 * 
 * @author bend
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/his/merchantWechat")
public class HisMerchantWechatController extends BaseController
{
    private String prefix = "his/merchantWechat";

    @Autowired
    private IHisMerchantWechatService hisMerchantWechatService;

    @RequiresPermissions("his:merchantWechat:view")
    @GetMapping()
    public String merchantWechat()
    {
        return prefix + "/merchantWechat";
    }

    /**
     * 查询特约商户列表
     */
    @RequiresPermissions("his:merchantWechat:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisMerchantWechat hisMerchantWechat)
    {
        startPage();
        List<HisMerchantWechat> list = hisMerchantWechatService.selectHisMerchantWechatList(hisMerchantWechat);
        return getDataTable(list);
    }

    /**
     * 导出特约商户列表
     */
    @RequiresPermissions("his:merchantWechat:export")
    @Log(title = "特约商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisMerchantWechat hisMerchantWechat)
    {
        List<HisMerchantWechat> list = hisMerchantWechatService.selectHisMerchantWechatList(hisMerchantWechat);
        ExcelUtil<HisMerchantWechat> util = new ExcelUtil<HisMerchantWechat>(HisMerchantWechat.class);
        return util.exportExcel(list, "merchantWechat");
    }

    /**
     * 新增特约商户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存特约商户
     */
    @RequiresPermissions("his:merchantWechat:add")
    @Log(title = "特约商户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisMerchantWechat hisMerchantWechat)
    {
        return toAjax(hisMerchantWechatService.insertHisMerchantWechat(hisMerchantWechat));
    }

    /**
     * 修改特约商户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisMerchantWechat hisMerchantWechat = hisMerchantWechatService.selectHisMerchantWechatById(id);
        mmap.put("hisMerchantWechat", hisMerchantWechat);
        return prefix + "/edit";
    }

    /**
     * 修改保存特约商户
     */
    @RequiresPermissions("his:merchantWechat:edit")
    @Log(title = "特约商户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisMerchantWechat hisMerchantWechat)
    {
        return toAjax(hisMerchantWechatService.updateHisMerchantWechat(hisMerchantWechat));
    }

    /**
     * 删除特约商户
     */
    @RequiresPermissions("his:merchantWechat:remove")
    @Log(title = "特约商户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisMerchantWechatService.deleteHisMerchantWechatByIds(ids));
    }
}
