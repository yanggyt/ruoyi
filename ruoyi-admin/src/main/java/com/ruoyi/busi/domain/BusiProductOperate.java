package com.ruoyi.busi.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成品操作流水对象 busi_product_operate
 * 
 * @author WangCL
 * @date 2022-01-08
 */
public class BusiProductOperate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 成品库存 */
    @Excel(name = "成品库存")
    private String productStockId;

    /** 任务 */
    @Excel(name = "任务")
    private String taskId;


    private String orderId;

    /** 操作数量 */
    @Excel(name = "操作数量")
    private Long amount;

    /** 价值 */
    @Excel(name = "价值")
    private BigDecimal productValue;

    /** 每包数量 */
    private Long amountPerPackage;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String oprateType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setProductStockId(String productStockId) 
    {
        this.productStockId = productStockId;
    }

    public String getProductStockId() 
    {
        return productStockId;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setProductValue(BigDecimal productValue) 
    {
        this.productValue = productValue;
    }

    public BigDecimal getProductValue() 
    {
        return productValue;
    }
    public void setAmountPerPackage(Long amountPerPackage) 
    {
        this.amountPerPackage = amountPerPackage;
    }

    public Long getAmountPerPackage() 
    {
        return amountPerPackage;
    }
    public void setOprateType(String oprateType) 
    {
        this.oprateType = oprateType;
    }

    public String getOprateType() 
    {
        return oprateType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productStockId", getProductStockId())
            .append("taskId", getTaskId())
            .append("amount", getAmount())
            .append("productValue", getProductValue())
            .append("amountPerPackage", getAmountPerPackage())
            .append("oprateType", getOprateType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
