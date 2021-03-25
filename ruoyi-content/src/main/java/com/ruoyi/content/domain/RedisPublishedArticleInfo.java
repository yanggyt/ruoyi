package com.ruoyi.content.domain;

public class RedisPublishedArticleInfo  {

		private String publishId;		//文章发布id
		private String articleId;		//文章id
		private String articleName;		//文章名称
		private String listPicUrl;		//文章列表显示图片
		private String createDate;		//文章发布日期
		private String visitorCount;	//文章浏览人数
		private String sharedCount;		//文章分享人数
		private String publishViewUrl;	//文章发布浏览静态页面路径
		private String publishEditUrl;	//文章发布编辑静态页面路径
		private String adId;			//广告id
		private String cardId;			//名片id

		public String getCardId() {
			return cardId;
		}

		public void setCardId(String cardId) {
			this.cardId = cardId;
		}

		public String getAdId() {
			return adId;
		}

		public void setAdId(String adId) {
			this.adId = adId;
		}

		@Override
		public String toString() {
			return "PublishedArticleInfo [publishId=" + publishId + ", articleId=" + articleId + ", articleName="
					+ articleName + ", listPicUrl=" + listPicUrl + ", createDate=" + createDate + ", visitorCount="
					+ visitorCount + ", sharedCount=" + sharedCount + ", publishViewUrl=" + publishViewUrl
					+ ", publishEditUrl=" + publishEditUrl + ", adId=" + adId + ", cardId=" + cardId + "]";
		}
		
		public String getPublishViewUrl() {
			return publishViewUrl;
		}

		public void setPublishViewUrl(String publishViewUrl) {
			this.publishViewUrl = publishViewUrl;
		}

		public String getPublishEditUrl() {
			return publishEditUrl;
		}

		public void setPublishEditUrl(String publishEditUrl) {
			this.publishEditUrl = publishEditUrl;
		}

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
		 
	}
