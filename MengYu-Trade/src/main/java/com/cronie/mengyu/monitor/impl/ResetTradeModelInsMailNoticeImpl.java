package com.cronie.mengyu.monitor.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.domain.GoodsTradeModelIns;
import com.cronie.mengyu.monitor.AbstractMonitor;
import com.cronie.mengyu.service.IGoodsTradeModelInsService;
import com.ruoyi.system.domain.SysUser;

/**
 * 重置所有的交易模型实例邮件通知状态
 * @author cronie
 *
 */
@Component("resetTradeModelInsMailNotice")
public class ResetTradeModelInsMailNoticeImpl extends AbstractMonitor {
	
	private static final Logger logger = LoggerFactory.getLogger(ResetTradeModelInsMailNoticeImpl.class);

	//模型实例服务层
	@Autowired
	IGoodsTradeModelInsService goodsTradeModelInsService ;
	@Override
	protected void monitorTask(SysUser user) {
		
		GoodsTradeModelIns goodsTradeModelInsParamter = new GoodsTradeModelIns();
		goodsTradeModelInsParamter.setCreater(user.getUserId().intValue());//查询用户
		goodsTradeModelInsParamter.setInsStatus(TradeConstants.MODEL_INS_STATUS_RUNING);//计划是进行中的状态
		goodsTradeModelInsParamter.setHasMailNotice(TradeConstants.MAIL_NOTICE_YES);
		
		
		List<GoodsTradeModelIns> listTradeModelIns = goodsTradeModelInsService.selectGoodsTradeModelInsList(goodsTradeModelInsParamter) ;
		
		logger.info("重置用户【"+user.getLoginName()+"】的交易计划模型邮件通知有【"+listTradeModelIns.size()+"】个。");
		
		for(GoodsTradeModelIns planModelIns : listTradeModelIns) {
			planModelIns.setHasMailNotice(TradeConstants.MAIL_NOTICE_NO);//未通知状态
			goodsTradeModelInsService.updateGoodsTradeModelIns(planModelIns) ;
		}
		logger.info("已重置完用户【"+user.getLoginName()+"】的交易计划模型实例邮件通知状态。");
	}
	

	
	
}
