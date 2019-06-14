package com.ruoyi.template.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务器网卡类型表 tmpl_server_netcard
 * 
 * @author TP
 * @date 2019-06-14
 */
public class TmplServerNetcard extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 服务器网卡编号 */
	private Integer serverNetcardId;
	/** 服务器编号 */
	private Integer serverId;
	/** 服务器网卡类型 */
	private Long serverNetcardType;
	/** 服务器网卡数量 */
	private Integer serverNetcardNum;

	public void setServerNetcardId(Integer serverNetcardId) 
	{
		this.serverNetcardId = serverNetcardId;
	}

	public Integer getServerNetcardId() 
	{
		return serverNetcardId;
	}
	public void setServerId(Integer serverId) 
	{
		this.serverId = serverId;
	}

	public Integer getServerId() 
	{
		return serverId;
	}
	public void setServerNetcardType(Long serverNetcardType) 
	{
		this.serverNetcardType = serverNetcardType;
	}

	public Long getServerNetcardType() 
	{
		return serverNetcardType;
	}
	public void setServerNetcardNum(Integer serverNetcardNum) 
	{
		this.serverNetcardNum = serverNetcardNum;
	}

	public Integer getServerNetcardNum() 
	{
		return serverNetcardNum;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serverNetcardId", getServerNetcardId())
            .append("serverId", getServerId())
            .append("serverNetcardType", getServerNetcardType())
            .append("serverNetcardNum", getServerNetcardNum())
            .toString();
    }
}
