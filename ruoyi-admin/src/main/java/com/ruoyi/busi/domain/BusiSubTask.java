package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品子任务对象 busi_sub_task
 * 
 * @author WangCL
 * @date 2021-12-30
 */
public class BusiSubTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private Long id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 产品需求ID */
    @Excel(name = "产品需求ID")
    private String productRequireId;

    /** 目标数量 */
    @Excel(name = "目标数量")
    private Long targetAmount;

    /** 完成数量 */
    @Excel(name = "完成数量")
    private Long completedAmount;

    /** 完成状态 */
    @Excel(name = "完成状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setProductRequireId(String productRequireId) 
    {
        this.productRequireId = productRequireId;
    }

    public String getProductRequireId() 
    {
        return productRequireId;
    }
    public void setTargetAmount(Long targetAmount) 
    {
        this.targetAmount = targetAmount;
    }

    public Long getTargetAmount() 
    {
        return targetAmount;
    }
    public void setCompletedAmount(Long completedAmount) 
    {
        this.completedAmount = completedAmount;
    }

    public Long getCompletedAmount() 
    {
        return completedAmount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("productRequireId", getProductRequireId())
            .append("targetAmount", getTargetAmount())
            .append("completedAmount", getCompletedAmount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
