package com.cronie.mengyu.monitor.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.cronie.mengyu.common.goods.ILoadGoodsInfo;
import com.cronie.mengyu.common.goods.LoadGoodsInfoFactory;
import com.cronie.mengyu.domain.GoodsTradeModel;
import com.cronie.mengyu.domain.MoneyPool;
import com.cronie.mengyu.monitor.AbstractMonitor;
import com.cronie.mengyu.service.IGoodsTradeModelService;
import com.cronie.mengyu.service.IMoneyPoolService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 
 * @author 监控计划模型
 *
 */
@Component("monitorTradeModel")
public class MonitorTradeModelImpl extends AbstractMonitor {
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorTradeModelImpl.class);
	
	//模型服务层
	@Autowired
	IGoodsTradeModelService goodsTradeModelService ;
	
	//系统参数服务层
	@Autowired
	ISysConfigService sysConfigService ;
	
	//实时数据源工厂
	@Autowired
	LoadGoodsInfoFactory loadGoodsInfoFactory ;
	
	//资金池服务
	@Autowired
	IMoneyPoolService moneyPoolService ;
	
	//投资品种涨幅比
	BigDecimal goodsPriceIncreaseRate ;
	//投资品种放量比
	BigDecimal goodsVolumesIncreaseRate;
	//资金池风险控比(至多亏损资金池中的百分之多少钱)
	BigDecimal moneyPoolLossRate;
	//交易次数
	BigDecimal moneyPoolTradeNums ;
	//最小交易份额
	Integer goodsTradeMinAmount = 100;
	
	/**
	 * 系统参数配置
	 */
	@PostConstruct
	protected void initSysConfig() {
		//1.设置涨副比
		String goodsPriceIncreaseRateStr = sysConfigService.selectConfigByKey(TradeConstants.GOODS_PRICE_INCREASE_RATE) ;
		if(StringUtils.isBlank(goodsPriceIncreaseRateStr) || StringUtils.isEmpty(goodsPriceIncreaseRateStr)) {
			logger.error("尚未配置系统参数【goods_Price_Increase_Rate】.");
		}else {
			goodsPriceIncreaseRate = new BigDecimal(goodsPriceIncreaseRateStr.trim());
			logger.info("初始化参数:【"+TradeConstants.GOODS_PRICE_INCREASE_RATE+"】值:【"+goodsPriceIncreaseRateStr.trim()+"】");
		}
		
		//2.设置放量比
		String goodsVolumesIncreaseRateStr = sysConfigService.selectConfigByKey(TradeConstants.GOODS_VOLUMES_INCREASE_RATE) ;
		if(StringUtils.isBlank(goodsVolumesIncreaseRateStr) || StringUtils.isEmpty(goodsVolumesIncreaseRateStr)) {
			logger.error("尚未配置系统参数【goods_Volumes_Increase_Rate】.");
		}else {
			goodsVolumesIncreaseRate = new BigDecimal(goodsVolumesIncreaseRateStr.trim());
			logger.info("初始化参数:【"+TradeConstants.GOODS_VOLUMES_INCREASE_RATE+"】值:【"+goodsVolumesIncreaseRateStr.trim()+"】");
		}
		//3.风控比
		String moneyPoolLossRateStr = sysConfigService.selectConfigByKey(TradeConstants.MONEY_POOL_LOSS_RATE) ;
		if(StringUtils.isBlank(moneyPoolLossRateStr) || StringUtils.isEmpty(moneyPoolLossRateStr)) {
			logger.error("尚未配置系统参数【money_Pool_Loss_Rate】.");
		}else {
			moneyPoolLossRate = new BigDecimal(moneyPoolLossRateStr.trim());
			logger.info("初始化参数:【"+TradeConstants.MONEY_POOL_LOSS_RATE+"】值:【"+moneyPoolLossRateStr.trim()+"】");
		}
		//4.交易次数
		String moneyPoolTradeNumsStr = sysConfigService.selectConfigByKey(TradeConstants.MONEY_POOL_TRADE_NUMS) ;
		if(StringUtils.isBlank(moneyPoolTradeNumsStr) || StringUtils.isEmpty(moneyPoolTradeNumsStr)) {
			logger.error("尚未配置系统参数【money_Pool_Trade_Nums】.");
		}else {
			moneyPoolTradeNums = new BigDecimal(moneyPoolTradeNumsStr.trim());  
			logger.info("初始化参数:【"+TradeConstants.MONEY_POOL_TRADE_NUMS+"】值:【"+moneyPoolTradeNumsStr.trim()+"】");
		}
		//4.交易份额
		String goodsTradeMinAmountStr = sysConfigService.selectConfigByKey(TradeConstants.GOODS_TRADE_MIN_AMOUNT) ;
		if(StringUtils.isBlank(goodsTradeMinAmountStr) || StringUtils.isEmpty(goodsTradeMinAmountStr)) {
			logger.error("尚未配置系统参数【goods_trade_min_amount】.");
		}else {
			goodsTradeMinAmount = Integer.parseInt(goodsTradeMinAmountStr) ;
			logger.info("初始化参数:【"+TradeConstants.GOODS_TRADE_MIN_AMOUNT+"】值:【"+goodsTradeMinAmountStr.trim()+"】");
		}
	}
	
	@Override
	protected void monitorTask(SysUser user) {
		
		GoodsTradeModel goodsTradeModelParamter = new GoodsTradeModel();
		goodsTradeModelParamter.setCreater(user.getUserId().intValue());//查询用户
		goodsTradeModelParamter.setModelStatus(TradeConstants.GOODS_MODEL_STATUS_VALID);//启用状态
		goodsTradeModelParamter.setHasMailNotice(TradeConstants.MAIL_NOTICE_NO);
		
		List<GoodsTradeModel> listTradeModel = goodsTradeModelService.selectGoodsTradeModelList(goodsTradeModelParamter) ;
		
		logger.info("监控用户【"+user.getLoginName()+"】的交易计划模型有【"+listTradeModel.size()+"】个。");
		
		for(GoodsTradeModel planModel : listTradeModel) {
			//监控模型
			monitorGoodsTradeModel(planModel, user);
		}

	}
	
	/**
	 * 监控计划模型
	 * @param model
	 * @param user
	 */
	protected void monitorGoodsTradeModel(GoodsTradeModel planModel,SysUser user) {
		
		logger.info("开始扫描用户【"+user.getLoginName()+"】,模型【"+JSONObject.toJSONString(planModel)+"】....");
		
		ILoadGoodsInfo loadGoodsInfoInterface = loadGoodsInfoFactory.getLoadGoodsInfoInstance() ;
		
		//得到实时品种信息
		ActualTimeGoods actualTimeGoods  = loadGoodsInfoInterface.loadGoodsInfo(planModel.getCode()) ;
		if(actualTimeGoods == null ) {
			logger.error("查询的实时品种信息失败.");
			return ;
		}
		
		//过滤
		//1.当前价为0
		if(actualTimeGoods.getActualTimePrice() == 0 ){
			return ;
		}
		//2.如果当前价不在此买入 卖出区间内，则跳过建仓
		if(actualTimeGoods.getActualTimePrice()>planModel.getExitPrice().doubleValue()||actualTimeGoods.getActualTimePrice()<planModel.getStopPrice().doubleValue()){
			return;
		}
		//3.放量比过滤
		double planVol =  (planModel.getBasicVolumes().add( planModel.getBasicVolumes().multiply(goodsVolumesIncreaseRate))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//如果没有放量
		if(actualTimeGoods.getActualTimeVolumes()<planVol){
			return ;
		}
		//4.如果放量了 但是没有创新高
		if(actualTimeGoods.getActualTimePrice() < planModel.getBuildingPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()){
			
			return ;
		}
		//5.价格过滤
		double planPrice =  (planModel.getBuildingPrice().add( planModel.getBuildingPrice().multiply(goodsPriceIncreaseRate))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//如果没有放量
		if(actualTimeGoods.getActualTimePrice()<planPrice){
			return ;
		}
		
		//至此，说明可以建仓了
		//1.将计划模的状态设置成停用状态
		planModel.setModelStatus(TradeConstants.GOODS_MODEL_STATUS_INVALID);
		planModel.setHasMailNotice(TradeConstants.MAIL_NOTICE_YES);
		goodsTradeModelService.updateGoodsTradeModel(planModel) ;
		
		//2.根据计划模型生成建仓实例
		buildModelIns(actualTimeGoods, planModel, user);
		
		logger.info("完成扫描用户【"+user.getLoginName()+"】,模型【"+JSONObject.toJSONString(planModel)+"】.");
	}
	
	/**
	 * 根据模型建仓
	 * @param actualTimeGoods
	 * @param planModel
	 * @param user
	 */
	protected void buildModelIns(final ActualTimeGoods actualTimeGoods,final GoodsTradeModel planModel, final SysUser user) {
		
		//预期成本价 
		double buildPrice = planModel.getBuildingPrice().setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue() ;  
		//止损价
		double lossPrice = planModel.getStopPrice().setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue() ;
		//每次亏损金额
		BigDecimal perBuildLossMoney = null;
		//止损额度
		double  priceDifferences =  actualTimeGoods.getActualTimePrice() - lossPrice; 
		//建仓份额
		int mockAmount = 0;
		
		//总资金
		MoneyPool moneyPool =  moneyPoolService.selectMoneyPoolById(user.getUserId().intValue()) ;
		BigDecimal repMoney = moneyPool.getMoneyPool() ;
		
		//计算每次亏损金额
		perBuildLossMoney = repMoney.multiply(moneyPoolLossRate).divide(moneyPoolTradeNums) ;
				//(repMoney*sysLossRate)/num;
		//计算买入份额
		mockAmount = (int) (perBuildLossMoney.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue()/priceDifferences) ;
		
		//opt:2015-09-25 换算成MIN_AMOUNT的整数倍.
		mockAmount = (mockAmount/goodsTradeMinAmount)*goodsTradeMinAmount;
		if(mockAmount == 0){
			mockAmount = goodsTradeMinAmount;
		}
		
		
		//发送邮箱提醒
		Map<String,Object> data = new HashMap<String,Object>();
		
		data.put("品种名称", actualTimeGoods.getName()) ;
		data.put("建仓价格", buildPrice) ;
		
		String subject = "品种名称:".concat(actualTimeGoods.getName()).concat(",").concat("建仓价格:").concat(String.valueOf(buildPrice)) ;
		
		data.put("建仓份额", mockAmount) ;
		data.put("建仓金额", mockAmount*buildPrice) ;
		data.put("建仓时间", actualTimeGoods.getRefreshTime()) ;
		String content = JSONObject.toJSONString(data) ;
		
		logger.info("正在向用户【"+user.getLoginName()+"("+user.getEmail()+")】发送建仓邮件.主题【"+subject+"】......");
		
		SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(mailConfig.getUserName());//发件人
        message.setTo(user.getEmail());//收件人
        message.setSubject( subject );//主题
        message.setText(jsonBeautifyUtils.formatJson(content));//正文
        mailSender.send(message);
        logger.info("已成功发送用户【"+user.getLoginName()+"("+user.getEmail()+")】建仓邮件.主题【"+subject+"】.");
		
	}

}
