/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.domain;

import javax.validation.constraints.Size;

import cn.com.infosouth.common.core.domain.BaseEntity;

/**
 * 导入csv时不同机型对应的header参数Entity
 * @author cyh
 * @version 2018-03-06
 */
public class InfoHeaderParams extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String id;	//id
	private String versionId;		// 版本
	private String headerParams;		// 参数
	private String refParams;    //csv文件第一行参数
	private String remarks;		//备注
	
	public InfoHeaderParams() {
		super();
	}

	@Size(min=1, max=11, message="版本长度必须介于 1 和 11 之间")
	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	
	public String getHeaderParams() {
		return headerParams;
	}

	public void setHeaderParams(String headerParams) {
		this.headerParams = headerParams;
	}

	public String getRefParams() {
		return this.refParams;
	}

	public void setRefParams(String refParams) {
		this.refParams = refParams;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remark) {
		this.remarks = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	 
	
}