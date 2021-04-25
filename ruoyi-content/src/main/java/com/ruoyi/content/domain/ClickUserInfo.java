package com.ruoyi.content.domain;

import java.io.Serializable;

/**
 * 文章查看记录信息数据
*
* @author  
* @date 2018年4月16日  
*
 */
public class ClickUserInfo implements Serializable{

	private static final long serialVersionUID = 3152100900712336169L;
	
	private String openId;
	private String headImgUrl;
	private String nickName;
	private String toShareState;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getToShareState() {
		return toShareState;
	}
	public void setToShareState(String toShareState) {
		this.toShareState = toShareState;
	}	
	
	
}
