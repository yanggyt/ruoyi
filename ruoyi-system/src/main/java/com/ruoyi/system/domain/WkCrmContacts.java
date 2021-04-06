package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 联系人对象 wk_crm_contacts
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long contactsId;

    /** 联系人名称 */
    @Excel(name = "联系人名称")
    private String name;

    /** 下次联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextTime;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 职务 */
    @Excel(name = "职务")
    private String post;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createUserId;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long ownerUserId;

    /** 批次 */
    @Excel(name = "批次")
    private String batchId;

    /** 最后跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastTime;

    public void setContactsId(Long contactsId) 
    {
        this.contactsId = contactsId;
    }

    public Long getContactsId() 
    {
        return contactsId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNextTime(Date nextTime) 
    {
        this.nextTime = nextTime;
    }

    public Date getNextTime() 
    {
        return nextTime;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPost(String post) 
    {
        this.post = post;
    }

    public String getPost() 
    {
        return post;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }
    public void setOwnerUserId(Long ownerUserId) 
    {
        this.ownerUserId = ownerUserId;
    }

    public Long getOwnerUserId() 
    {
        return ownerUserId;
    }
    public void setBatchId(String batchId) 
    {
        this.batchId = batchId;
    }

    public String getBatchId() 
    {
        return batchId;
    }
    public void setLastTime(Date lastTime) 
    {
        this.lastTime = lastTime;
    }

    public Date getLastTime() 
    {
        return lastTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactsId", getContactsId())
            .append("name", getName())
            .append("nextTime", getNextTime())
            .append("mobile", getMobile())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("post", getPost())
            .append("customerId", getCustomerId())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("createUserId", getCreateUserId())
            .append("ownerUserId", getOwnerUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("batchId", getBatchId())
            .append("lastTime", getLastTime())
            .toString();
    }
}
