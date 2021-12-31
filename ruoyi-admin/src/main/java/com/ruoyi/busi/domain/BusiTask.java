package com.ruoyi.busi.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产任务对象 busi_task
 * 
 * @author WangCL
 * @date 2021-12-30
 */
public class BusiTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private String id;

    /** 订单ID */
    private String orderId;

    /** 订单名称 */
    @Excel(name = "订单名称")
    private String orderName;

    /** 产线ID */
    private String prisonLineId;

    /** 产线名称 */
    @Excel(name = "产线名称")
    private String lineName;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 目标值 */
    @Excel(name = "目标值")
    private Long targetAmount;

    /** 完成值 */
    @Excel(name = "完成值")
    private Long completedAmount;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String status;

    /** 产品子任务信息 */
    private List<BusiSubTask> busiSubTaskList;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Long getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Long targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Long getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(Long completedAmount) {
        this.completedAmount = completedAmount;
    }

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
    public void setPrisonLineId(String prisonLineId) 
    {
        this.prisonLineId = prisonLineId;
    }

    public String getPrisonLineId() 
    {
        return prisonLineId;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<BusiSubTask> getBusiSubTaskList()
    {
        return busiSubTaskList;
    }

    public void setBusiSubTaskList(List<BusiSubTask> busiSubTaskList)
    {
        this.busiSubTaskList = busiSubTaskList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("prisonLineId", getPrisonLineId())
            .append("taskName", getTaskName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("busiSubTaskList", getBusiSubTaskList())
            .toString();
    }
}
