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

import com.cronie.mengyu.domain.Goods;
import com.cronie.mengyu.service.IGoodsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 投资品种 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/goods")
public class GoodsController extends BaseController
{
    private String prefix = "mengyu/goods";
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequiresPermissions("mengyu:goods:view")
	@GetMapping()
	public String goods()
	{
	    return prefix + "/goods";
	}
	
	/**
	 * 查询投资品种列表
	 */
	@RequiresPermissions("mengyu:goods:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Goods goods)
	{
		startPage();
        List<Goods> list = goodsService.selectGoodsList(goods);
		return getDataTable(list);
	}
	
	/**
	 * 新增投资品种
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存投资品种
	 */
	@RequiresPermissions("mengyu:goods:add")
	@Log(title = "投资品种", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Goods goods)
	{		
		return toAjax(goodsService.insertGoods(goods));
	}

	/**
	 * 修改投资品种
	 */
	@GetMapping("/edit/{code}")
	public String edit(@PathVariable("code") String code, ModelMap mmap)
	{
		Goods goods = goodsService.selectGoodsById(code);
		mmap.put("goods", goods);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存投资品种
	 */
	@RequiresPermissions("mengyu:goods:edit")
	@Log(title = "投资品种", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Goods goods)
	{		
		return toAjax(goodsService.updateGoods(goods));
	}
	
	/**
	 * 删除投资品种
	 */
	@RequiresPermissions("mengyu:goods:remove")
	@Log(title = "投资品种", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsService.deleteGoodsByIds(ids));
	}
	
}
