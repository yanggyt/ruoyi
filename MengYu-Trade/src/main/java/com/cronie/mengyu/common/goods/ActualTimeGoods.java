package com.cronie.mengyu.common.goods;

import com.cronie.mengyu.domain.Goods;


/**
 * 实时商品信息
 * @author cronie
 *
 */
public class ActualTimeGoods extends Goods {

	private static final long serialVersionUID = 1L;
	
	public double actualTimePrice ;//当前价格
	public double actualTimeVolumes ;//当日成交量
	public String refreshTime ;//刷新时间
	public double yesterdayPrice;//昨天收盘价
	public double todayPrice;//今天开盘价
	public String dataSource ;
	public double getActualTimePrice() {
		return actualTimePrice;
	}
	public void setActualTimePrice(double actualTimePrice) {
		this.actualTimePrice = actualTimePrice;
	}
	public double getActualTimeVolumes() {
		return actualTimeVolumes;
	}
	public void setActualTimeVolumes(double actualTimeVolumes) {
		this.actualTimeVolumes = actualTimeVolumes;
	}
	public String getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
	public double getYesterdayPrice() {
		return yesterdayPrice;
	}
	public void setYesterdayPrice(double yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}
	public double getTodayPrice() {
		return todayPrice;
	}
	public void setTodayPrice(double todayPrice) {
		this.todayPrice = todayPrice;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
