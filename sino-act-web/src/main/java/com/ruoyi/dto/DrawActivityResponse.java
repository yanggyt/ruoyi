package com.ruoyi.dto;

import java.io.Serializable;

public class DrawActivityResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2898381019555914194L;
	private String drawCode;
	private String userId;
	private String userType;
	private String gateWayTime;
	private String result;
	private String prizeCode;
	private String prizeName;
	private String prizeType;
	private String displayOrder;
	private String prizeLevel;
	private String gatewayFolw;
	private String available;
	private String extId;
	private String source;
	
	public String getGatewayFolw() {
		return gatewayFolw;
	}
	public void setGatewayFolw(String gatewayFolw) {
		this.gatewayFolw = gatewayFolw;
	}
	public String getPrizeLevel() {
		return prizeLevel;
	}
	public void setPrizeLevel(String prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	private String cue;
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
	public String getGateWayTime() {
		return gateWayTime;
	}
	public void setGateWayTime(String gateWayTime) {
		this.gateWayTime = gateWayTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPrizeCode() {
		return prizeCode;
	}
	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public String getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getCue() {
		return cue;
	}
	public void setCue(String cue) {
		this.cue = cue;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getExtId() {
		return extId;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
