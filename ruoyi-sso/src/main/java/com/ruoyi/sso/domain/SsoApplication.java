package com.ruoyi.sso.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单点登录应用对象 sso_application
 * 
 * @author shixueshu
 * @date 2020-03-23
 */
public class SsoApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用id */
    private Long appId;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appName;

    /** 应用描述 */
    @Excel(name = "应用描述")
    private String appDesc;

    /** 应用标识 */
    @Excel(name = "应用标识")
    private String appKey;

    /** 应用密钥 */
    @Excel(name = "应用密钥")
    private String appSecret;

    /** 应用回调地址 */
    @Excel(name = "应用回调地址")
    private String appCallBackUrl;

    /** 是否启用：0禁用，1启用 */
    @Excel(name = "是否启用：0禁用，1启用")
    private String status;

    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setAppDesc(String appDesc) 
    {
        this.appDesc = appDesc;
    }

    public String getAppDesc() 
    {
        return appDesc;
    }
    public void setAppKey(String appKey) 
    {
        this.appKey = appKey;
    }

    public String getAppKey() 
    {
        return appKey;
    }
    public void setAppSecret(String appSecret) 
    {
        this.appSecret = appSecret;
    }

    public String getAppSecret() 
    {
        return appSecret;
    }
    public void setAppCallBackUrl(String appCallBackUrl) 
    {
        this.appCallBackUrl = appCallBackUrl;
    }

    public String getAppCallBackUrl() 
    {
        return appCallBackUrl;
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
            .append("appName", getAppName())
            .append("appDesc", getAppDesc())
            .append("appKey", getAppKey())
            .append("appSecret", getAppSecret())
            .append("appCallBackUrl", getAppCallBackUrl())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
