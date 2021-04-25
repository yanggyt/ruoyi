package com.ruoyi.content.domain;

public class CompanyArticleInfo  {

	private String publishId;		//文章发布Id(版本号)
	private String articleId;		//文章id
	private String articleName;		//文章名称
	private String listPicUrl;		//文章列表显示图片
	private String createDate;		//文章发布日期
	private String visitorCount;	//文章浏览人数
	private String sharedCount;		//文章分享人数
	private String publishViewUrl;	//文章发布浏览静态页面路径
	
	
	
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
	public String getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(String visitorCount) {
		this.visitorCount = visitorCount;
	}
	public String getSharedCount() {
		return sharedCount;
	}
	public void setSharedCount(String sharedCount) {
		this.sharedCount = sharedCount;
	}
	public String getPublishViewUrl() {
		return publishViewUrl;
	}
	public void setPublishViewUrl(String publishViewUrl) {
		this.publishViewUrl = publishViewUrl;
	}

	
	 
}