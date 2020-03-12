/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.com.infosouth.common.core.domain.BaseEntity;

/**
 * 译码工程值查看-多个csv图形展示Entity
 * @author zy
 * @version 2018-03-26
 */
public class ShowMultiCsv extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private Date flDate;		// 航班日期
	private String flNo;		// 航班号
	private String arn;		// 飞机号
	private String acType;		// 机型
	private String pod;		// 起飞机场
	private String poa;		// 到达机场
	private String airline;		// 所属公司
	private String csvPath;		// csv存储路径
	private String csvName;		// csv文件名
	private String infoResourceId;		// 资源目录ID（info_resource.id）
	private Date updateDate;  //更新日期
	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	private String versionId;		//版本id
	
	private List<String> idsList; //
	private List<DataAuthCondition> conditionsList;
	
	public ShowMultiCsv() {
		super();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="航班日期不能为空")
	public Date getFlDate() {
		return flDate;
	}

	public void setFlDate(Date flDate) {
		this.flDate = flDate;
	}
	
	@Size(min=1, max=30, message="航班号长度必须介于 1 和 30 之间")
	public String getFlNo() {
		return flNo;
	}

	public void setFlNo(String flNo) {
		this.flNo = flNo;
	}
	
	@Size(min=0, max=30, message="飞机号长度必须介于 0 和 30 之间")
	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}
	
	@Size(min=1, max=30, message="机型长度必须介于 1 和 30 之间")
	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}
	
	@Size(min=0, max=30, message="起飞机场长度必须介于 0 和 30 之间")
	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}
	
	@Size(min=0, max=30, message="到达机场长度必须介于 0 和 30 之间")
	public String getPoa() {
		return poa;
	}

	public void setPoa(String poa) {
		this.poa = poa;
	}
	
	@Size(min=0, max=50, message="所属公司长度必须介于 0 和 50 之间")
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	@Size(min=0, max=200, message="csv存储路径长度必须介于 0 和 200 之间")
	public String getCsvPath() {
		return csvPath;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}
	
	@Size(min=0, max=512, message="csv文件名长度必须介于 0 和 512 之间")
	public String getCsvName() {
		return csvName;
	}

	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}
	
	//@id）长度必须介于 0 和 64 之间")
	public String getInfoResourceId() {
		return infoResourceId;
	}

	public void setInfoResourceId(String infoResourceId) {
		this.infoResourceId = infoResourceId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVersionId() {
		return this.versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	
	
	

	public List<String> getIdsList() {
		return this.idsList;
	}

	public void setIdsList(List<String> idsList) {
		this.idsList = idsList;
	}

	public List<DataAuthCondition> getConditionsList() {
		return this.conditionsList;
	}

	public void setConditionsList(List<DataAuthCondition> conditionsList) {
		this.conditionsList = conditionsList;
	}
	
	
}