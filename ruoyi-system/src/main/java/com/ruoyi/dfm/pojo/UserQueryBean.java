package com.ruoyi.dfm.pojo;

import java.io.Serializable;

public class UserQueryBean implements Serializable {

	private static final long serialVersionUID = -2612773710283491830L;
	private String name;
    private String username;
    private String department;
    private String projectGroup;
    private String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProjectGroup() {
		return projectGroup;
	}
	public void setProjectGroup(String projectGroup) {
		this.projectGroup = projectGroup;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
    
}	
