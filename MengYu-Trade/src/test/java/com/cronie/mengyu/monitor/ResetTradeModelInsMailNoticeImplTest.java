package com.cronie.mengyu.monitor;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@EnableAutoConfiguration
//@ContextConfiguration(classes={ ResetTradeModelInsMailNoticeImpl.class})
//@WebAppConfiguration
//@ComponentScan({"com.ruoyi.*","com.cronie.*"})
//@SpringApplicationConfiguration
public class ResetTradeModelInsMailNoticeImplTest {

	private static final Logger logger = LoggerFactory.getLogger(ResetTradeModelInsMailNoticeImplTest.class);
	
	@Autowired
	@Qualifier("resetTradeModelInsMailNotice")
	IMonitor monitor;
	
	@Test
    public void monitor(){
		monitor.monitor("admin");
    }
}
