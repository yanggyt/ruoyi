package com.ruoyi.content.domain;

import java.io.Serializable;

/**
 * 文章转发，用户记录
*
* @author  
* @date 2018年4月16日  
*
 */
public class YwyPublishInfo implements Serializable{

	private static final long serialVersionUID = 3152100900712336169L;
	
	private String clickId;
	private String openId;
	private String watchTime;
	private String shareState;
	private String clickDate;
	private String clickTime;
	private String clientNickName;
	private String clientHeadImgurl;
	
	public String getClickId() {
		return clickId;
	}
	public void setClickId(String clickId) {
		this.clickId = clickId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getWatchTime() {
		return watchTime;
	}
	public void setWatchTime(String watchTime) {
		this.watchTime = watchTime;
	}
	public String getShareState() {
		return shareState;
	}
	public void setShareState(String shareState) {
		this.shareState = shareState;
	}
	public String getClickDate() {
		return clickDate;
	}
	public void setClickDate(String clickDate) {
		this.clickDate = clickDate;
	}
	public String getClickTime() {
		return clickTime;
	}
	public void setClickTime(String clickTime) {
		this.clickTime = clickTime;
	}
	public String getClientNickName() {
		return clientNickName;
	}
	public void setClientNickName(String clientNickName) {
		this.clientNickName = clientNickName;
	}
	public String getClientHeadImgurl() {
		return clientHeadImgurl;
	}
	public void setClientHeadImgurl(String clientHeadImgurl) {
		this.clientHeadImgurl = clientHeadImgurl;
	}	
	
}
