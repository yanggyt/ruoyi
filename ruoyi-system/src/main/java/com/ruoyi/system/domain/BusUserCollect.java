package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户收藏表 bus_user_collect
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class BusUserCollect extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 收藏关联id */
	private Long collectId;
	/** 收藏类型（01新闻/02资源/03资源需求） */
	private String collectType;
	/** 收藏人id */
	private Long collectUserId;
	/** 收藏人名称 */
	private String collectUserName;

	public void setCollectId(Long collectId) 
	{
		this.collectId = collectId;
	}

	public Long getCollectId() 
	{
		return collectId;
	}
	public void setCollectType(String collectType) 
	{
		this.collectType = collectType;
	}

	public String getCollectType() 
	{
		return collectType;
	}
	public void setCollectUserId(Long collectUserId) 
	{
		this.collectUserId = collectUserId;
	}

	public Long getCollectUserId() 
	{
		return collectUserId;
	}
	public void setCollectUserName(String collectUserName) 
	{
		this.collectUserName = collectUserName;
	}

	public String getCollectUserName() 
	{
		return collectUserName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("collectId", getCollectId())
            .append("collectType", getCollectType())
            .append("collectUserId", getCollectUserId())
            .append("collectUserName", getCollectUserName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
