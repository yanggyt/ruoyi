package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件表 bus_accessory
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusAccessory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 附件id */
	private Long accessoryId;
	/** 附件类型 */
	private String accessoryType;
	/** 附件名称 */
	private String accessoryName;
	/** 附件地址 */
	private String address;
	/** 附件大小 */
	private Double size;
	/** 附件关联id */
	private Long relevancyId;
	/** 附件关联类型（新闻/资源/资源需求/企业明细/公共文档等） */
	private String relevancyType;

	public void setAccessoryId(Long accessoryId) 
	{
		this.accessoryId = accessoryId;
	}

	public Long getAccessoryId() 
	{
		return accessoryId;
	}
	public void setAccessoryType(String accessoryType) 
	{
		this.accessoryType = accessoryType;
	}

	public String getAccessoryType() 
	{
		return accessoryType;
	}
	public void setAccessoryName(String accessoryName) 
	{
		this.accessoryName = accessoryName;
	}

	public String getAccessoryName() 
	{
		return accessoryName;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setSize(Double size) 
	{
		this.size = size;
	}

	public Double getSize() 
	{
		return size;
	}
	public void setRelevancyId(Long relevancyId) 
	{
		this.relevancyId = relevancyId;
	}

	public Long getRelevancyId() 
	{
		return relevancyId;
	}
	public void setRelevancyType(String relevancyType) 
	{
		this.relevancyType = relevancyType;
	}

	public String getRelevancyType() 
	{
		return relevancyType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accessoryId", getAccessoryId())
            .append("accessoryType", getAccessoryType())
            .append("accessoryName", getAccessoryName())
            .append("address", getAddress())
            .append("size", getSize())
            .append("createTime", getCreateTime())
            .append("relevancyId", getRelevancyId())
            .append("relevancyType", getRelevancyType())
            .toString();
    }
}
