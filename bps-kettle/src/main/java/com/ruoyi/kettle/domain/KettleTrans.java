package com.ruoyi.kettle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转换对象 kettle_trans
 * 
 * @author kone
 * @date 2021-07-14
 */
public class KettleTrans extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 转换名称 */
    @Excel(name = "转换名称")
    private String transName;

    /** 转换描述 */
    @Excel(name = "转换描述")
    private String transDescription;

    /**  */
    private Date createdTime;

    /**  */
    private String createdBy;

    /** 转换类型(file,ftp,sf) */
    private String transType;

    /** 路径 */
    @Excel(name = "路径")
    private String transPath;

    /** 所属资源库id */
    @Excel(name = "所属资源库id")
    private Long transRepositoryId;

    /** 日志级别 */
    @Excel(name = "日志级别")
    private String transLogLevel;

    /** 状态 */
    @Excel(name = "状态")
    private String transStatus;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDel;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer isMonitorEnabled;

    /** 保留备用 */
    private String tplKey;

    /** 可执行角色key,用+号拼接 */
    @Excel(name = "可执行角色key")
    private String roleKey;

    @Excel(name = "最后一次成功时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastSucceedTime;

    public Date getLastSucceedTime() {
        return lastSucceedTime;
    }

    public void setLastSucceedTime(Date lastSucceedTime) {
        this.lastSucceedTime = lastSucceedTime;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTransName(String transName) 
    {
        this.transName = transName;
    }

    public String getTransName() 
    {
        return transName;
    }
    public void setTransDescription(String transDescription) 
    {
        this.transDescription = transDescription;
    }

    public String getTransDescription() 
    {
        return transDescription;
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
    public void setTransType(String transType) 
    {
        this.transType = transType;
    }

    public String getTransType() 
    {
        return transType;
    }
    public void setTransPath(String transPath) 
    {
        this.transPath = transPath;
    }

    public String getTransPath() 
    {
        return transPath;
    }
    public void setTransRepositoryId(Long transRepositoryId) 
    {
        this.transRepositoryId = transRepositoryId;
    }

    public Long getTransRepositoryId() 
    {
        return transRepositoryId;
    }
    public void setTransLogLevel(String transLogLevel) 
    {
        this.transLogLevel = transLogLevel;
    }

    public String getTransLogLevel() 
    {
        return transLogLevel;
    }
    public void setTransStatus(String transStatus) 
    {
        this.transStatus = transStatus;
    }

    public String getTransStatus() 
    {
        return transStatus;
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
    public void setTplKey(String tplKey) 
    {
        this.tplKey = tplKey;
    }

    public String getTplKey() 
    {
        return tplKey;
    }
    public void setRoleKey(String roleKey) 
    {
        this.roleKey = roleKey;
    }

    public String getRoleKey() 
    {
        return roleKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("transName", getTransName())
            .append("transDescription", getTransDescription())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("createdBy", getCreatedBy())
            .append("updateBy", getUpdateBy())
            .append("transType", getTransType())
            .append("transPath", getTransPath())
            .append("transRepositoryId", getTransRepositoryId())
            .append("transLogLevel", getTransLogLevel())
            .append("transStatus", getTransStatus())
            .append("isDel", getIsDel())
            .append("isMonitorEnabled", getIsMonitorEnabled())
            .append("tplKey", getTplKey())
            .append("roleKey", getRoleKey())
            .append("remark", getRemark())
            .toString();
    }

}
