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
import com.ruoyi.bend.domain.WechatMember;
import com.ruoyi.bend.service.IWechatMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信用户Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/wechatMember")
public class WechatMemberController extends BaseController
{
    private String prefix = "bend/wechatMember";

    @Autowired
    private IWechatMemberService wechatMemberService;

    @RequiresPermissions("bend:wechatMember:view")
    @GetMapping()
    public String wechatMember()
    {
        return prefix + "/wechatMember";
    }

    /**
     * 查询微信用户列表
     */
    @RequiresPermissions("bend:wechatMember:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WechatMember wechatMember)
    {
        startPage();
        List<WechatMember> list = wechatMemberService.selectWechatMemberList(wechatMember);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表
     */
    @RequiresPermissions("bend:wechatMember:export")
    @Log(title = "微信用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatMember wechatMember)
    {
        List<WechatMember> list = wechatMemberService.selectWechatMemberList(wechatMember);
        ExcelUtil<WechatMember> util = new ExcelUtil<WechatMember>(WechatMember.class);
        return util.exportExcel(list, "wechatMember");
    }

    /**
     * 新增微信用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存微信用户
     */
    @RequiresPermissions("bend:wechatMember:add")
    @Log(title = "微信用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WechatMember wechatMember)
    {
        return toAjax(wechatMemberService.insertWechatMember(wechatMember));
    }

    /**
     * 修改微信用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WechatMember wechatMember = wechatMemberService.selectWechatMemberById(id);
        mmap.put("wechatMember", wechatMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信用户
     */
    @RequiresPermissions("bend:wechatMember:edit")
    @Log(title = "微信用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WechatMember wechatMember)
    {
        return toAjax(wechatMemberService.updateWechatMember(wechatMember));
    }

    /**
     * 删除微信用户
     */
    @RequiresPermissions("bend:wechatMember:remove")
    @Log(title = "微信用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wechatMemberService.deleteWechatMemberByIds(ids));
    }
}
