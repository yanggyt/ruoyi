package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流变参数对象 eheological_parameters
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class EheologicalParameters extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流变参数编号 */
    private Long rheologicalParameternumber;

    /** 流体名称 */
    @Excel(name = "流体名称")
    private String rheologicalName;

    /** 流体密度 */
    @Excel(name = "流体密度")
    private String rheologicalDensity;

    /** 流变模式 */
    @Excel(name = "流变模式")
    private String rheologicalModel;

    /** 塑性粘度 */
    @Excel(name = "塑性粘度")
    private String plasticViscosity;

    /** 动切力 */
    @Excel(name = "动切力")
    private String dynamicShear;

    /** 稠度系数 */
    @Excel(name = "稠度系数")
    private String consistencyCoefficient;

    /** 流性指数 */
    @Excel(name = "流性指数")
    private String fluidityIndex;

    /** 牛顿粘度 */
    @Excel(name = "牛顿粘度")
    private String newtonianViscosity;

    public void setRheologicalParameternumber(Long rheologicalParameternumber) 
    {
        this.rheologicalParameternumber = rheologicalParameternumber;
    }

    public Long getRheologicalParameternumber() 
    {
        return rheologicalParameternumber;
    }
    public void setRheologicalName(String rheologicalName) 
    {
        this.rheologicalName = rheologicalName;
    }

    public String getRheologicalName() 
    {
        return rheologicalName;
    }
    public void setRheologicalDensity(String rheologicalDensity) 
    {
        this.rheologicalDensity = rheologicalDensity;
    }

    public String getRheologicalDensity() 
    {
        return rheologicalDensity;
    }
    public void setRheologicalModel(String rheologicalModel) 
    {
        this.rheologicalModel = rheologicalModel;
    }

    public String getRheologicalModel() 
    {
        return rheologicalModel;
    }
    public void setPlasticViscosity(String plasticViscosity) 
    {
        this.plasticViscosity = plasticViscosity;
    }

    public String getPlasticViscosity() 
    {
        return plasticViscosity;
    }
    public void setDynamicShear(String dynamicShear) 
    {
        this.dynamicShear = dynamicShear;
    }

    public String getDynamicShear() 
    {
        return dynamicShear;
    }
    public void setConsistencyCoefficient(String consistencyCoefficient) 
    {
        this.consistencyCoefficient = consistencyCoefficient;
    }

    public String getConsistencyCoefficient() 
    {
        return consistencyCoefficient;
    }
    public void setFluidityIndex(String fluidityIndex) 
    {
        this.fluidityIndex = fluidityIndex;
    }

    public String getFluidityIndex() 
    {
        return fluidityIndex;
    }
    public void setNewtonianViscosity(String newtonianViscosity) 
    {
        this.newtonianViscosity = newtonianViscosity;
    }

    public String getNewtonianViscosity() 
    {
        return newtonianViscosity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rheologicalParameternumber", getRheologicalParameternumber())
            .append("rheologicalName", getRheologicalName())
            .append("rheologicalDensity", getRheologicalDensity())
            .append("rheologicalModel", getRheologicalModel())
            .append("plasticViscosity", getPlasticViscosity())
            .append("dynamicShear", getDynamicShear())
            .append("consistencyCoefficient", getConsistencyCoefficient())
            .append("fluidityIndex", getFluidityIndex())
            .append("newtonianViscosity", getNewtonianViscosity())
            .toString();
    }
}
