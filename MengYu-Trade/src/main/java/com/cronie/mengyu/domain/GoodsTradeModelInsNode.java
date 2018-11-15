package com.cronie.mengyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.base.BaseEntity;

/**
 * 交易计划操作表 my_goods_trade_model_ins_node
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class GoodsTradeModelInsNode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 操作流水号 */
	private Integer nodeId;
	/** 实例号 */
	private Integer insId;
	/** 交易价格 */
	private BigDecimal price;
	/** 成交份额 */
	private BigDecimal volumes;
	/** 交易时间 */
	private Date tradeTime;
	/** 创建人 */
	private Integer creater;
	/** 创建时间 */
	private Date createTime;
	/** 交易类型（建仓，加仓，减仓，平仓，止损） */
	private Integer tradeType;
	/** 交易笔记 */
	private String tradeNote;

	public void setNodeId(Integer nodeId) 
	{
		this.nodeId = nodeId;
	}

	public Integer getNodeId() 
	{
		return nodeId;
	}
	public void setInsId(Integer insId) 
	{
		this.insId = insId;
	}

	public Integer getInsId() 
	{
		return insId;
	}
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}
	public void setVolumes(BigDecimal volumes) 
	{
		this.volumes = volumes;
	}

	public BigDecimal getVolumes() 
	{
		return volumes;
	}
	public void setTradeTime(Date tradeTime) 
	{
		this.tradeTime = tradeTime;
	}

	public Date getTradeTime() 
	{
		return tradeTime;
	}
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
	public void setTradeType(Integer tradeType) 
	{
		this.tradeType = tradeType;
	}

	public Integer getTradeType() 
	{
		return tradeType;
	}
	public void setTradeNote(String tradeNote) 
	{
		this.tradeNote = tradeNote;
	}

	public String getTradeNote() 
	{
		return tradeNote;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("nodeId", getNodeId())
            .append("insId", getInsId())
            .append("price", getPrice())
            .append("volumes", getVolumes())
            .append("tradeTime", getTradeTime())
            .append("creater", getCreater())
            .append("createTime", getCreateTime())
            .append("tradeType", getTradeType())
            .append("tradeNote", getTradeNote())
            .toString();
    }
}
