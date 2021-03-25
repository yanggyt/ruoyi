package com.ruoyi.content.domain;

import java.io.Serializable;

/**
 * 文章转发，用户记录
*
* @author  
* @date 2018年4月16日  
*
 */
public class YwyForwardUserInfo implements Serializable{

	private static final long serialVersionUID = 3152100900712336169L;
	
	private String userId;
	private String articleId;
	private String headImgUrl;
	private String nickName;
	private String createDate;
	private String createUser;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	
}
