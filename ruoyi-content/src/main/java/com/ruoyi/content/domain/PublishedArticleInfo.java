package com.ruoyi.content.domain;

public class PublishedArticleInfo  {

	private String articleId;		//文章id
	private String articleName;		//文章名称
	private String pushDate;		//文章发布日期
	private String articleAuthor;	//文章作者
	private String special;		//一级栏目	
	private String channelId;	//二级栏目
	private String articleState;	//文章状态
	private String originalUrl;	//原文链接
	private String shareTitle;	//分享标题
	private String shareDes;	//分享描述	
	private String createUser;	//创建者
	private String updateDate;	//更新日期
	private String updateTime;	//更新时间
	private String visitorCount;	//浏览量
	private String modifiedEditUrl;	//预览路径
	private String modifiedViewUrl;	//编辑路径
	private String shareCount;		//分享量
	
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
	public String getArticleAuthor() {
		return articleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getArticleState() {
		return articleState;
	}
	public void setArticleState(String articleState) {
		this.articleState = articleState;
	}
	
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareDes() {
		return shareDes;
	}
	public void setShareDes(String shareDes) {
		this.shareDes = shareDes;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(String visitorCount) {
		this.visitorCount = visitorCount;
	}
	public String getShareCount() {
		return shareCount;
	}
	public void setShareCount(String shareCount) {
		this.shareCount = shareCount;
	}
	public String getModifiedEditUrl() {
		return modifiedEditUrl;
	}
	public void setModifiedEditUrl(String modifiedEditUrl) {
		this.modifiedEditUrl = modifiedEditUrl;
	}
	public String getModifiedViewUrl() {
		return modifiedViewUrl;
	}
	public void setModifiedViewUrl(String modifiedViewUrl) {
		this.modifiedViewUrl = modifiedViewUrl;
	}
	

	
}
