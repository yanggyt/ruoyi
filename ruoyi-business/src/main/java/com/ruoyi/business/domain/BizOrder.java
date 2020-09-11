package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 biz_order
 * 
 * @author ruoyi
 * @date 2020-09-09
 */
public class BizOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long id;

    /** 订单编码 */
    @Excel(name = "订单编码")
    private String orderSn;

    /** 会员ID */
    private Long memberId;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String memberName;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 订单状态：0-待支付，1-已支付，2-已取消 */
    @Excel(name = "订单状态：0-待支付，1-已支付，2-已取消")
    private Integer orderStatus;

    /** 收货人地址ID */
    @Excel(name = "收货人地址ID")
    private Long addressId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }
    public void setAddressId(Long addressId) 
    {
        this.addressId = addressId;
    }

    public Long getAddressId() 
    {
        return addressId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderSn", getOrderSn())
            .append("memberId", getMemberId())
            .append("mobile", getMobile())
            .append("memberName", getMemberName())
            .append("orderAmount", getOrderAmount())
            .append("orderStatus", getOrderStatus())
            .append("addressId", getAddressId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
