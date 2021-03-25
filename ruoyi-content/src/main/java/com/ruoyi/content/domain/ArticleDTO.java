package com.ruoyi.content.domain;

import java.io.Serializable;

public class ArticleDTO implements Serializable{

	private static final long serialVersionUID = -4566641360099978631L;

	private String publishId;		//文章发布id
	private String articleId;		//文章id
	private String articleName;		//文章名称
	private String listPicUrl;		//文章列表显示图片
	private String createDate;		//文章发布日期
	private String visitors;		//文章浏览人数
	
	public String getPublishId() {
		return publishId;
	}
	public void setPublishId(String publishId) {
		this.publishId = publishId;
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
	public String getListPicUrl() {
		return listPicUrl;
	}
	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getVisitors() {
		return visitors;
	}
	public void setVisitors(String visitors) {
		this.visitors = visitors;
	}
	
	
	
}
