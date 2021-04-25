package com.ruoyi.content.domain;



public class ArticeClickInfo {
	// 浏览者信息
	private String clickUserHeadimgurl; // 用户头像url
	private String clickUserNickname;// 用户昵称
	private String watchTime; // 观察时间
	private String createDate; 	// 浏览时间yyyy-mm-dd
	private String createTime;	// 浏览时间 HH-mm
	private String shareState; // 分享状态 0-未分享、1-已分享

	public ArticeClickInfo() {
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getClickUserHeadimgurl() {
		return clickUserHeadimgurl;
	}

	public void setClickUserHeadimgurl(String clickUserHeadimgurl) {
		this.clickUserHeadimgurl = clickUserHeadimgurl;
	}

	public String getClickUserNickname() {
//		if (StringUtils.isNotBlank(clickUserNickname)) {
//			return Base64.getDecoder().decode(clickUserNickname).toString();
//		}
		return clickUserNickname;
	}

	public void setClickUserNickname(String clickUserNickname) {
		this.clickUserNickname = clickUserNickname;
	}

	public String getWatchTime() {
		return watchTime;
	}

	public void setWatchTime(String watchTime) {
		this.watchTime = watchTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getShareState() {
		return shareState;
	}

	public void setShareState(String shareState) {
		this.shareState = shareState;
	}

}
