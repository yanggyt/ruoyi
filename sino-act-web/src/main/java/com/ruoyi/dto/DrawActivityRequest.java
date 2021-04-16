package com.ruoyi.dto;

import java.io.Serializable;

public class DrawActivityRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -426397448496644788L;
	
	private String serialNo;

	private String drawCode;

	private String userId;
	
	private String userType;
	
	private String drawTime;
	
	private String merchantCode;
	
	private String merchantSysCode;
	
	private String channel;
	
	private String userName;
	
	private String businessArea;
	
	private String source;
	
	private String phone;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDrawCode() {
		return drawCode;
	}

	public void setDrawCode(String drawCode) {
		this.drawCode = drawCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(String drawTime) {
		this.drawTime = drawTime;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantSysCode() {
		return merchantSysCode;
	}

	public void setMerchantSysCode(String merchantSysCode) {
		this.merchantSysCode = merchantSysCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	
	

}
