package com.ruoyi.content.domain;

import java.io.Serializable;

public class ArticlePublishSendDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//推送id
	private Integer id;
	//推送对象类型
	private String sendType;
	//推送的员工工号或分公司编码
	private String groupId;
	//操作人姓名
	private String operateName;
	//操作时间
	private String operateTime;
	//推送时间
	private String publishTime;
	//推送状态
	private String sendState;
	
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}
