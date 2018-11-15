package com.cronie.mengyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.base.BaseEntity;

/**
 * 资金池表 my_money_pool
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class MoneyPool extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 创建人 */
	private Integer creater;
	/** 创建时间 */
	private Date createTime;
	/** 资金池金额 单位（元） */
	private BigDecimal moneyPool;
	/** 资金池ID=用户ID */
	private Date moneyPoolId;

	public void setCreater(Integer creater) 
	{
		this.creater = creater;
	}

	public Integer getCreater() 
	{
		return creater;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setMoneyPool(BigDecimal moneyPool) 
	{
		this.moneyPool = moneyPool;
	}

	public BigDecimal getMoneyPool() 
	{
		return moneyPool;
	}
	public void setMoneyPoolId(Date moneyPoolId) 
	{
		this.moneyPoolId = moneyPoolId;
	}

	public Date getMoneyPoolId() 
	{
		return moneyPoolId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("creater", getCreater())
            .append("createTime", getCreateTime())
            .append("moneyPool", getMoneyPool())
            .append("moneyPoolId", getMoneyPoolId())
            .toString();
    }
}
