package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 编码申请对象 sys_app
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
public class SysApp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码申请单主键seq_sys_app.nextval */
    private Long appId;

    /** 申请编码 */
    @Excel(name = "申请编码")
    private String appCode;

    /** 编码体系ID */
    @Excel(name = "编码体系ID")
    private Long categoryId;

    /** 申请名称 */
    @Excel(name = "申请名称")
    private String appName;

    /** 状态（0正常 1通过2驳回） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=通过2驳回")
    private String status;

    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setAppCode(String appCode) 
    {
        this.appCode = appCode;
    }

    public String getAppCode() 
    {
        return appCode;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
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
            .append("appId", getAppId())
            .append("appCode", getAppCode())
            .append("categoryId", getCategoryId())
            .append("appName", getAppName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
