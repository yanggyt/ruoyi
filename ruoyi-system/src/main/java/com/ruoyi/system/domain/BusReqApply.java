package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资源需求接包申请表 bus_req_apply
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusReqApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 申请id */
	private Long applyId;
	/** 需求id */
	private Long reqId;
	/** 申请状态（00待应答/01已应答/99已废弃） */
	private String status;
	/** 申请企业 */
	private Long applyDeptId;
	/** 申请人id */
	private Long applyUserId;
	/** 申请人名称 */
	private String applyUserName;
	/** 申请时间 */
	private Date applyTime;
	/** 申请备注 */
	private String applyRemark;
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
	public void setReqId(Long reqId) 
	{
		this.reqId = reqId;
	}

	public Long getReqId() 
	{
		return reqId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setApplyDeptId(Long applyDeptId) 
	{
		this.applyDeptId = applyDeptId;
	}

	public Long getApplyDeptId() 
	{
		return applyDeptId;
	}
	public void setApplyUserId(Long applyUserId) 
	{
		this.applyUserId = applyUserId;
	}

	public Long getApplyUserId() 
	{
		return applyUserId;
	}
	public void setApplyUserName(String applyUserName) 
	{
		this.applyUserName = applyUserName;
	}

	public String getApplyUserName() 
	{
		return applyUserName;
	}
	public void setApplyTime(Date applyTime) 
	{
		this.applyTime = applyTime;
	}

	public Date getApplyTime() 
	{
		return applyTime;
	}
	public void setApplyRemark(String applyRemark) 
	{
		this.applyRemark = applyRemark;
	}

	public String getApplyRemark() 
	{
		return applyRemark;
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
            .append("reqId", getReqId())
            .append("status", getStatus())
            .append("applyDeptId", getApplyDeptId())
            .append("applyUserId", getApplyUserId())
            .append("applyUserName", getApplyUserName())
            .append("applyTime", getApplyTime())
            .append("applyRemark", getApplyRemark())
            .append("auditUserId", getAuditUserId())
            .append("auditUserName", getAuditUserName())
            .append("auditTime", getAuditTime())
            .append("auditRemark", getAuditRemark())
            .toString();
    }
}
