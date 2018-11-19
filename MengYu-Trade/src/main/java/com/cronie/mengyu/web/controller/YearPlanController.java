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

import com.cronie.mengyu.domain.YearPlan;
import com.cronie.mengyu.service.IYearPlanService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 年度计划 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/yearPlan")
public class YearPlanController extends BaseController
{
    private String prefix = "mengyu/yearPlan";
	
	@Autowired
	private IYearPlanService yearPlanService;
	
	@RequiresPermissions("mengyu:yearPlan:view")
	@GetMapping()
	public String yearPlan()
	{
	    return prefix + "/yearPlan";
	}
	
	/**
	 * 查询年度计划列表
	 */
	@RequiresPermissions("mengyu:yearPlan:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(YearPlan yearPlan)
	{
		startPage();
        List<YearPlan> list = yearPlanService.selectYearPlanList(yearPlan);
		return getDataTable(list);
	}
	
	/**
	 * 新增年度计划
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存年度计划
	 */
	@RequiresPermissions("mengyu:yearPlan:add")
	@Log(title = "年度计划", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(YearPlan yearPlan)
	{		
		yearPlan.setCreater(getUserId().intValue());
		yearPlan.setCreateTime(new Date());
		return toAjax(yearPlanService.insertYearPlan(yearPlan));
	}

	/**
	 * 修改年度计划
	 */
	@GetMapping("/edit/{year}")
	public String edit(@PathVariable("year") String year, ModelMap mmap)
	{
		YearPlan yearPlan = yearPlanService.selectYearPlanById(year);
		mmap.put("yearPlan", yearPlan);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存年度计划
	 */
	@RequiresPermissions("mengyu:yearPlan:edit")
	@Log(title = "年度计划", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(YearPlan yearPlan)
	{		
		return toAjax(yearPlanService.updateYearPlan(yearPlan));
	}
	
	/**
	 * 删除年度计划
	 */
	@RequiresPermissions("mengyu:yearPlan:remove")
	@Log(title = "年度计划", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(yearPlanService.deleteYearPlanByIds(ids));
	}
	
}
