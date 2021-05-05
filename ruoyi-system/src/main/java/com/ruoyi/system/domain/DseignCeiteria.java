package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设计标准对象 dseign_ceiteria
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class DseignCeiteria extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设计标准编号 */
    private Long designStandardNumber;

    /** 井编号 */
    private Long oilWellId;

    /** 设计标准名称 */
    @Excel(name = "设计标准名称")
    private String designStandardName;

    /** 设计标准的指定排量 */
    @Excel(name = "设计标准的指定排量")
    private Long designDisplacementofdesignstandard;

    public void setDesignStandardNumber(Long designStandardNumber) 
    {
        this.designStandardNumber = designStandardNumber;
    }

    public Long getDesignStandardNumber() 
    {
        return designStandardNumber;
    }
    public void setOilWellId(Long oilWellId) 
    {
        this.oilWellId = oilWellId;
    }

    public Long getOilWellId() 
    {
        return oilWellId;
    }
    public void setDesignStandardName(String designStandardName) 
    {
        this.designStandardName = designStandardName;
    }

    public String getDesignStandardName() 
    {
        return designStandardName;
    }
    public void setDesignDisplacementofdesignstandard(Long designDisplacementofdesignstandard) 
    {
        this.designDisplacementofdesignstandard = designDisplacementofdesignstandard;
    }

    public Long getDesignDisplacementofdesignstandard() 
    {
        return designDisplacementofdesignstandard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("designStandardNumber", getDesignStandardNumber())
            .append("oilWellId", getOilWellId())
            .append("designStandardName", getDesignStandardName())
            .append("designDisplacementofdesignstandard", getDesignDisplacementofdesignstandard())
            .toString();
    }
}
