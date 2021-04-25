package com.ruoyi.content.domain;



public class ArticeClickInfoExDTO {
	// 浏览者信息
	private String clickUserHeadimgurl; // 用户头像url
	private String clickUserNickname;// 用户昵称
	private String watchTime; // 观察时间
	private String createDate; 	// 浏览时间yyyy-mm-dd
	private String createTime;	// 浏览时间 HH-mm
	private String shareState; // 分享状态 0-未分享、1-已分享
	private String fromShareState;// 查看文章的人是否已分享,0-未分享,1-对话框分享(包含个人和群组),2-朋友圈分享,5-对话框和朋友圈都分享了'
	private String articleId; //文章id
	private String articleName;//文章名称
	private String pushDate;//发布时间
	private String articleAutchor;
	private String clickUserInfo;
	private String clickId;
	public ArticeClickInfoExDTO() {
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

	public String getFromShareState() {
		return fromShareState;
	}

	public void setFromShareState(String fromShareState) {
		this.fromShareState = fromShareState;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public String getArticleAutchor() {
		return articleAutchor;
	}

	public void setArticleAutchor(String articleAutchor) {
		this.articleAutchor = articleAutchor;
	}

	public String getClickUserInfo() {
		return clickUserInfo;
	}

	public void setClickUserInfo(String clickUserInfo) {
		this.clickUserInfo = clickUserInfo;
	}

	public String getClickId() {
		return clickId;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

}
