package com.cronie.mengyu.config.mail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cronie.mengyu.config.MailConfig;
import com.cronie.mengyu.monitor.AbstractMonitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ MailConfig.class})
@WebAppConfiguration
public class MailConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(AbstractMonitor.class);
	
	@Autowired
	MailSender mailSender;
	
	@Test
    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("jonw000@163.com");//发件人
        message.setTo("413852682@qq.com");//收件人
        message.setSubject("Spring Email Test");//主题
        message.setText("hello world!!");//正文
        mailSender.send(message);
        logger.info("邮件发送完成.");
    }
}
