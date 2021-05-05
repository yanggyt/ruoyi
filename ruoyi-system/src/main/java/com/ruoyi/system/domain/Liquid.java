package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 液体数据对象 liquid
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class Liquid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 液体编号 */
    private Long liquidNumber;

    /** 流变参数编号 */
    private Long rheologicalParameterNumber;

    /** 井编号 */
    private Long oilWellId;

    /** 液体名称 */
    @Excel(name = "液体名称")
    private String liquidName;

    /** 液体密度 */
    @Excel(name = "液体密度")
    private Long liquidDensity;

    /** 液体返深 */
    private Long liquidReturnDepth;

    /** 液体容积 */
    private Long liquidVolume;

    /** 液体附加 */
    private Long liquidAdditional;

    /** 液体附加量 */
    private Long liquidAdditionalCapacity;

    /** 液体用量 */
    @Excel(name = "液体用量")
    private Long liquidDosage;

    /** 液体基本性能 */
    private String liquidBasicProperties;

    /** 液体失水量 */
    private Long liquidWaterLoss;

    /** 液体稠化时间(min/50BC) */
    private Long fivebcLiquidThickeningTime;

    /** 液体稠化时间(min/100BC) */
    private Long onehbcLiquidThickeningTime;

    /** 液体使用长度 */
    @Excel(name = "液体使用长度")
    private Long liquidServiceLength;

    /** 液体配方 */
    @Excel(name = "液体配方")
    private String liquidFormula;

    public void setLiquidNumber(Long liquidNumber) 
    {
        this.liquidNumber = liquidNumber;
    }

    public Long getLiquidNumber() 
    {
        return liquidNumber;
    }
    public void setRheologicalParameterNumber(Long rheologicalParameterNumber) 
    {
        this.rheologicalParameterNumber = rheologicalParameterNumber;
    }

    public Long getRheologicalParameterNumber() 
    {
        return rheologicalParameterNumber;
    }
    public void setOilWellId(Long oilWellId) 
    {
        this.oilWellId = oilWellId;
    }

    public Long getOilWellId() 
    {
        return oilWellId;
    }
    public void setLiquidName(String liquidName) 
    {
        this.liquidName = liquidName;
    }

    public String getLiquidName() 
    {
        return liquidName;
    }
    public void setLiquidDensity(Long liquidDensity) 
    {
        this.liquidDensity = liquidDensity;
    }

    public Long getLiquidDensity() 
    {
        return liquidDensity;
    }
    public void setLiquidReturnDepth(Long liquidReturnDepth) 
    {
        this.liquidReturnDepth = liquidReturnDepth;
    }

    public Long getLiquidReturnDepth() 
    {
        return liquidReturnDepth;
    }
    public void setLiquidVolume(Long liquidVolume) 
    {
        this.liquidVolume = liquidVolume;
    }

    public Long getLiquidVolume() 
    {
        return liquidVolume;
    }
    public void setLiquidAdditional(Long liquidAdditional) 
    {
        this.liquidAdditional = liquidAdditional;
    }

    public Long getLiquidAdditional() 
    {
        return liquidAdditional;
    }
    public void setLiquidAdditionalCapacity(Long liquidAdditionalCapacity) 
    {
        this.liquidAdditionalCapacity = liquidAdditionalCapacity;
    }

    public Long getLiquidAdditionalCapacity() 
    {
        return liquidAdditionalCapacity;
    }
    public void setLiquidDosage(Long liquidDosage) 
    {
        this.liquidDosage = liquidDosage;
    }

    public Long getLiquidDosage() 
    {
        return liquidDosage;
    }
    public void setLiquidBasicProperties(String liquidBasicProperties) 
    {
        this.liquidBasicProperties = liquidBasicProperties;
    }

    public String getLiquidBasicProperties() 
    {
        return liquidBasicProperties;
    }
    public void setLiquidWaterLoss(Long liquidWaterLoss) 
    {
        this.liquidWaterLoss = liquidWaterLoss;
    }

    public Long getLiquidWaterLoss() 
    {
        return liquidWaterLoss;
    }
    public void setFivebcLiquidThickeningTime(Long fivebcLiquidThickeningTime) 
    {
        this.fivebcLiquidThickeningTime = fivebcLiquidThickeningTime;
    }

    public Long getFivebcLiquidThickeningTime() 
    {
        return fivebcLiquidThickeningTime;
    }
    public void setOnehbcLiquidThickeningTime(Long onehbcLiquidThickeningTime) 
    {
        this.onehbcLiquidThickeningTime = onehbcLiquidThickeningTime;
    }

    public Long getOnehbcLiquidThickeningTime() 
    {
        return onehbcLiquidThickeningTime;
    }
    public void setLiquidServiceLength(Long liquidServiceLength) 
    {
        this.liquidServiceLength = liquidServiceLength;
    }

    public Long getLiquidServiceLength() 
    {
        return liquidServiceLength;
    }
    public void setLiquidFormula(String liquidFormula) 
    {
        this.liquidFormula = liquidFormula;
    }

    public String getLiquidFormula() 
    {
        return liquidFormula;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("liquidNumber", getLiquidNumber())
            .append("rheologicalParameterNumber", getRheologicalParameterNumber())
            .append("oilWellId", getOilWellId())
            .append("liquidName", getLiquidName())
            .append("liquidDensity", getLiquidDensity())
            .append("liquidReturnDepth", getLiquidReturnDepth())
            .append("liquidVolume", getLiquidVolume())
            .append("liquidAdditional", getLiquidAdditional())
            .append("liquidAdditionalCapacity", getLiquidAdditionalCapacity())
            .append("liquidDosage", getLiquidDosage())
            .append("liquidBasicProperties", getLiquidBasicProperties())
            .append("liquidWaterLoss", getLiquidWaterLoss())
            .append("fivebcLiquidThickeningTime", getFivebcLiquidThickeningTime())
            .append("onehbcLiquidThickeningTime", getOnehbcLiquidThickeningTime())
            .append("liquidServiceLength", getLiquidServiceLength())
            .append("liquidFormula", getLiquidFormula())
            .toString();
    }
}
