package com.cronie.mengyu.web.controller;

import java.util.Date;
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

import com.cronie.mengyu.domain.MoneyPoolLog;
import com.cronie.mengyu.service.IMoneyPoolLogService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 资金池交明细 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/moneyPoolLog")
public class MoneyPoolLogController extends BaseController
{
    private String prefix = "mengyu/moneyPoolLog";
	
	@Autowired
	private IMoneyPoolLogService moneyPoolLogService;
	
	@RequiresPermissions("mengyu:moneyPoolLog:view")
	@GetMapping()
	public String moneyPoolLog()
	{
	    return prefix + "/moneyPoolLog";
	}
	
	/**
	 * 查询资金池交明细列表
	 */
	@RequiresPermissions("mengyu:moneyPoolLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoneyPoolLog moneyPoolLog)
	{
		startPage();
        List<MoneyPoolLog> list = moneyPoolLogService.selectMoneyPoolLogList(moneyPoolLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增资金池交明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金池交明细
	 */
	@RequiresPermissions("mengyu:moneyPoolLog:add")
	@Log(title = "资金池交明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoneyPoolLog moneyPoolLog)
	{		
		moneyPoolLog.setCreateTime(new Date());
		return toAjax(moneyPoolLogService.insertMoneyPoolLog(moneyPoolLog));
	}

	/**
	 * 修改资金池交明细
	 */
	@GetMapping("/edit/{billType}")
	public String edit(@PathVariable("billType") Integer billType, ModelMap mmap)
	{
		MoneyPoolLog moneyPoolLog = moneyPoolLogService.selectMoneyPoolLogById(billType);
		mmap.put("moneyPoolLog", moneyPoolLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金池交明细
	 */
	@RequiresPermissions("mengyu:moneyPoolLog:edit")
	@Log(title = "资金池交明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoneyPoolLog moneyPoolLog)
	{		
		return toAjax(moneyPoolLogService.updateMoneyPoolLog(moneyPoolLog));
	}
	
	/**
	 * 删除资金池交明细
	 */
	@RequiresPermissions("mengyu:moneyPoolLog:remove")
	@Log(title = "资金池交明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moneyPoolLogService.deleteMoneyPoolLogByIds(ids));
	}
	
}
