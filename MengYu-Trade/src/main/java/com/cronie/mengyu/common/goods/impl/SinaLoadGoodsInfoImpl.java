package com.cronie.mengyu.common.goods.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.ruoyi.common.utils.DateUtils;


@Component("sinaDataSource")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SinaLoadGoodsInfoImpl extends AbstractLoadGoodsInfo {

	
	
	@Override
	public String loadGoodsUrl(ActualTimeGoods goodsInfo) {
		return TradeConstants.DATASOURCE_SINA_URL.concat(goodsInfo.getPlateType()).concat(goodsInfo.getCode());
	}
	
	
	//	0：”大秦铁路”，股票名字；
	//	1：”27.55″，今日开盘价；
	//	2：”27.25″，昨日收盘价；
	//	3：”26.91″，当前价格；
	//	4：”27.55″，今日最高价；
	//	5：”26.20″，今日最低价；
	//	6：”26.91″，竞买价，即“买一”报价；
	//	7：”26.92″，竞卖价，即“卖一”报价；
	//	8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
	//	9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
	//	10：”4695″，“买一”申请4695股，即47手；
	//	11：”26.91″，“买一”报价；
	//	12：”57590″，“买二”
	//	13：”26.90″，“买二”
	//	14：”14700″，“买三”
	//	15：”26.89″，“买三”
	//	16：”14300″，“买四”
	//	17：”26.88″，“买四”
	//	18：”15100″，“买五”
	//	19：”26.87″，“买五”
	//	20：”3100″，“卖一”申报3100股，即31手；
	//	21：”26.92″，“卖一”报价
	//	(22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
	//	30：”2008-01-11″，日期；
	//	31：”15:05:32″，时间；
	@Override
	public void praseData(String data,ActualTimeGoods goodsInfo) throws Exception{
		String pureData = null;
		pureData = data.substring(data.indexOf("\"")+1, data.lastIndexOf("\""));
		String datas[] = pureData.split(",");
		goodsInfo.setActualTimePrice(Double.parseDouble(datas[3]));
		goodsInfo.setYesterdayPrice(Double.parseDouble(datas[2]));
		goodsInfo.setTodayPrice(Double.parseDouble(datas[1]));
		BigDecimal bd = new BigDecimal(datas[8]) ;
		//手(100股)*单位（10000）= 1000000 万手
		BigDecimal _i = new BigDecimal(TradeConstants.DATASOURCE_SINA_MIN_UNIT_VOL) ;
		double v = bd.divide(_i,2,BigDecimal.ROUND_HALF_UP).doubleValue();
		goodsInfo.setActualTimeVolumes(v);
		goodsInfo.setName(datas[0]);
		goodsInfo.setDataSource(TradeConstants.DATASOURCE_SINA);
		goodsInfo.setRefreshTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,new Date()));
	}
	
	
}
