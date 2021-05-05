package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 流变学设计数据对象 rheological_design
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public class RheologicalDesign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流变学设计编号 */
    private Long rheologicalDesignNumber;

    /** 井编号 */
    private Long oilWellId;

    /** 顶替排量设计 */
    @Excel(name = "顶替排量设计")
    private Long displacementDisplacementDesign;

    /** 关注点（3292）压力 */
    @Excel(name = "关注点", readConverterExp = "3=292")
    private Long focusOn3292Pressure;

    /** 关注点（3342）压力 */
    @Excel(name = "关注点", readConverterExp = "3=342")
    private Long focusOn3342Pressure;

    /** 全井静液压差 */
    @Excel(name = "全井静液压差")
    private Long overallWellStatichydraulicdifference;

    /** 环空循环压耗 */
    @Excel(name = "环空循环压耗")
    private Long annularCirculationpressureloss;

    /** 管内循环压耗 */
    @Excel(name = "管内循环压耗")
    private Long circulatingPressurelossinthetube;

    /** 全井循环压耗 */
    @Excel(name = "全井循环压耗")
    private Long circulatingPressurelossthroughoutthewell;

    /** 最终顶替泵压 */
    @Excel(name = "最终顶替泵压")
    private Long finalDisplacementpumppressure;

    public void setRheologicalDesignNumber(Long rheologicalDesignNumber) 
    {
        this.rheologicalDesignNumber = rheologicalDesignNumber;
    }

    public Long getRheologicalDesignNumber() 
    {
        return rheologicalDesignNumber;
    }
    public void setOilWellId(Long oilWellId) 
    {
        this.oilWellId = oilWellId;
    }

    public Long getOilWellId() 
    {
        return oilWellId;
    }
    public void setDisplacementDisplacementDesign(Long displacementDisplacementDesign) 
    {
        this.displacementDisplacementDesign = displacementDisplacementDesign;
    }

    public Long getDisplacementDisplacementDesign() 
    {
        return displacementDisplacementDesign;
    }
    public void setFocusOn3292Pressure(Long focusOn3292Pressure) 
    {
        this.focusOn3292Pressure = focusOn3292Pressure;
    }

    public Long getFocusOn3292Pressure() 
    {
        return focusOn3292Pressure;
    }
    public void setFocusOn3342Pressure(Long focusOn3342Pressure) 
    {
        this.focusOn3342Pressure = focusOn3342Pressure;
    }

    public Long getFocusOn3342Pressure() 
    {
        return focusOn3342Pressure;
    }
    public void setOverallWellStatichydraulicdifference(Long overallWellStatichydraulicdifference) 
    {
        this.overallWellStatichydraulicdifference = overallWellStatichydraulicdifference;
    }

    public Long getOverallWellStatichydraulicdifference() 
    {
        return overallWellStatichydraulicdifference;
    }
    public void setAnnularCirculationpressureloss(Long annularCirculationpressureloss) 
    {
        this.annularCirculationpressureloss = annularCirculationpressureloss;
    }

    public Long getAnnularCirculationpressureloss() 
    {
        return annularCirculationpressureloss;
    }
    public void setCirculatingPressurelossinthetube(Long circulatingPressurelossinthetube) 
    {
        this.circulatingPressurelossinthetube = circulatingPressurelossinthetube;
    }

    public Long getCirculatingPressurelossinthetube() 
    {
        return circulatingPressurelossinthetube;
    }
    public void setCirculatingPressurelossthroughoutthewell(Long circulatingPressurelossthroughoutthewell) 
    {
        this.circulatingPressurelossthroughoutthewell = circulatingPressurelossthroughoutthewell;
    }

    public Long getCirculatingPressurelossthroughoutthewell() 
    {
        return circulatingPressurelossthroughoutthewell;
    }
    public void setFinalDisplacementpumppressure(Long finalDisplacementpumppressure) 
    {
        this.finalDisplacementpumppressure = finalDisplacementpumppressure;
    }

    public Long getFinalDisplacementpumppressure() 
    {
        return finalDisplacementpumppressure;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rheologicalDesignNumber", getRheologicalDesignNumber())
            .append("oilWellId", getOilWellId())
            .append("displacementDisplacementDesign", getDisplacementDisplacementDesign())
            .append("focusOn3292Pressure", getFocusOn3292Pressure())
            .append("focusOn3342Pressure", getFocusOn3342Pressure())
            .append("overallWellStatichydraulicdifference", getOverallWellStatichydraulicdifference())
            .append("annularCirculationpressureloss", getAnnularCirculationpressureloss())
            .append("circulatingPressurelossinthetube", getCirculatingPressurelossinthetube())
            .append("circulatingPressurelossthroughoutthewell", getCirculatingPressurelossthroughoutthewell())
            .append("finalDisplacementpumppressure", getFinalDisplacementpumppressure())
            .toString();
    }
}
