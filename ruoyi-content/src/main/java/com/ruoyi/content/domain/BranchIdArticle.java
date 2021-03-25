package com.ruoyi.content.domain;

import java.io.Serializable;

public class BranchIdArticle implements Serializable{

	private static final long serialVersionUID = -4566641360099978631L;

	private String articleId;		
	private String articleName;		
	private String createDate;		
	private String updateDate;
	private String libraryState;
	private String publishId;
	private String name;
	private String articleVersion;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getLibraryState() {
		return libraryState;
	}
	public void setLibraryState(String libraryState) {
		this.libraryState = libraryState;
	}
	public String getPublishId() {
		return publishId;
	}
	public void setPublishId(String publishId) {
		this.publishId = publishId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArticleVersion() {
		return this.articleVersion;
	}
	public void setArticleVersion(String articleVersion) {
		this.articleVersion = articleVersion;
	}
}
