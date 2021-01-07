package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户浏览足迹表 bus_user_browse
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusUserBrowse extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 浏览关联id */
	private Long browseId;
	/** 浏览类型（01新闻/02资源/03资源需求） */
	private String browseType;
	/** 浏览人id */
	private Long browseUserId;
	/** 浏览人名称 */
	private String browseUserName;

	public void setBrowseId(Long browseId) 
	{
		this.browseId = browseId;
	}

	public Long getBrowseId() 
	{
		return browseId;
	}
	public void setBrowseType(String browseType) 
	{
		this.browseType = browseType;
	}

	public String getBrowseType() 
	{
		return browseType;
	}
	public void setBrowseUserId(Long browseUserId) 
	{
		this.browseUserId = browseUserId;
	}

	public Long getBrowseUserId() 
	{
		return browseUserId;
	}
	public void setBrowseUserName(String browseUserName) 
	{
		this.browseUserName = browseUserName;
	}

	public String getBrowseUserName() 
	{
		return browseUserName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("browseId", getBrowseId())
            .append("browseType", getBrowseType())
            .append("browseUserId", getBrowseUserId())
            .append("browseUserName", getBrowseUserName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
