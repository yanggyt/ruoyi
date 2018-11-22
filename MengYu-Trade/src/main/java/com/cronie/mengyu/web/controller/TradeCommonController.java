package com.cronie.mengyu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.cronie.mengyu.common.goods.LoadGoodsInfoFactory;
import com.cronie.mengyu.common.goods.ILoadGoodsInfo;
import com.ruoyi.framework.web.base.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "MengYu常用接口", description = "MengYu常用接口")
@Controller
@RequestMapping("/trade/common")
public class TradeCommonController extends BaseController {
	
	 
	@Autowired
	LoadGoodsInfoFactory loadGoodsInfoFactory ;
	
	/**
	 * 查询商品实时信息
	 */
    @ApiOperation(value = "实时查询品种代码基本信息", notes = "实时查询品种代码基本信息", httpMethod = "GET")
	@ResponseBody
	@RequestMapping("/loadGoodsInfo")
	public ActualTimeGoods loadGoodsInfo(
			@ApiParam(value = "品种代码，必填项") @RequestParam(value = "code", required = true, defaultValue = "000001") 
			String code)
	{		
		ILoadGoodsInfo loadGoodsInfoInterface = loadGoodsInfoFactory.getLoadGoodsInfoInstance() ;
		ActualTimeGoods goodsInfo  = loadGoodsInfoInterface.loadGoodsInfo(code) ;
		return goodsInfo;
	}
	

}
