package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资源使用申请表 bus_resource_apply
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusResourceApply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 申请id */
	private Long applyId;
	/** 资源id */
	private Long resourceId;
	/** 申请状态（00待审核/01已通过/99未通过） */
	private String status;
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
	public void setResourceId(Long resourceId) 
	{
		this.resourceId = resourceId;
	}

	public Long getResourceId() 
	{
		return resourceId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
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
            .append("resourceId", getResourceId())
            .append("status", getStatus())
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
