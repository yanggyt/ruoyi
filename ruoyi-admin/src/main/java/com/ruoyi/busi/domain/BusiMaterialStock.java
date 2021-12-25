package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料库存对象 busi_material_stock
 * 
 * @author WangCL
 * @date 2021-12-25
 */
public class BusiMaterialStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 所属订单 */
    @Excel(name = "所属订单")
    private String orderId;

    /** 进库量 */
    @Excel(name = "进库量")
    private String amountIn;

    /** 出库量 */
    @Excel(name = "出库量")
    private String amountOut;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 类型 */
    @Excel(name = "类型")
    private String classify;

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
    public void setAmountIn(String amountIn) 
    {
        this.amountIn = amountIn;
    }

    public String getAmountIn() 
    {
        return amountIn;
    }
    public void setAmountOut(String amountOut) 
    {
        this.amountOut = amountOut;
    }

    public String getAmountOut() 
    {
        return amountOut;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setClassify(String classify) 
    {
        this.classify = classify;
    }

    public String getClassify() 
    {
        return classify;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("amountIn", getAmountIn())
            .append("amountOut", getAmountOut())
            .append("unit", getUnit())
            .append("color", getColor())
            .append("classify", getClassify())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
