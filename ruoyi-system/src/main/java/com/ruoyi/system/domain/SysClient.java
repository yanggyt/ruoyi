package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户信息对象 sys_client
 * 
 * @author ruoyi
 * @date 2022-04-23
 */
public class SysClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long clientId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 客户key */
    @Excel(name = "客户key")
    private Long clientKey;

    /** 客户昵称 */
    @Excel(name = "客户昵称")
    private String clientName;

    /** 客户类型（1-内部用户 2-外部用户） */
    @Excel(name = "客户类型", readConverterExp = "1=-内部用户,2=-外部用户")
    private String clientType;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    /** 密码最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "密码最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pwdUpdateDate;

    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setClientKey(Long clientKey) 
    {
        this.clientKey = clientKey;
    }

    public Long getClientKey() 
    {
        return clientKey;
    }
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }
    public void setClientType(String clientType) 
    {
        this.clientType = clientType;
    }

    public String getClientType() 
    {
        return clientType;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }
    public void setPwdUpdateDate(Date pwdUpdateDate) 
    {
        this.pwdUpdateDate = pwdUpdateDate;
    }

    public Date getPwdUpdateDate() 
    {
        return pwdUpdateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clientId", getClientId())
            .append("deptId", getDeptId())
            .append("clientKey", getClientKey())
            .append("clientName", getClientName())
            .append("clientType", getClientType())
            .append("phonenumber", getPhonenumber())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("pwdUpdateDate", getPwdUpdateDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
