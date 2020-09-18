package com.ruoyi.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.business.utils.Encrypt;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员Controller
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
@Controller
@RequestMapping("/business/member")
public class BizMemberController extends BaseController
{
    private String prefix = "business/member";

    @Autowired
    private IBizMemberService bizMemberService;

    @RequiresPermissions("business:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询会员列表
     */
    @RequiresPermissions("business:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizMember bizMember)
    {
        startPage();
        List<BizMember> list = bizMemberService.selectBizMemberList(bizMember);
        return getDataTable(list);
    }

    /**
     * 导出会员列表
     */
    @RequiresPermissions("business:member:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizMember bizMember)
    {
        List<BizMember> list = bizMemberService.selectBizMemberList(bizMember);
        ExcelUtil<BizMember> util = new ExcelUtil<BizMember>(BizMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增会员
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员
     */
    @RequiresPermissions("business:member:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizMember bizMember)
    {
        return toAjax(bizMemberService.insertBizMember(bizMember));
    }

    /**
     * 修改会员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BizMember bizMember = bizMemberService.selectBizMemberById(id);
        mmap.put("bizMember", bizMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizMember bizMember)
    {
        return toAjax(bizMemberService.updateBizMemberAndDou(bizMember));
    }

    /**
     * 查看会员密码
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员密码", businessType = BusinessType.UPDATE)
    @PostMapping("/showPassword")
    @ResponseBody
    public AjaxResult showPassword(Long memberID)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pwd", Encrypt.decrypt(bizMember.getPassword()));
        return AjaxResult.success(resultMap);
    }

    /**
     * 修改会员密码
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员密码", businessType = BusinessType.UPDATE)
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(Long memberID, String password)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        if(bizMember == null || StringUtils.isEmpty(password)) return toAjax(0);
        //加密
        bizMember.setPassword(Encrypt.encrypt(password));
        return toAjax(bizMemberService.updateBizMember(bizMember));
    }

    /**
     * 删除会员
     */
    @RequiresPermissions("business:member:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bizMemberService.deleteBizMemberByIds(ids));
    }
}
