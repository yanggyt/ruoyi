package com.ruoyi.content.domain;
/**
 * 文章与标签关系dto
 * @author Administrator
 *
 */
public class ArticleLabelRelDTO {
	private int articleId; // 文章id
	private int labelId;   //标签id
	private String labelName; //标签名称
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

}
