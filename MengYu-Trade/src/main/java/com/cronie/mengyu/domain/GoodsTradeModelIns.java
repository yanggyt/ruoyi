package com.cronie.mengyu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 计划模型实例表 my_goods_trade_model_ins
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class GoodsTradeModelIns extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 计划实例 ID */
	private Integer insId;
	/** 模型ID */
	private Integer modelId;
	/** 计划状态 */
	private Integer insStatus;
	/** 创建人 */
	private Integer creater;
	/** 创建时间 */
	private Date createTime;
	/** 是否已邮件提醒 */
	private Integer hasMailNotice ;

	public void setInsId(Integer insId) 
	{
		this.insId = insId;
	}

	public Integer getHasMailNotice() {
		return hasMailNotice;
	}

	public void setHasMailNotice(Integer hasMailNotice) {
		this.hasMailNotice = hasMailNotice;
	}

	public Integer getInsId() 
	{
		return insId;
	}
	public void setModelId(Integer modelId) 
	{
		this.modelId = modelId;
	}

	public Integer getModelId() 
	{
		return modelId;
	}
	public void setInsStatus(Integer insStatus) 
	{
		this.insStatus = insStatus;
	}

	public Integer getInsStatus() 
	{
		return insStatus;
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
            .append("insId", getInsId())
            .append("modelId", getModelId())
            .append("insStatus", getInsStatus())
            .append("creater", getCreater())
            .append("createTime", getCreateTime())
            .toString();
    }
}
