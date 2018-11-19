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

import com.cronie.mengyu.domain.GoodsTradeModelInsNode;
import com.cronie.mengyu.service.IGoodsTradeModelInsNodeService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 交易计划操作 信息操作处理
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Controller
@RequestMapping("/mengyu/goodsTradeModelInsNode")
public class GoodsTradeModelInsNodeController extends BaseController
{
    private String prefix = "mengyu/goodsTradeModelInsNode";
	
	@Autowired
	private IGoodsTradeModelInsNodeService goodsTradeModelInsNodeService;
	
	@RequiresPermissions("mengyu:goodsTradeModelInsNode:view")
	@GetMapping()
	public String goodsTradeModelInsNode()
	{
	    return prefix + "/goodsTradeModelInsNode";
	}
	
	/**
	 * 查询交易计划操作列表
	 */
	@RequiresPermissions("mengyu:goodsTradeModelInsNode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{
		startPage();
        List<GoodsTradeModelInsNode> list = goodsTradeModelInsNodeService.selectGoodsTradeModelInsNodeList(goodsTradeModelInsNode);
		return getDataTable(list);
	}
	
	/**
	 * 新增交易计划操作
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存交易计划操作
	 */
	@RequiresPermissions("mengyu:goodsTradeModelInsNode:add")
	@Log(title = "交易计划操作", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{		
		goodsTradeModelInsNode.setCreater(getUserId().intValue());
		goodsTradeModelInsNode.setCreateTime(new Date());
		return toAjax(goodsTradeModelInsNodeService.insertGoodsTradeModelInsNode(goodsTradeModelInsNode));
	}

	/**
	 * 修改交易计划操作
	 */
	@GetMapping("/edit/{nodeId}")
	public String edit(@PathVariable("nodeId") Integer nodeId, ModelMap mmap)
	{
		GoodsTradeModelInsNode goodsTradeModelInsNode = goodsTradeModelInsNodeService.selectGoodsTradeModelInsNodeById(nodeId);
		mmap.put("goodsTradeModelInsNode", goodsTradeModelInsNode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存交易计划操作
	 */
	@RequiresPermissions("mengyu:goodsTradeModelInsNode:edit")
	@Log(title = "交易计划操作", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{		
		return toAjax(goodsTradeModelInsNodeService.updateGoodsTradeModelInsNode(goodsTradeModelInsNode));
	}
	
	/**
	 * 删除交易计划操作
	 */
	@RequiresPermissions("mengyu:goodsTradeModelInsNode:remove")
	@Log(title = "交易计划操作", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(goodsTradeModelInsNodeService.deleteGoodsTradeModelInsNodeByIds(ids));
	}
	
}
