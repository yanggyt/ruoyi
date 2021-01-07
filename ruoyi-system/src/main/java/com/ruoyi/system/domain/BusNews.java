package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻表 bus_news
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusNews extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 新闻id */
	private Long newsId;
	/** 新闻标题 */
	private String title;
	/** 新闻正文 */
	private String content;
	/** 新闻标签 */
	private String label;
	/** 新闻状态（00待发布/01已发布/99已屏蔽） */
	private String status;
	/** 发布企业 */
	private Long publishDeptId;
	/** 发布人id */
	private Long publishUserId;
	/** 发布人名称 */
	private String publishUserName;

	public void setNewsId(Long newsId) 
	{
		this.newsId = newsId;
	}

	public Long getNewsId() 
	{
		return newsId;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}

	public String getLabel() 
	{
		return label;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsId", getNewsId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("label", getLabel())
            .append("status", getStatus())
            .append("publishDeptId", getPublishDeptId())
            .append("publishUserId", getPublishUserId())
            .append("publishUserName", getPublishUserName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
