package com.ruoyi.content.domain;

public class AuthorityExDto {

	private String id;
	private String level;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String  operatorRoleName;
	private String  operatorRolePath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOperatorRoleName() {
		return operatorRoleName;
	}
	public void setOperatorRoleName(String operatorRoleName) {
		this.operatorRoleName = operatorRoleName;
	}
	public String getOperatorRolePath() {
		return operatorRolePath;
	}
	public void setOperatorRolePath(String operatorRolePath) {
		this.operatorRolePath = operatorRolePath;
	}
}
