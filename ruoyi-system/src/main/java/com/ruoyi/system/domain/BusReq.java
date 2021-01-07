package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 资源需求表 bus_req
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusReq extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 需求id */
	private Long reqId;
	/** 需求类型 */
	private Long reqType;
	/** 需求名称 */
	private String reqName;
	/** 需求级别 */
	private String classes;
	/** 需求标签 */
	private String label;
	/** 需求描述 */
	private String content;
	/** 需求状态（00待发布/01已发布/02处理中/03验收中/04已完成/00已屏蔽） */
	private String status;
	/** 发布企业 */
	private Long publishDeptId;
	/** 发布人id */
	private Long publishUserId;
	/** 发布人名称 */
	private String publishUserName;
	/** 接收企业 */
	private Long receiveDeptId;
	/** 接收人id */
	private Long receiveUserId;
	/** 接收人名称 */
	private String receiveUserName;
	/** 接收时间 */
	private Date receiveTime;

	public void setReqId(Long reqId) 
	{
		this.reqId = reqId;
	}

	public Long getReqId() 
	{
		return reqId;
	}
	public void setReqType(Long reqType) 
	{
		this.reqType = reqType;
	}

	public Long getReqType() 
	{
		return reqType;
	}
	public void setReqName(String reqName) 
	{
		this.reqName = reqName;
	}

	public String getReqName() 
	{
		return reqName;
	}
	public void setClasses(String classes) 
	{
		this.classes = classes;
	}

	public String getClasses() 
	{
		return classes;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}

	public String getLabel() 
	{
		return label;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setPublishDeptId(Long publishDeptId) 
	{
		this.publishDeptId = publishDeptId;
	}

	public Long getPublishDeptId() 
	{
		return publishDeptId;
	}
	public void setPublishUserId(Long publishUserId) 
	{
		this.publishUserId = publishUserId;
	}

	public Long getPublishUserId() 
	{
		return publishUserId;
	}
	public void setPublishUserName(String publishUserName) 
	{
		this.publishUserName = publishUserName;
	}

	public String getPublishUserName() 
	{
		return publishUserName;
	}
	public void setReceiveDeptId(Long receiveDeptId) 
	{
		this.receiveDeptId = receiveDeptId;
	}

	public Long getReceiveDeptId() 
	{
		return receiveDeptId;
	}
	public void setReceiveUserId(Long receiveUserId) 
	{
		this.receiveUserId = receiveUserId;
	}

	public Long getReceiveUserId() 
	{
		return receiveUserId;
	}
	public void setReceiveUserName(String receiveUserName) 
	{
		this.receiveUserName = receiveUserName;
	}

	public String getReceiveUserName() 
	{
		return receiveUserName;
	}
	public void setReceiveTime(Date receiveTime) 
	{
		this.receiveTime = receiveTime;
	}

	public Date getReceiveTime() 
	{
		return receiveTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reqId", getReqId())
            .append("reqType", getReqType())
            .append("reqName", getReqName())
            .append("classes", getClasses())
            .append("label", getLabel())
            .append("content", getContent())
            .append("status", getStatus())
            .append("publishDeptId", getPublishDeptId())
            .append("publishUserId", getPublishUserId())
            .append("publishUserName", getPublishUserName())
            .append("createTime", getCreateTime())
            .append("receiveDeptId", getReceiveDeptId())
            .append("receiveUserId", getReceiveUserId())
            .append("receiveUserName", getReceiveUserName())
            .append("receiveTime", getReceiveTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
