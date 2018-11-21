package com.cronie.mengyu.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;

import com.cronie.mengyu.common.json.JSONBeautifyUtils;
import com.cronie.mengyu.config.MailConfig;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;

public abstract class AbstractMonitor implements IMonitor {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMonitor.class);
	
	@Autowired(required=true)
    private ISysUserService userService;
	
	//邮箱服务器
	@Autowired
	protected MailSender mailSender;
	
	//邮箱配置
	@Autowired
	protected MailConfig mailConfig ;
	
	//json美化器
	@Autowired
	protected JSONBeautifyUtils jsonBeautifyUtils ;
	
	
	@Override
	public void monitor(String loginName) {

		SysUser user = userService.selectUserByLoginName(loginName);
		if(user == null) {
			logger.error("无用户【"+loginName+"】的信息,请检查.");
			return ;
		}
		
		monitorTask(user);
	}
	
	protected abstract void  monitorTask(SysUser user);
	
	

}
