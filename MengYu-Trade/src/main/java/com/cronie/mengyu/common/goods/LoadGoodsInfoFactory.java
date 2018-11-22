package com.cronie.mengyu.common.goods;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LoadGoodsInfoFactory implements ApplicationContextAware{
	
	ApplicationContext ctx = null;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ctx = arg0 ;
	}
	
	public ILoadGoodsInfo getLoadGoodsInfoInstance() {
		
		ILoadGoodsInfo loadGoodsInfo = null;
		
		Long time = System.currentTimeMillis();
		if(time%2==0){
			loadGoodsInfo = ctx.getBean("sinaDataSource", ILoadGoodsInfo.class) ;
		}else {
			loadGoodsInfo = ctx.getBean("tencentDataSource", ILoadGoodsInfo.class) ;
		}
		return loadGoodsInfo ;
		
	}
	
	
	
	
}
