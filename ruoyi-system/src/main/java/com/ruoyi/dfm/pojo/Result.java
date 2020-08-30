package com.ruoyi.dfm.pojo;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = -8219578427197649446L;
	private boolean success;
	private String message;
	private Object data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
