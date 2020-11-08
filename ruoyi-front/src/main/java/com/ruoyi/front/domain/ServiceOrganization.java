package com.ruoyi.front.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务组织对象 service_organization
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public class ServiceOrganization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 营业执照图片地址（多个地址用，分隔） */
    @Excel(name = "营业执照图片地址", readConverterExp = "多=个地址用，分隔")
    private String licenseUrl;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 机构详情内容 */
    @Excel(name = "机构详情内容")
    private String content;

    /** 点击量 */
    @Excel(name = "点击量")
    private Integer hits;

    /** 状态（0:待审核，1：审核不通过，2：审核通过） */
    @Excel(name = "状态", readConverterExp = "0=:待审核，1：审核不通过，2：审核通过")
    private String auditStatus;

    /** 图片地址（多个地址用，分隔） */
    @Excel(name = "图片地址", readConverterExp = "多=个地址用，分隔")
    private String pictureUrl;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 审核者 */
    @Excel(name = "审核者")
    private String checkBy;

    /** 审核时间 */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLicenseUrl(String licenseUrl) 
    {
        this.licenseUrl = licenseUrl;
    }

    public String getLicenseUrl() 
    {
        return licenseUrl;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setHits(Integer hits) 
    {
        this.hits = hits;
    }

    public Integer getHits() 
    {
        return hits;
    }
    public void setAuditStatus(String auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() 
    {
        return auditStatus;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() 
    {
        return pictureUrl;
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
    public void setCheckBy(String checkBy) 
    {
        this.checkBy = checkBy;
    }

    public String getCheckBy() 
    {
        return checkBy;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("contacts", getContacts())
            .append("phone", getPhone())
            .append("licenseUrl", getLicenseUrl())
            .append("title", getTitle())
            .append("introduction", getIntroduction())
            .append("content", getContent())
            .append("hits", getHits())
            .append("auditStatus", getAuditStatus())
            .append("pictureUrl", getPictureUrl())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("checkBy", getCheckBy())
            .append("checkTime", getCheckTime())
            .append("remark", getRemark())
            .toString();
    }
}
