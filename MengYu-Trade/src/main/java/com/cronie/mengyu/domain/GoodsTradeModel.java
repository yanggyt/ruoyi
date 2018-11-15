package com.cronie.mengyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.base.BaseEntity;

/**
 * 品种计划模型表 my_goods_trade_model
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class GoodsTradeModel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 模型ID */
	private Integer modelId;
	/** 品种代码 */
	private String code;
	/** 基础成交量 */
	private BigDecimal basicVolumes;
	/** 模型状态 （启用，停用） */
	private Integer modelStatus;
	/** 建仓价 */
	private BigDecimal buildingPrice;
	/** 平仓价 */
	private BigDecimal exitPrice;
	/** 止损价 */
	private BigDecimal stopPrice;
	/** 创建人 */
	private Integer creater;
	/** 创建时间 */
	private Date createTime;

	public void setModelId(Integer modelId) 
	{
		this.modelId = modelId;
	}

	public Integer getModelId() 
	{
		return modelId;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setBasicVolumes(BigDecimal basicVolumes) 
	{
		this.basicVolumes = basicVolumes;
	}

	public BigDecimal getBasicVolumes() 
	{
		return basicVolumes;
	}
	public void setModelStatus(Integer modelStatus) 
	{
		this.modelStatus = modelStatus;
	}

	public Integer getModelStatus() 
	{
		return modelStatus;
	}
	public void setBuildingPrice(BigDecimal buildingPrice) 
	{
		this.buildingPrice = buildingPrice;
	}

	public BigDecimal getBuildingPrice() 
	{
		return buildingPrice;
	}
	public void setExitPrice(BigDecimal exitPrice) 
	{
		this.exitPrice = exitPrice;
	}

	public BigDecimal getExitPrice() 
	{
		return exitPrice;
	}
	public void setStopPrice(BigDecimal stopPrice) 
	{
		this.stopPrice = stopPrice;
	}

	public BigDecimal getStopPrice() 
	{
		return stopPrice;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modelId", getModelId())
            .append("code", getCode())
            .append("basicVolumes", getBasicVolumes())
            .append("modelStatus", getModelStatus())
            .append("buildingPrice", getBuildingPrice())
            .append("exitPrice", getExitPrice())
            .append("stopPrice", getStopPrice())
            .append("creater", getCreater())
            .append("createTime", getCreateTime())
            .toString();
    }
}
