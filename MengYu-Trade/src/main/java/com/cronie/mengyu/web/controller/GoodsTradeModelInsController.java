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

import com.cronie.mengyu.domain.GoodsTradeModelIns;
import com.cronie.mengyu.service.IGoodsTradeModelInsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 计划模型实例 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/goodsTradeModelIns")
public class GoodsTradeModelInsController extends BaseController
{
    private String prefix = "mengyu/goodsTradeModelIns";
	
	@Autowired
	private IGoodsTradeModelInsService goodsTradeModelInsService;
	
	@RequiresPermissions("mengyu:goodsTradeModelIns:view")
	@GetMapping()
	public String goodsTradeModelIns()
	{
	    return prefix + "/goodsTradeModelIns";
	}
	
	/**
	 * 查询计划模型实例列表
	 */
	@RequiresPermissions("mengyu:goodsTradeModelIns:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GoodsTradeModelIns goodsTradeModelIns)
	{
		startPage();
        List<GoodsTradeModelIns> list = goodsTradeModelInsService.selectGoodsTradeModelInsList(goodsTradeModelIns);
		return getDataTable(list);
	}
	
	/**
	 * 新增计划模型实例
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存计划模型实例
	 */
	@RequiresPermissions("mengyu:goodsTradeModelIns:add")
	@Log(title = "计划模型实例", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodsTradeModelIns goodsTradeModelIns)
	{		
		return toAjax(goodsTradeModelInsService.insertGoodsTradeModelIns(goodsTradeModelIns));
	}

	/**
	 * 修改计划模型实例
	 */
	@GetMapping("/edit/{insId}")
	public String edit(@PathVariable("insId") Integer insId, ModelMap mmap)
	{
		GoodsTradeModelIns goodsTradeModelIns = goodsTradeModelInsService.selectGoodsTradeModelInsById(insId);
		mmap.put("goodsTradeModelIns", goodsTradeModelIns);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存计划模型实例
	 */
	@RequiresPermissions("mengyu:goodsTradeModelIns:edit")
	@Log(title = "计划模型实例", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodsTradeModelIns goodsTradeModelIns)
	{		
		return toAjax(goodsTradeModelInsService.updateGoodsTradeModelIns(goodsTradeModelIns));
	}
	
	/**
	 * 删除计划模型实例
	 */
	@RequiresPermissions("mengyu:goodsTradeModelIns:remove")
	@Log(title = "计划模型实例", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsTradeModelInsService.deleteGoodsTradeModelInsByIds(ids));
	}
	
}
