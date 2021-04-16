package com.ruoyi.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * @ClassName: SequenceService 
 * @Description: 生成序列号
 * @author liuyaoyao
 * @date 2018年2月8日 下午4:24:32 
 *
 */
public class SequenceService {
	
	private static Logger logger = LoggerFactory.getLogger(SequenceService.class);
	
	public synchronized String getPrizeSequence(){
		try{
			String sql = "select lpad(sequenec_prize.nextval,8,0) from dual";
			return null;
		}catch(Exception e){
			logger.error("通过数据库序列获取帐户ID出错，错误原因：", e);
			return null;
		}
	}
	
	public String getDrawActivirtSeq(){
		try{
			String sql = "select lpad(gt_draw_activity_seq.nextval,7,0) from dual";
			return null;
		}catch(Exception e){
			logger.error("通过数据库序列获取帐户ID出错，错误原因：", e);
			return null;
		}
	}
	public String getDrawActivirtTradeOrderSeq(){
		try{
			String sql = "select lpad(gt_draw_activity_tradeorder.nextval,10,0) from dual";
			return null;
		}catch(Exception e){
			logger.error("通过数据库序列获取帐户ID出错，错误原因：", e);
			return null;
		}
	}
	
	public String getGtReqFlagSeq(){
		try{
			String sql = "select lpad(gt_req_flag_seq.nextval,10,0) from dual";
			return null;
		}catch(Exception e){
			logger.error("通过数据库序列获取帐户ID出错，错误原因：", e);
			return null;
		}
	}
	
}
