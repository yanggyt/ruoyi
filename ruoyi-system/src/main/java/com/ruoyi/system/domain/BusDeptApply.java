package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 企业入驻申请表 bus_dept_apply
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusDeptApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 入驻申请id */
	private Long applyId;
	/** 申请类型（企业注册/企业迁址） */
	private String applyType;
	/** 企业名称 */
	private String deptName;
	/** 行业 */
	private String business;
	/** 企业经营范围 */
	private String deptScope;
	/** 企业类型 */
	private String deptType;
	/** 企业链接 */
	private String deptAddress;
	/** 企业联系电话 */
	private String deptPhone;
	/** 法人信息(姓名、性别、政治面貌、固定电话、手机号码等) */
	private String legalConfig;
	/** 企业工作人员信息列表（姓名、性别、手机号码、车牌号等） */
	private String deptUsers;
	/** 工商注册号 */
	private String regCode;
	/** 统一社会信用代码 */
	private String creditCode;
	/** 组织机构代码 */
	private String orgCode;
	/** 纳税人识别号 */
	private String taxpayerCode;
	/** 纳税人资质 */
	private String taxpayerQua;
	/** 审核状态（00待审核/01已通过/99未通过） */
	private String status;
	/** 审核人id */
	private Long auditUserId;
	/** 审核人名称 */
	private String auditUserName;
	/** 审核时间 */
	private Date auditTime;
	/** 审核意见 */
	private String auditRemark;

	public void setApplyId(Long applyId) 
	{
		this.applyId = applyId;
	}

	public Long getApplyId() 
	{
		return applyId;
	}
	public void setApplyType(String applyType) 
	{
		this.applyType = applyType;
	}

	public String getApplyType() 
	{
		return applyType;
	}
	public void setDeptName(String deptName) 
	{
		this.deptName = deptName;
	}

	public String getDeptName() 
	{
		return deptName;
	}
	public void setBusiness(String business) 
	{
		this.business = business;
	}

	public String getBusiness() 
	{
		return business;
	}
	public void setDeptScope(String deptScope) 
	{
		this.deptScope = deptScope;
	}

	public String getDeptScope() 
	{
		return deptScope;
	}
	public void setDeptType(String deptType) 
	{
		this.deptType = deptType;
	}

	public String getDeptType() 
	{
		return deptType;
	}
	public void setDeptAddress(String deptAddress) 
	{
		this.deptAddress = deptAddress;
	}

	public String getDeptAddress() 
	{
		return deptAddress;
	}
	public void setDeptPhone(String deptPhone) 
	{
		this.deptPhone = deptPhone;
	}

	public String getDeptPhone() 
	{
		return deptPhone;
	}
	public void setLegalConfig(String legalConfig) 
	{
		this.legalConfig = legalConfig;
	}

	public String getLegalConfig() 
	{
		return legalConfig;
	}
	public void setDeptUsers(String deptUsers) 
	{
		this.deptUsers = deptUsers;
	}

	public String getDeptUsers() 
	{
		return deptUsers;
	}
	public void setRegCode(String regCode) 
	{
		this.regCode = regCode;
	}

	public String getRegCode() 
	{
		return regCode;
	}
	public void setCreditCode(String creditCode) 
	{
		this.creditCode = creditCode;
	}

	public String getCreditCode() 
	{
		return creditCode;
	}
	public void setOrgCode(String orgCode) 
	{
		this.orgCode = orgCode;
	}

	public String getOrgCode() 
	{
		return orgCode;
	}
	public void setTaxpayerCode(String taxpayerCode) 
	{
		this.taxpayerCode = taxpayerCode;
	}

	public String getTaxpayerCode() 
	{
		return taxpayerCode;
	}
	public void setTaxpayerQua(String taxpayerQua) 
	{
		this.taxpayerQua = taxpayerQua;
	}

	public String getTaxpayerQua() 
	{
		return taxpayerQua;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setAuditUserId(Long auditUserId) 
	{
		this.auditUserId = auditUserId;
	}

	public Long getAuditUserId() 
	{
		return auditUserId;
	}
	public void setAuditUserName(String auditUserName) 
	{
		this.auditUserName = auditUserName;
	}

	public String getAuditUserName() 
	{
		return auditUserName;
	}
	public void setAuditTime(Date auditTime) 
	{
		this.auditTime = auditTime;
	}

	public Date getAuditTime() 
	{
		return auditTime;
	}
	public void setAuditRemark(String auditRemark) 
	{
		this.auditRemark = auditRemark;
	}

	public String getAuditRemark() 
	{
		return auditRemark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applyId", getApplyId())
            .append("applyType", getApplyType())
            .append("deptName", getDeptName())
            .append("business", getBusiness())
            .append("deptScope", getDeptScope())
            .append("deptType", getDeptType())
            .append("deptAddress", getDeptAddress())
            .append("deptPhone", getDeptPhone())
            .append("legalConfig", getLegalConfig())
            .append("deptUsers", getDeptUsers())
            .append("regCode", getRegCode())
            .append("creditCode", getCreditCode())
            .append("orgCode", getOrgCode())
            .append("taxpayerCode", getTaxpayerCode())
            .append("taxpayerQua", getTaxpayerQua())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("auditUserId", getAuditUserId())
            .append("auditUserName", getAuditUserName())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .toString();
    }
}
