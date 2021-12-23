package com.ruoyi.busi.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品需求对象 busi_product_require
 * 
 * @author WangCL
 * @date 2021-12-22
 */
public class BusiProductRequire extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 订单ID */
    private String orderId;

    /** 订单名称 */
    @Excel(name = "订单名称")
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

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 物料需求信息 */
    private List<BusiMaterialRequire> busiMaterialRequireList;

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
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    public List<BusiMaterialRequire> getBusiMaterialRequireList()
    {
        return busiMaterialRequireList;
    }

    public void setBusiMaterialRequireList(List<BusiMaterialRequire> busiMaterialRequireList)
    {
        this.busiMaterialRequireList = busiMaterialRequireList;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderName", getOrderName())
            .append("amount", getAmount())
            .append("size", getSize())
            .append("color", getColor())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("endDate", getEndDate())
            .append("busiMaterialRequireList", getBusiMaterialRequireList())
            .toString();
    }
}
