package com.ruoyi.vip.controller;

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
import com.ruoyi.vip.domain.VipUserCertificate;
import com.ruoyi.vip.service.IVipUserCertificateService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的订单 信息操作处理
 * 
 * @author zhujj
 * @date 2019-01-15
 */
@Controller
@RequestMapping("/vip/vipUserCertificate")
public class VipUserCertificateController extends BaseController
{
    private String prefix = "vip/vipUserCertificate";
	
	@Autowired
	private IVipUserCertificateService vipUserCertificateService;
	
	@RequiresPermissions("vip:vipUserCertificate:view")
	@GetMapping()
	public String vipUserCertificate()
	{
	    return prefix + "/vipUserCertificate";
	}
	
	/**
	 * 查询我的订单列表
	 */
	@RequiresPermissions("vip:vipUserCertificate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VipUserCertificate vipUserCertificate)
	{
        List<VipUserCertificate> list = vipUserCertificateService.selectVipUserCertificatePage(vipUserCertificate);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出我的订单列表
	 */
	@RequiresPermissions("vip:vipUserCertificate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VipUserCertificate vipUserCertificate)
    {
    	List<VipUserCertificate> list = vipUserCertificateService.selectVipUserCertificateList(vipUserCertificate);
        ExcelUtil<VipUserCertificate> util = new ExcelUtil<VipUserCertificate>(VipUserCertificate.class);
        return util.exportExcel(list, "vipUserCertificate");
    }
	
	/**
	 * 新增我的订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存我的订单
	 */
	@RequiresPermissions("vip:vipUserCertificate:add")
	@Log(title = "我的订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VipUserCertificate vipUserCertificate)
	{		
		return toAjax(vipUserCertificateService.insert(vipUserCertificate));
	}

	/**
	 * 修改我的订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		VipUserCertificate vipUserCertificate = vipUserCertificateService.selectById(id);
		mmap.put("vipUserCertificate", vipUserCertificate);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的订单
	 */
	@RequiresPermissions("vip:vipUserCertificate:edit")
	@Log(title = "我的订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VipUserCertificate vipUserCertificate)
	{		
		return toAjax(vipUserCertificateService.updateById(vipUserCertificate));
	}
	
	/**
	 * 删除我的订单
	 */
	@RequiresPermissions("vip:vipUserCertificate:remove")
	@Log(title = "我的订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vipUserCertificateService.deleteByIds(ids));
	}
	
}
