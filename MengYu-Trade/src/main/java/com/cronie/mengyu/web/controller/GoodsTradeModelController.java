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

import com.cronie.mengyu.domain.GoodsTradeModel;
import com.cronie.mengyu.service.IGoodsTradeModelService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 品种计划模型 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/goodsTradeModel")
public class GoodsTradeModelController extends BaseController
{
    private String prefix = "mengyu/goodsTradeModel";
	
	@Autowired
	private IGoodsTradeModelService goodsTradeModelService;
	
	@RequiresPermissions("mengyu:goodsTradeModel:view")
	@GetMapping()
	public String goodsTradeModel()
	{
	    return prefix + "/goodsTradeModel";
	}
	
	/**
	 * 查询品种计划模型列表
	 */
	@RequiresPermissions("mengyu:goodsTradeModel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GoodsTradeModel goodsTradeModel)
	{
		startPage();
        List<GoodsTradeModel> list = goodsTradeModelService.selectGoodsTradeModelList(goodsTradeModel);
		return getDataTable(list);
	}
	
	/**
	 * 新增品种计划模型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存品种计划模型
	 */
	@RequiresPermissions("mengyu:goodsTradeModel:add")
	@Log(title = "品种计划模型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodsTradeModel goodsTradeModel)
	{		
		return toAjax(goodsTradeModelService.insertGoodsTradeModel(goodsTradeModel));
	}

	/**
	 * 修改品种计划模型
	 */
	@GetMapping("/edit/{modelId}")
	public String edit(@PathVariable("modelId") Integer modelId, ModelMap mmap)
	{
		GoodsTradeModel goodsTradeModel = goodsTradeModelService.selectGoodsTradeModelById(modelId);
		mmap.put("goodsTradeModel", goodsTradeModel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存品种计划模型
	 */
	@RequiresPermissions("mengyu:goodsTradeModel:edit")
	@Log(title = "品种计划模型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodsTradeModel goodsTradeModel)
	{		
		return toAjax(goodsTradeModelService.updateGoodsTradeModel(goodsTradeModel));
	}
	
	/**
	 * 删除品种计划模型
	 */
	@RequiresPermissions("mengyu:goodsTradeModel:remove")
	@Log(title = "品种计划模型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsTradeModelService.deleteGoodsTradeModelByIds(ids));
	}
	
}
