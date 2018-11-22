package com.cronie.mengyu.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:mail/mail.properties")
public class MailConfig {

	private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);
	
	@Value("${sender.mail.host}")
	String host ;
	@Value("${sender.mail.port}")
	String port;
	@Value("${sender.mail.userName}")
	String senderUserName;
	@Value("${sender.mail.passWord}")
	String senderPassWord;
	@Value("${sender.mail.smtp.auth}")
	String auth ;
	@Value("${sender.mail.smtp.timeout}")
	String timeOut;
	/**
     * 配置邮件发送器
     * @return
     */
    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(Integer.parseInt(port));//默认端口，标准的SMTP端口
        mailSender.setUsername(senderUserName);//用户名
        mailSender.setPassword(senderPassWord);//密码
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", auth);
        javaMailProperties.put("mail.smtp.timeout", timeOut);
        mailSender.setJavaMailProperties(javaMailProperties);
        logger.info("初始化163邮箱发送器完成.");
        return mailSender;
    }
	public String getUserName() {
		return senderUserName;
	}

}
