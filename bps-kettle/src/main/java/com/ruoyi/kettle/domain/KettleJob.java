package com.ruoyi.kettle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业调度对象 kettle_job
 * 
 * @author kone
 * @date 2021-07-22
 */
public class KettleJob extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**  */
    @Excel(name = "")
    private String createdBy;

    /** 作业名称 */
    @Excel(name = "作业名称")
    private String jobName;

    /** 描述 */
    @Excel(name = "描述")
    private String jobDescription;

    /** 作业类型(file,ftp,sf) */
    @Excel(name = "作业类型(file,ftp,sf)")
    private String jobType;

    /** 路径 */
    @Excel(name = "路径")
    private String jobPath;

    /** 资源库id */
    @Excel(name = "资源库id")
    private Long jobRepositoryId;

    /** 日志级别 */
    @Excel(name = "日志级别")
    private String jobLogLevel;

    /** 状态 */
    @Excel(name = "状态")
    private String jobStatus;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDel;

    /** 是否监控 */
    @Excel(name = "是否监控")
    private Integer isMonitorEnabled;

    /** 可执行角色key,用+号拼接 */
    @Excel(name = "可执行角色key,用+号拼接")
    private String roleKey;

    /**  */
    @Excel(name = "")
    private String tplKey;
    @Excel(name = "最后一次成功时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastSucceedTime;

    public Date getLastSucceedTime() {
        return lastSucceedTime;
    }

    public void setLastSucceedTime(Date lastSucceedTime) {
        this.lastSucceedTime = lastSucceedTime;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setJobName(String jobName) 
    {
        this.jobName = jobName;
    }

    public String getJobName() 
    {
        return jobName;
    }
    public void setJobDescription(String jobDescription) 
    {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() 
    {
        return jobDescription;
    }
    public void setJobType(String jobType) 
    {
        this.jobType = jobType;
    }

    public String getJobType() 
    {
        return jobType;
    }
    public void setJobPath(String jobPath) 
    {
        this.jobPath = jobPath;
    }

    public String getJobPath() 
    {
        return jobPath;
    }
    public void setJobRepositoryId(Long jobRepositoryId) 
    {
        this.jobRepositoryId = jobRepositoryId;
    }

    public Long getJobRepositoryId() 
    {
        return jobRepositoryId;
    }
    public void setJobLogLevel(String jobLogLevel) 
    {
        this.jobLogLevel = jobLogLevel;
    }

    public String getJobLogLevel() 
    {
        return jobLogLevel;
    }
    public void setJobStatus(String jobStatus) 
    {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() 
    {
        return jobStatus;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }
    public void setIsMonitorEnabled(Integer isMonitorEnabled)
    {
        this.isMonitorEnabled = isMonitorEnabled;
    }

    public Integer getIsMonitorEnabled()
    {
        return isMonitorEnabled;
    }
    public void setRoleKey(String roleKey) 
    {
        this.roleKey = roleKey;
    }

    public String getRoleKey() 
    {
        return roleKey;
    }
    public void setTplKey(String tplKey) 
    {
        this.tplKey = tplKey;
    }

    public String getTplKey() 
    {
        return tplKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("createdBy", getCreatedBy())
            .append("updateBy", getUpdateBy())
            .append("jobName", getJobName())
            .append("jobDescription", getJobDescription())
            .append("jobType", getJobType())
            .append("jobPath", getJobPath())
            .append("jobRepositoryId", getJobRepositoryId())
            .append("jobLogLevel", getJobLogLevel())
            .append("jobStatus", getJobStatus())
            .append("isDel", getIsDel())
            .append("isMonitorEnabled", getIsMonitorEnabled())
            .append("roleKey", getRoleKey())
            .append("tplKey", getTplKey())
            .toString();
    }
}
