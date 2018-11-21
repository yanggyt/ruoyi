package com.cronie.mengyu.common.goods.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.cronie.mengyu.common.goods.ActualTimeGoods;
import com.cronie.mengyu.common.goods.ILoadGoodsInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ SinaLoadGoodsInfoImpl.class})
@WebAppConfiguration
public class SinaLoadGoodsInfoImplTest {

	private static final Logger logger = LoggerFactory.getLogger(SinaLoadGoodsInfoImplTest.class);

	@Autowired
	@Qualifier("sinaDataSource")
	ILoadGoodsInfo loadGoodsInfo ;
	
	@Test
	public  void loadGoodsInfo() {
		ActualTimeGoods goodsInfo  = loadGoodsInfo.loadGoodsInfo("000001") ;
		logger.info(JSONObject.toJSONString(goodsInfo));
		assertEquals("上证指数", goodsInfo.getName());
	}
}
