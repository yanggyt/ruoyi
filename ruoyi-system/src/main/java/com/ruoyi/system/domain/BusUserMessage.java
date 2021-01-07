package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户消息表 bus_user_message
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusUserMessage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 消息id */
	private Long messageId;
	/** 消息类型 */
	private String messageType;
	/** 消息内容 */
	private String content;
	/** 接收人id */
	private Long receiveUserId;
	/** 接收人名称 */
	private String receiveUserName;
	/** 发布人id */
	private Long publishUserId;
	/** 发布人名称 */
	private String publishUserName;

	public void setMessageId(Long messageId) 
	{
		this.messageId = messageId;
	}

	public Long getMessageId() 
	{
		return messageId;
	}
	public void setMessageType(String messageType) 
	{
		this.messageType = messageType;
	}

	public String getMessageType() 
	{
		return messageType;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("messageType", getMessageType())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("receiveUserId", getReceiveUserId())
            .append("receiveUserName", getReceiveUserName())
            .append("publishUserId", getPublishUserId())
            .append("publishUserName", getPublishUserName())
            .toString();
    }
}
