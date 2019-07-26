package com.bmw.servicecenter.domain;


import com.bmw.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单表 sc_order
 * 
 * @author bmw
 * @date 2019-07-26
 */
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 订单ID */
	private Long orderId;
	/** 客户ID */
	private Long memberId;
	/** 服务员ID */
	private Long attendantId;

	public void setOrderId(Long orderId) 
	{
		this.orderId = orderId;
	}

	public Long getOrderId() 
	{
		return orderId;
	}
	public void setMemberId(Long memberId) 
	{
		this.memberId = memberId;
	}

	public Long getMemberId() 
	{
		return memberId;
	}
	public void setAttendantId(Long attendantId) 
	{
		this.attendantId = attendantId;
	}

	public Long getAttendantId() 
	{
		return attendantId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("memberId", getMemberId())
            .append("attendantId", getAttendantId())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
