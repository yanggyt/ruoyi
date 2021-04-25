package com.ruoyi.content.domain;

/**
 * OSS参数对象
 * 
 * @author zhanghe
 *
 */
public class OssDTO {

	private String ossId; // 阿里云OSS提供的ossId
	private String ossKey; // 阿里云OSS提供的ossKey
	private String bucketName; // 阿里云OSS提供的ossBucket
	private String ossPath; // 阿里云OSS上保存文件的路径
	private String ossEndPoint; // 阿里云OSS提供的ossEndPoint
	private String ossEndPointOut; // 阿里云OSS提供的ossEndPoint

	public String getOssId() {
		return ossId;
	}

	public void setOssId(String ossId) {
		this.ossId = ossId;
	}

	public String getOssKey() {
		return ossKey;
	}

	public void setOssKey(String ossKey) {
		this.ossKey = ossKey;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getOssPath() {
		return ossPath;
	}

	public void setOssPath(String ossPath) {
		this.ossPath = ossPath;
	}

	public String getOssEndPoint() {
		return ossEndPoint;
	}

	public void setOssEndPoint(String ossEndPoint) {
		this.ossEndPoint = ossEndPoint;
	}

	public String getOssEndPointOut() {
		return ossEndPointOut;
	}

	public void setOssEndPointOut(String ossEndPointOut) {
		this.ossEndPointOut = ossEndPointOut;
	}

}