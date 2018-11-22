package com.cronie.mengyu.monitor.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cronie.mengyu.common.constants.TradeConstants;
import com.cronie.mengyu.domain.GoodsTradeModel;
import com.cronie.mengyu.monitor.AbstractMonitor;
import com.cronie.mengyu.service.IGoodsTradeModelService;
import com.ruoyi.system.domain.SysUser;

/**
 * 重置所有的交易模型邮件通知状态
 * @author cronie
 *
 */
@Component("resetTradeModelMailNotice")
public class ResetTradeModelMailNoticeImpl extends AbstractMonitor {

	private static final Logger logger = LoggerFactory.getLogger(ResetTradeModelMailNoticeImpl.class);
	//模型服务层
	@Autowired
	IGoodsTradeModelService goodsTradeModelService ;
		
	@Override
	protected void monitorTask(SysUser user) {

		GoodsTradeModel goodsTradeModelParamter = new GoodsTradeModel();
		goodsTradeModelParamter.setCreater(user.getUserId().intValue());//查询用户
		goodsTradeModelParamter.setModelStatus(TradeConstants.GOODS_MODEL_STATUS_VALID);//启用状态
		goodsTradeModelParamter.setHasMailNotice(TradeConstants.MAIL_NOTICE_YES);
		
		List<GoodsTradeModel> listTradeModel = goodsTradeModelService.selectGoodsTradeModelList(goodsTradeModelParamter) ;
		logger.info("重置用户【"+user.getLoginName()+"】的交易计划模型邮件通知有【"+listTradeModel.size()+"】个。");
		for(GoodsTradeModel planModel : listTradeModel) {
			planModel.setHasMailNotice(TradeConstants.MAIL_NOTICE_NO);//未通知状态
			goodsTradeModelService.updateGoodsTradeModel(planModel) ;
			
		}
		logger.info("已重置完用户【"+user.getLoginName()+"】的交易计划模型邮件通知状态。");
	}

}
