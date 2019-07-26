package com.bmw.servicecenter.controller;

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
import com.bmw.common.annotation.Log;
import com.bmw.common.enums.BusinessType;
import com.bmw.servicecenter.domain.Member;
import com.bmw.servicecenter.service.IMemberService;
import com.bmw.common.core.controller.BaseController;
import com.bmw.common.core.page.TableDataInfo;
import com.bmw.common.core.domain.AjaxResult;
import com.bmw.common.utils.poi.ExcelUtil;

/**
 * 客户 信息操作处理
 * 
 * @author bmw
 * @date 2019-07-26
 */
@Controller
@RequestMapping("/servicecenter/member")
public class MemberController extends BaseController
{
    private String prefix = "servicecenter/member";
	
	@Autowired
	private IMemberService memberService;
	
	@RequiresPermissions("servicecenter:member:view")
	@GetMapping()
	public String member()
	{
	    return prefix + "/member";
	}
	
	/**
	 * 查询客户列表
	 */
	@RequiresPermissions("servicecenter:member:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Member member)
	{
		startPage();
        List<Member> list = memberService.selectMemberList(member);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出客户列表
	 */
	@RequiresPermissions("servicecenter:member:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member)
    {
    	List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "member");
    }
	
	/**
	 * 新增客户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存客户
	 */
	@RequiresPermissions("servicecenter:member:add")
	@Log(title = "客户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Member member)
	{		
		return toAjax(memberService.insertMember(member));
	}

	/**
	 * 修改客户
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") Long memberId, ModelMap mmap)
	{
		Member member = memberService.selectMemberById(memberId);
		mmap.put("member", member);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户
	 */
	@RequiresPermissions("servicecenter:member:edit")
	@Log(title = "客户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Member member)
	{		
		return toAjax(memberService.updateMember(member));
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("servicecenter:member:remove")
	@Log(title = "客户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(memberService.deleteMemberByIds(ids));
	}
	
}
