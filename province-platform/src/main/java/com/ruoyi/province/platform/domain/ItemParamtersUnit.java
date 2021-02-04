package com.ruoyi.province.platform.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品参数_计量单位对象 platf_item_paramters_unit
 * 
 * @author dalin
 * @date 2021-01-14
 */
public class ItemParamtersUnit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long itemParamtersUnitId;

    /** 主表单据号 */
    @Excel(name = "主表单据号")
    private Long itemParamtersId;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    /** 计量单位状态 */
    @Excel(name = "计量单位状态")
    private String unitStatus;

    /** 计量单位描述 */
    @Excel(name = "计量单位描述")
    private String fmemo;

    public void setItemParamtersUnitId(Long itemParamtersUnitId) 
    {
        this.itemParamtersUnitId = itemParamtersUnitId;
    }

    public Long getItemParamtersUnitId() 
    {
        return itemParamtersUnitId;
    }
    public void setItemParamtersId(Long itemParamtersId) 
    {
        this.itemParamtersId = itemParamtersId;
    }

    public Long getItemParamtersId() 
    {
        return itemParamtersId;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setUnitStatus(String unitStatus) 
    {
        this.unitStatus = unitStatus;
    }

    public String getUnitStatus() 
    {
        return unitStatus;
    }
    public void setFmemo(String fmemo) 
    {
        this.fmemo = fmemo;
    }

    public String getFmemo() 
    {
        return fmemo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemParamtersUnitId", getItemParamtersUnitId())
            .append("itemParamtersId", getItemParamtersId())
            .append("unit", getUnit())
            .append("unitStatus", getUnitStatus())
            .append("fmemo", getFmemo())
            .toString();
    }
}
