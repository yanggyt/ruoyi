package com.ruoyi.vip.framework;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义带用户类型token
 * @author Sunny
 */
public class UsernamePasswordByUserTypeToken extends UsernamePasswordToken {
	private static final long serialVersionUID = -7638434498222500528L;
 
	/*
	 * 用户类型
	 * 1:积分后台用户(后台管理员)
	 * 2:积分兑换端用户(店长店员)
	 * 3:积分验证端用户(第三方合作店铺)
	 */
	private String userType;
 
	public String getUserType() {
		return userType;
	}
 
	public void setUserType(String userType) {
		this.userType = userType;
	}
 
	public UsernamePasswordByUserTypeToken(String username, String password, String userType) {
		super(username, password);
		this.userType = userType;
	}
}