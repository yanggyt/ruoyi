package com.cronie.mengyu.web.controller;

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

import com.cronie.mengyu.domain.MoneyPool;
import com.cronie.mengyu.service.IMoneyPoolService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 资金池 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/moneyPool")
public class MoneyPoolController extends BaseController
{
    private String prefix = "mengyu/moneyPool";
	
	@Autowired
	private IMoneyPoolService moneyPoolService;
	
	@RequiresPermissions("mengyu:moneyPool:view")
	@GetMapping()
	public String moneyPool()
	{
	    return prefix + "/moneyPool";
	}
	
	/**
	 * 查询资金池列表
	 */
	@RequiresPermissions("mengyu:moneyPool:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoneyPool moneyPool)
	{
		startPage();
        List<MoneyPool> list = moneyPoolService.selectMoneyPoolList(moneyPool);
		return getDataTable(list);
	}
	
	/**
	 * 新增资金池
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金池
	 */
	@RequiresPermissions("mengyu:moneyPool:add")
	@Log(title = "资金池", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoneyPool moneyPool)
	{		
		return toAjax(moneyPoolService.insertMoneyPool(moneyPool));
	}

	/**
	 * 修改资金池
	 */
	@GetMapping("/edit/{creater}")
	public String edit(@PathVariable("creater") Integer creater, ModelMap mmap)
	{
		MoneyPool moneyPool = moneyPoolService.selectMoneyPoolById(creater);
		mmap.put("moneyPool", moneyPool);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金池
	 */
	@RequiresPermissions("mengyu:moneyPool:edit")
	@Log(title = "资金池", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoneyPool moneyPool)
	{		
		return toAjax(moneyPoolService.updateMoneyPool(moneyPool));
	}
	
	/**
	 * 删除资金池
	 */
	@RequiresPermissions("mengyu:moneyPool:remove")
	@Log(title = "资金池", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moneyPoolService.deleteMoneyPoolByIds(ids));
	}
	
}
