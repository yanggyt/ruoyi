package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单据号迭代信息对象 sys_bill_no
 * 
 * @author dalin
 * @date 2020-12-15
 */
public class SysBillNo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 期间 */
    private String fperiod;

    /** 单据标识 */
    private String billName;

    /** 迭代值 */
    @Excel(name = "迭代值")
    private String iterationValue;

    /** 下一个值 */
    @Excel(name = "下一个值")
    private String nextValue;

    public void setFperiod(String fperiod) 
    {
        this.fperiod = fperiod;
    }

    public String getFperiod() 
    {
        return fperiod;
    }
    public void setBillName(String billName) 
    {
        this.billName = billName;
    }

    public String getBillName() 
    {
        return billName;
    }
    public void setIterationValue(String iterationValue) 
    {
        this.iterationValue = iterationValue;
    }

    public String getIterationValue() 
    {
        return iterationValue;
    }
    public void setNextValue(String nextValue) 
    {
        this.nextValue = nextValue;
    }

    public String getNextValue() 
    {
        return nextValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fperiod", getFperiod())
            .append("billName", getBillName())
            .append("iterationValue", getIterationValue())
            .append("nextValue", getNextValue())
            .append("remark", getRemark())
            .toString();
    }
}
