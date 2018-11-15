package com.cronie.mengyu.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.base.BaseEntity;

/**
 * 资金池交明细表 my_money_pool_log
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class MoneyPoolLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 交易类型  (支出/收入) */
	private Integer billType;
	/** 交易后的资金池金额 */
	private BigDecimal moneyPoolLog;
	/** 交易金额 */
	private BigDecimal trademMoney;
	/** 资金池ID号 */
	private Integer moneyPoolId;
	/** 资金池流水号ID=交易ID号 */
	private Integer nodeId;

	public void setBillType(Integer billType) 
	{
		this.billType = billType;
	}

	public Integer getBillType() 
	{
		return billType;
	}
	public void setMoneyPoolLog(BigDecimal moneyPoolLog) 
	{
		this.moneyPoolLog = moneyPoolLog;
	}

	public BigDecimal getMoneyPoolLog() 
	{
		return moneyPoolLog;
	}
	public void setTrademMoney(BigDecimal trademMoney) 
	{
		this.trademMoney = trademMoney;
	}

	public BigDecimal getTrademMoney() 
	{
		return trademMoney;
	}
	public void setMoneyPoolId(Integer moneyPoolId) 
	{
		this.moneyPoolId = moneyPoolId;
	}

	public Integer getMoneyPoolId() 
	{
		return moneyPoolId;
	}
	public void setNodeId(Integer nodeId) 
	{
		this.nodeId = nodeId;
	}

	public Integer getNodeId() 
	{
		return nodeId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("billType", getBillType())
            .append("moneyPoolLog", getMoneyPoolLog())
            .append("trademMoney", getTrademMoney())
            .append("moneyPoolId", getMoneyPoolId())
            .append("nodeId", getNodeId())
            .toString();
    }
}
