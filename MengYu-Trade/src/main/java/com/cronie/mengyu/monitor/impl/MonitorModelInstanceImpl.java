package com.cronie.mengyu.monitor.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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
import com.cronie.mengyu.domain.GoodsTradeModelIns;
import com.cronie.mengyu.monitor.AbstractMonitor;
import com.cronie.mengyu.service.IGoodsTradeModelInsService;
import com.cronie.mengyu.service.IGoodsTradeModelService;
import com.cronie.mengyu.service.IMoneyPoolService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 
 * @author 监控模型实例
 *
 */
@Component("monitorModelInstance")
public class MonitorModelInstanceImpl extends AbstractMonitor {
	
	private static final Logger logger = LoggerFactory.getLogger(MonitorModelInstanceImpl.class);
	
	//模型服务层
	@Autowired
	IGoodsTradeModelService goodstradeModelService ;
	
	//模型实例服务层
	@Autowired
	IGoodsTradeModelInsService goodsTradeModelInsService ;
	
	//系统参数服务层
	@Autowired
	ISysConfigService sysConfigService ;
	
	//实时数据源工厂
	@Autowired
	LoadGoodsInfoFactory loadGoodsInfoFactory ;
	
	//资金池服务
	@Autowired
	IMoneyPoolService moneyPoolService ;
	
	
	/**
	 * 系统参数配置
	 */
	@PostConstruct
	protected void initSysConfig() {
		
	}
	
	@Override
	protected void monitorTask(SysUser user) {
		
		GoodsTradeModelIns goodsTradeModelInsParamter = new GoodsTradeModelIns();
		goodsTradeModelInsParamter.setCreater(user.getUserId().intValue());//查询用户
		goodsTradeModelInsParamter.setInsStatus(TradeConstants.MODEL_INS_STATUS_RUNING);//计划是进行中的状态
		goodsTradeModelInsParamter.setHasMailNotice(TradeConstants.MAIL_NOTICE_NO);
		
		
		List<GoodsTradeModelIns> listTradeModelIns = goodsTradeModelInsService.selectGoodsTradeModelInsList(goodsTradeModelInsParamter) ;
		
		logger.info("监控到用户【"+user.getLoginName()+"】的交易模型实例有【"+listTradeModelIns.size()+"】个。");
		
		for(GoodsTradeModelIns planModelIns : listTradeModelIns) {
			//监控模型
			monitorGoodsTradeModelIns(planModelIns, user);
		}

	}
	
	/**
	 * 监控计划模型
	 * @param model
	 * @param user
	 */
	protected void monitorGoodsTradeModelIns(GoodsTradeModelIns planModelIns,SysUser user) {
		
		logger.info("开始扫描用户【"+user.getLoginName()+"】,模型【"+JSONObject.toJSONString(planModelIns)+"】....");
		
		ILoadGoodsInfo loadGoodsInfoInterface = loadGoodsInfoFactory.getLoadGoodsInfoInstance() ;
		
		//模型信息
		GoodsTradeModel planModel = goodstradeModelService.selectGoodsTradeModelById(planModelIns.getModelId()) ;
		
		
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
		//2.如果当前价在此买入 卖出区间内，则跳过验证
		if(actualTimeGoods.getActualTimePrice()<planModel.getExitPrice().doubleValue() && actualTimeGoods.getActualTimePrice()>planModel.getStopPrice().doubleValue()){
			return;
		}
		
		//至此，说明可以平仓了
		
		//1.设置今日不提醒邮件
		planModelIns.setHasMailNotice(TradeConstants.MAIL_NOTICE_YES);
		goodsTradeModelInsService.updateGoodsTradeModelIns(planModelIns) ;
		
		//2.根据计划实例生成平仓实例
		completeModelIns(actualTimeGoods, planModel,planModelIns, user);
		
		
		logger.info("完成扫描用户【"+user.getLoginName()+"】,模型【"+JSONObject.toJSONString(planModel)+"】.");
	}
	
	/**
	 * 根据模型建仓
	 * @param actualTimeGoods
	 * @param planModel
	 * @param user
	 */
	protected void completeModelIns(final ActualTimeGoods actualTimeGoods,final GoodsTradeModel planModel,GoodsTradeModelIns planModelIns, final SysUser user) {
		
		//止损价
		double lossPrice = planModel.getStopPrice().setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue() ;
		//平仓价
		double exitPrice = planModel.getExitPrice().setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue() ;
		
		//发送邮箱提醒
		Map<String,Object> data = new HashMap<String,Object>();
		
		data.put("品种名称", actualTimeGoods.getName()) ;
		data.put("实际平仓价格", actualTimeGoods.getActualTimePrice()) ;
		
		String subject = "品种名称:".concat(actualTimeGoods.getName()).concat(",").concat("平仓价格:").concat(String.valueOf(actualTimeGoods.getActualTimePrice())) ;
		
		data.put("计划平仓价格", exitPrice) ;
		data.put("止损价格", lossPrice) ;
		data.put("平仓时间", actualTimeGoods.getRefreshTime()) ;
		String content = JSONObject.toJSONString(data) ;
		
		logger.info("正在向用户【"+user.getLoginName()+"("+user.getEmail()+")】发送平仓邮件.主题【"+subject+"】......");
		SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(mailConfig.getUserName());//发件人
        message.setTo(user.getEmail());//收件人
        message.setSubject( subject );//主题
        message.setText(jsonBeautifyUtils.formatJson(content));//正文
        mailSender.send(message);
        logger.info("已成功发送用户【"+user.getLoginName()+"("+user.getEmail()+")】平仓邮件.主题【"+subject+"】.");
		
	}

}
