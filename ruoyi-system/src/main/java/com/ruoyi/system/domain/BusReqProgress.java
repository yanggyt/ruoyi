package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源需求进度表 bus_req_progress
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusReqProgress extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 进度id */
	private Long progressId;
	/** 资源需求id */
	private Long reqId;
	/** 用户id */
	private Long userId;
	/** 用户名称 */
	private String userName;
	/** 需求当前状态 */
	private String status;
	/** 描述 */
	private String content;

	public void setProgressId(Long progressId) 
	{
		this.progressId = progressId;
	}

	public Long getProgressId() 
	{
		return progressId;
	}
	public void setReqId(Long reqId) 
	{
		this.reqId = reqId;
	}

	public Long getReqId() 
	{
		return reqId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("progressId", getProgressId())
            .append("reqId", getReqId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("status", getStatus())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
