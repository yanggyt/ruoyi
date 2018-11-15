package com.cronie.mengyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.base.BaseEntity;

/**
 * 年度计划表 my_year_plan
 * 
 * @author cronie
 * @date 2018-11-15
 */
public class YearPlan extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 年 */
	private String year;
	/** 预期盈利 */
	private BigDecimal profit;
	/** 计划状态 */
	private Integer planStatus;
	/** 任务状态 */
	private Integer taskStatus;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private Integer creater;

	public void setYear(String year) 
	{
		this.year = year;
	}

	public String getYear() 
	{
		return year;
	}
	public void setProfit(BigDecimal profit) 
	{
		this.profit = profit;
	}

	public BigDecimal getProfit() 
	{
		return profit;
	}
	public void setPlanStatus(Integer planStatus) 
	{
		this.planStatus = planStatus;
	}

	public Integer getPlanStatus() 
	{
		return planStatus;
	}
	public void setTaskStatus(Integer taskStatus) 
	{
		this.taskStatus = taskStatus;
	}

	public Integer getTaskStatus() 
	{
		return taskStatus;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setCreater(Integer creater) 
	{
		this.creater = creater;
	}

	public Integer getCreater() 
	{
		return creater;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("year", getYear())
            .append("profit", getProfit())
            .append("planStatus", getPlanStatus())
            .append("taskStatus", getTaskStatus())
            .append("createTime", getCreateTime())
            .append("creater", getCreater())
            .toString();
    }
}
