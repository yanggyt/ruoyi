package com.cronie.mengyu.common.goods.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.ruoyi.common.utils.DateUtils;


@Component("tencentDataSource")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TencentLoadGoodsInfoImpl extends AbstractLoadGoodsInfo {
	
	@Override
	public String loadGoodsUrl(ActualTimeGoods goodsInfo) {
		return TradeConstants.DATASOURCE_TENCENT_URL.concat(goodsInfo.getPlateType()).concat(goodsInfo.getCode());
	}
	
	//	 0: 未知
	//	 1: 名字
	//	 2: 代码
	//	 3: 当前价格
	//	 4: 昨收
	//	 5: 今开
	//	 6: 成交量（手）
	//	 7: 外盘
	//	 8: 内盘
	//	 9: 买一
	//	10: 买一量（手）
	//	11-18: 买二 买五
	//	19: 卖一
	//	20: 卖一量
	//	21-28: 卖二 卖五
	//	29: 最近逐笔成交
	//	30: 时间
	//	31: 涨跌
	//	32: 涨跌%
	//	33: 最高
	//	34: 最低
	//	35: 价格/成交量（手）/成交额
	//	36: 成交量（手）
	//	37: 成交额（万）
	//	38: 换手率
	//	39: 市盈率
	//	40: 
	//	41: 最高
	//	42: 最低
	//	43: 振幅
	//	44: 流通市值
	//	45: 总市值
	//	46: 市净率
	//	47: 涨停价
	//	48: 跌停价
	@Override
	public void praseData(String data,ActualTimeGoods goodsInfo)  throws Exception{
		String pureData = null;
		pureData = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
		String datas[] = pureData.split("~");
		goodsInfo.setActualTimePrice(Double.parseDouble(datas[3]));
		goodsInfo.setYesterdayPrice(Double.parseDouble(datas[4]));
		goodsInfo.setTodayPrice(Double.parseDouble(datas[5]));
		BigDecimal bd = new BigDecimal(datas[6]) ;
		//手(100股)*单位（10000）= 1000000 万手
		BigDecimal _i = new BigDecimal(TradeConstants.DATASOURCE_TENCENT_MIN_UNIT_VOL) ;
		double v = bd.divide(_i,2,BigDecimal.ROUND_HALF_UP).doubleValue();
		goodsInfo.setActualTimeVolumes(v);
		goodsInfo.setName(datas[1]);
		goodsInfo.setDataSource(TradeConstants.DATASOURCE_TENCENT);
		goodsInfo.setRefreshTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,new Date()));
			
	}
	
	
	
}
