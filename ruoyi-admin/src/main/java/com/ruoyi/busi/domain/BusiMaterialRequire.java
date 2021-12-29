package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 物料需求对象 busi_material_require
 * 
 * @author WangCL
 * @date 2021-12-22
 */
public class BusiMaterialRequire extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 产品需求 */
    @Excel(name = "产品需求")
    private String productRequireId;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal amount;

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
    public void setProductRequireId(String productRequireId) 
    {
        this.productRequireId = productRequireId;
    }

    public String getProductRequireId() 
    {
        return productRequireId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
            .append("productRequireId", getProductRequireId())
            .append("amount", getAmount())
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
