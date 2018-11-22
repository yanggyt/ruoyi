package com.cronie.mengyu.common.goods.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.cronie.mengyu.common.goods.ILoadGoodsInfo;
import com.cronie.mengyu.common.http.HttpUtil;

public abstract class AbstractLoadGoodsInfo implements ILoadGoodsInfo {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractLoadGoodsInfo.class);
	
	@Override
	public ActualTimeGoods loadGoodsInfo(String code) {
		
		ActualTimeGoods goodsInfo = new ActualTimeGoods();
		//解析代码
		praseStockCode(code, goodsInfo);
		//加载URL
		String url = loadGoodsUrl(goodsInfo);
		
		String data;
		try {
			data = HttpUtil.sendGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("查询品种【"+code+"】的信息失败。",e);
			return  null;
		}
		
		//解析数据
		try {
			praseData(data,goodsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("解析品种【"+code+"】的信息失败,原始数据:【"+data+"】",e);
			return  null;
		}
		logger.info(JSONObject.toJSONString(goodsInfo));
		return goodsInfo;
	}
	
	
	/**
	 * 解析URl
	 * @return String
	 */
	protected abstract String loadGoodsUrl(ActualTimeGoods goodsInfo);
	
	/**
	 * 解析数据
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	protected abstract void praseData(String data,ActualTimeGoods goodsInfo) throws Exception;
	
	/**
	 * @author: 王冲
	 * @description:解析代码 信息
	 * @date:2016年3月4日
	 */
	protected void praseStockCode(String code ,ActualTimeGoods goodsInfo){
		
		goodsInfo.setCode(code);
		//版指
		if(code.equalsIgnoreCase(TradeConstants.GOODS_FIXED_SH_PLATE_INDEX)) {
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SH);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_STOCK);
		}else if (code.equalsIgnoreCase(TradeConstants.GOODS_FIXED_SZ_PLATE_INDEX)||
				code.equalsIgnoreCase(TradeConstants.GOODS_FIXED_CYB_PLATE_INDEX)||
				code.equalsIgnoreCase(TradeConstants.GOODS_FIXED_ZXB_PLATE_INDEX)  ) {
			
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SZ);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_STOCK);
		}
		//
		else if(code.startsWith("60")) {
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SH);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_STOCK);
		}else if (code.startsWith("002")|| code.startsWith("300")) {
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SZ);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_STOCK);
		}else if (code.startsWith("510")) {
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SH);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_FUND);
		}else if ( code.startsWith("150")) {
			goodsInfo.setPlateType(TradeConstants.GOODS_PLATE_BELONG_SZ);
			goodsInfo.setCodeType(TradeConstants.GOODS_TYPE_FUND);
		}
		
//		if(stockCode.equalsIgnoreCase("000001")){
//			
//			
//		}else if(stockCode.startsWith("60")){
//			stockCodeInfo.setStockType(TYPE_STOCK);
//			stockCodeInfo.setBelong("sh");
//		}else if(stockCode.startsWith("000")){
//			stockCodeInfo.setStockType(TYPE_STOCK);
//			stockCodeInfo.setBelong("sz");
//		}else if(stockCode.startsWith("300")){
//			stockCodeInfo.setStockType(TYPE_STOCK);
//			stockCodeInfo.setBelong("sz");
//		}else if(stockCode.startsWith("399")){
//			stockCodeInfo.setStockType(TYPE_STOCK);
//			stockCodeInfo.setBelong("sz");
//		}else if(stockCode.startsWith("150")){
//			stockCodeInfo.setStockType(TYPE_FUND);
//			stockCodeInfo.setBelong("sz");
//		}else if(stockCode.startsWith("002")){
//			stockCodeInfo.setStockType(TYPE_STOCK);
//			stockCodeInfo.setBelong("sz");
//		}else if(stockCode.startsWith("502")){
//			stockCodeInfo.setStockType(TYPE_FUND);
//			stockCodeInfo.setBelong("sh");
//		}else {
//			stockCodeInfo.setBelong("sh");
//			stockCodeInfo.setStockType(TYPE_FUND);
//		}
	}
}
