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
import com.ruoyi.bend.domain.Member;
import com.ruoyi.bend.service.IMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员列表Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/member")
public class MemberController extends BaseController
{
    private String prefix = "bend/member";

    @Autowired
    private IMemberService memberService;

    @RequiresPermissions("bend:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询会员列表列表
     */
    @RequiresPermissions("bend:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Member member)
    {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    /**
     * 导出会员列表列表
     */
    @RequiresPermissions("bend:member:export")
    @Log(title = "会员列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增会员列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员列表
     */
    @RequiresPermissions("bend:member:add")
    @Log(title = "会员列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Member member)
    {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改会员列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Member member = memberService.selectMemberById(id);
        mmap.put("member", member);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员列表
     */
    @RequiresPermissions("bend:member:edit")
    @Log(title = "会员列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Member member)
    {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除会员列表
     */
    @RequiresPermissions("bend:member:remove")
    @Log(title = "会员列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberService.deleteMemberByIds(ids));
    }
}
