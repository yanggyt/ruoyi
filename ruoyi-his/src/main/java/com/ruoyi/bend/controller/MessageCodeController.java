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
import com.ruoyi.bend.domain.MessageCode;
import com.ruoyi.bend.service.IMessageCodeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 短信管理Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/messageCode")
public class MessageCodeController extends BaseController
{
    private String prefix = "bend/messageCode";

    @Autowired
    private IMessageCodeService messageCodeService;

    @RequiresPermissions("bend:messageCode:view")
    @GetMapping()
    public String messageCode()
    {
        return prefix + "/messageCode";
    }

    /**
     * 查询短信管理列表
     */
    @RequiresPermissions("bend:messageCode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MessageCode messageCode)
    {
        startPage();
        List<MessageCode> list = messageCodeService.selectMessageCodeList(messageCode);
        return getDataTable(list);
    }

    /**
     * 导出短信管理列表
     */
    @RequiresPermissions("bend:messageCode:export")
    @Log(title = "短信管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MessageCode messageCode)
    {
        List<MessageCode> list = messageCodeService.selectMessageCodeList(messageCode);
        ExcelUtil<MessageCode> util = new ExcelUtil<MessageCode>(MessageCode.class);
        return util.exportExcel(list, "messageCode");
    }

    /**
     * 新增短信管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短信管理
     */
    @RequiresPermissions("bend:messageCode:add")
    @Log(title = "短信管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MessageCode messageCode)
    {
        return toAjax(messageCodeService.insertMessageCode(messageCode));
    }

    /**
     * 修改短信管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MessageCode messageCode = messageCodeService.selectMessageCodeById(id);
        mmap.put("messageCode", messageCode);
        return prefix + "/edit";
    }

    /**
     * 修改保存短信管理
     */
    @RequiresPermissions("bend:messageCode:edit")
    @Log(title = "短信管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MessageCode messageCode)
    {
        return toAjax(messageCodeService.updateMessageCode(messageCode));
    }

    /**
     * 删除短信管理
     */
    @RequiresPermissions("bend:messageCode:remove")
    @Log(title = "短信管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(messageCodeService.deleteMessageCodeByIds(ids));
    }
}
