package com.cronie.mengyu.common.constants;

public final class TradeConstants {
	
	//1.固定板指
	public final static String GOODS_FIXED_SH_PLATE_INDEX =  "000001";//上证
	public final static String GOODS_FIXED_SZ_PLATE_INDEX =  "399001";//深证
	public final static String GOODS_FIXED_ZXB_PLATE_INDEX =  "399005";//中小板
	public final static String GOODS_FIXED_CYB_PLATE_INDEX =  "399006";//创业板
	
	//2.商品类型
	public final static String GOODS_TYPE_STOCK = "stock";
	public final static String GOODS_TYPE_FUND = "fund";
	
	//3.所属板块
	public final static String GOODS_PLATE_BELONG_SH="sh" ;
	public final static String GOODS_PLATE_BELONG_SZ="sz" ;
	
	//4.数据源源
	public final static String DATASOURCE_SINA_URL="http://hq.sinajs.cn/list=" ;//新浪请求地址
	public final static String DATASOURCE_SINA = "Sina" ;//数据来源:新浪
	public final static  String DATASOURCE_SINA_MIN_UNIT_VOL = "1000000" ;//成交量单位
	
	public final static String DATASOURCE_TENCENT_URL="http://qt.gtimg.cn/q=" ;//腾讯请求地址
	public final static String DATASOURCE_TENCENT = "Tencent" ;//数据来源:腾讯
	public final static  String DATASOURCE_TENCENT_MIN_UNIT_VOL = "10000" ;//成交量单位
	
	//5.计划模型状态
	public final static Integer GOODS_MODEL_STATUS_VALID = 1 ;//启用
	public final static Integer GOODS_MODEL_STATUS_INVALID = 0 ;//停用
	
	//6.交易参数
	public final static String GOODS_PRICE_INCREASE_RATE="goods_price_increase_rate" ;//涨副比
	public final static String GOODS_VOLUMES_INCREASE_RATE="goods_volumes_increase_rate" ;//放量比
	public final static String MONEY_POOL_LOSS_RATE = "money_pool_loss_rate" ;//风控比
	public final static String MONEY_POOL_TRADE_NUMS= "money_pool_trade_nums" ;//计划交易次数
	public final static String GOODS_TRADE_MIN_AMOUNT="goods_trade_min_amount" ;//最小建仓份额
	
	//7.模型实例状态
	public final static Integer MODEL_INS_STATUS_RUNING = 1 ;//进行中
	public final static Integer MODEL_INS_STATUS_COMPLETED = 2 ;//已完成
	public final static Integer MODEL_INS_STATUS_NOTSTARTED= 0 ;//已完成
	
	//邮件提醒状态
	public final static Integer MAIL_NOTICE_YES = 1;//已提醒
	public final static Integer MAIL_NOTICE_NO = 0;//未提醒
	
	

}
