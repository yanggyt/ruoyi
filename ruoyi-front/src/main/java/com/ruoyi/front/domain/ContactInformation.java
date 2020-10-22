package com.ruoyi.front.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 联系方式对象 contact_information
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
public class ContactInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 机构地址 */
    @Excel(name = "机构地址")
    private String address;

    /** 服务电话 */
    @Excel(name = "服务电话")
    private String servicePhone;

    /** 监督电话 */
    @Excel(name = "监督电话")
    private String supervisePhone;

    /** 监督部门 */
    @Excel(name = "监督部门")
    private String superviseDept;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 服务时间 */
    @Excel(name = "服务时间")
    private String serviceDate;

    /** 版权所有 */
    @Excel(name = "版权所有")
    private String copyright;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setServicePhone(String servicePhone) 
    {
        this.servicePhone = servicePhone;
    }

    public String getServicePhone() 
    {
        return servicePhone;
    }
    public void setSupervisePhone(String supervisePhone) 
    {
        this.supervisePhone = supervisePhone;
    }

    public String getSupervisePhone() 
    {
        return supervisePhone;
    }
    public void setSuperviseDept(String superviseDept) 
    {
        this.superviseDept = superviseDept;
    }

    public String getSuperviseDept() 
    {
        return superviseDept;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setServiceDate(String serviceDate) 
    {
        this.serviceDate = serviceDate;
    }

    public String getServiceDate() 
    {
        return serviceDate;
    }
    public void setCopyright(String copyright) 
    {
        this.copyright = copyright;
    }

    public String getCopyright() 
    {
        return copyright;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("servicePhone", getServicePhone())
            .append("supervisePhone", getSupervisePhone())
            .append("superviseDept", getSuperviseDept())
            .append("email", getEmail())
            .append("serviceDate", getServiceDate())
            .append("copyright", getCopyright())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
