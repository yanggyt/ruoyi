package com.ruoyi.content.domain;

import java.util.Date;
import java.util.List;

public class BaseCodeTree {

    private Integer id;
    private String codeCode;
    private String codeType;
    private String orderNo;
    private String codeCname;
    private String codeEname;
    private String codeTname;
    private String state;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private String updateRemark;
    private String businessArea;
    private String companyId;
    private String branchId;
    private List<BaseCodeTree> child;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodeCode() {
		return codeCode;
	}
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCodeCname() {
		return codeCname;
	}
	public void setCodeCname(String codeCname) {
		this.codeCname = codeCname;
	}
	public String getCodeEname() {
		return codeEname;
	}
	public void setCodeEname(String codeEname) {
		this.codeEname = codeEname;
	}
	public String getCodeTname() {
		return codeTname;
	}
	public void setCodeTname(String codeTname) {
		this.codeTname = codeTname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateRemark() {
		return updateRemark;
	}
	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public List<BaseCodeTree> getChild() {
		return child;
	}
	public void setChild(List<BaseCodeTree> child) {
		this.child = child;
	}
    
    
    
}
