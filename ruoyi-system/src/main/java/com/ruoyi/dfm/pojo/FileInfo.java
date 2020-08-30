package com.ruoyi.dfm.pojo;

import java.io.Serializable;

public class FileInfo implements Serializable {

	private static final long serialVersionUID = 1363196522567826008L;
	private int id;
	private String fileName;
	private String relaPath;
	private String extendName;
	private long fileSize;
	private int uploadUser;
	private String uploadTime;
	private String fieldName;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRelaPath() {
		return relaPath;
	}
	public void setRelaPath(String relaPath) {
		this.relaPath = relaPath;
	}
	public String getExtendName() {
		return extendName;
	}
	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(int uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	
}
