package com.ruoyi.template.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 交换机模板表 tmpl_switch
 * 
 * @author TP
 * @date 2019-06-11
 */
public class Switch extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 交换机模板编号 */
	private Integer serverId;
	/** 交换机品牌 */
	private String serverBrand;
	/** 交换机型号 */
	private String serverType;
	/** 交换机电源数量 */
	private Integer powerNum;

	public void setServerId(Integer serverId) 
	{
		this.serverId = serverId;
	}

	public Integer getServerId() 
	{
		return serverId;
	}
	public void setServerBrand(String serverBrand) 
	{
		this.serverBrand = serverBrand;
	}

	public String getServerBrand() 
	{
		return serverBrand;
	}
	public void setServerType(String serverType) 
	{
		this.serverType = serverType;
	}

	public String getServerType() 
	{
		return serverType;
	}
	public void setPowerNum(Integer powerNum) 
	{
		this.powerNum = powerNum;
	}

	public Integer getPowerNum() 
	{
		return powerNum;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serverId", getServerId())
            .append("serverBrand", getServerBrand())
            .append("serverType", getServerType())
            .append("powerNum", getPowerNum())
            .toString();
    }
}
