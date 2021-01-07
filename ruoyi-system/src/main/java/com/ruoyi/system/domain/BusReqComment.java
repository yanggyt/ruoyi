package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源需求评论表 bus_req_comment
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusReqComment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 评论id */
	private Long commentId;
	/** 资源需求id */
	private Long reqId;
	/** 用户id */
	private Long userId;
	/** 用户名称 */
	private String userName;
	/** 评论内容 */
	private String content;

	public void setCommentId(Long commentId) 
	{
		this.commentId = commentId;
	}

	public Long getCommentId() 
	{
		return commentId;
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
            .append("commentId", getCommentId())
            .append("reqId", getReqId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
