package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成品库存对象 busi_product_stock
 * 
 * @author WangCL
 * @date 2022-01-08
 */
public class BusiProductStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 订单 */
    private String orderId;

    /** 订单 */
    @Excel(name = "订单")
    private String orderName;

    /** 数量 */
    @Excel(name = "数量")
    private Long amount;

    /** 尺码 */
    @Excel(name = "尺码")
    private String size;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("amount", getAmount())
            .append("size", getSize())
            .append("color", getColor())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
