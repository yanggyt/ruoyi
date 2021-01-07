package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源表 bus_resource
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusResource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 资源id */
	private Long resourceId;
	/** 资源分类id */
	private Long typeId;
	/** 资源级别 */
	private String classes;
	/** 资源基本信息 */
	private String content;
	/** 资源标签 */
	private String label;
	/** 资源状态（00待发布/01已发布/99已屏蔽） */
	private String status;
	/** 发布企业 */
	private Long publishDeptId;
	/** 发布人id */
	private Long publishUserId;
	/** 发布人名称 */
	private String publishUserName;

	public void setResourceId(Long resourceId) 
	{
		this.resourceId = resourceId;
	}

	public Long getResourceId() 
	{
		return resourceId;
	}
	public void setTypeId(Long typeId) 
	{
		this.typeId = typeId;
	}

	public Long getTypeId() 
	{
		return typeId;
	}
	public void setClasses(String classes) 
	{
		this.classes = classes;
	}

	public String getClasses() 
	{
		return classes;
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
            .append("resourceId", getResourceId())
            .append("typeId", getTypeId())
            .append("classes", getClasses())
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
