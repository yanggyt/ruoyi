package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户对象 wk_crm_customer
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 下次联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextTime;

    /** 成交状态 0 未成交 1 已成交 */
    @Excel(name = "成交状态 0 未成交 1 已成交")
    private Integer dealStatus;

    /** 成交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 网址 */
    @Excel(name = "网址")
    private String website;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createUserId;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long ownerUserId;

    /** 只读权限 */
    @Excel(name = "只读权限")
    private String roUserId;

    /** 读写权限 */
    @Excel(name = "读写权限")
    private String rwUserId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddress;

    /** 批次 比如附件批次 */
    @Excel(name = "批次 比如附件批次")
    private String batchId;

    /** 最后跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastTime;

    /** 放入公海时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "放入公海时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date poolTime;

    /** 1 分配 2 领取 */
    @Excel(name = "1 分配 2 领取")
    private Integer isReceive;

    /** 最后一条跟进记录 */
    @Excel(name = "最后一条跟进记录")
    private String lastContent;

    /** 接收到客户时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接收到客户时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    /** 进入公海前负责人id */
    @Excel(name = "进入公海前负责人id")
    private Long preOwnerUserId;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setNextTime(Date nextTime) 
    {
        this.nextTime = nextTime;
    }

    public Date getNextTime() 
    {
        return nextTime;
    }
    public void setDealStatus(Integer dealStatus) 
    {
        this.dealStatus = dealStatus;
    }

    public Integer getDealStatus() 
    {
        return dealStatus;
    }
    public void setDealTime(Date dealTime) 
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime() 
    {
        return dealTime;
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
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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
    public void setRoUserId(String roUserId) 
    {
        this.roUserId = roUserId;
    }

    public String getRoUserId() 
    {
        return roUserId;
    }
    public void setRwUserId(String rwUserId) 
    {
        this.rwUserId = rwUserId;
    }

    public String getRwUserId() 
    {
        return rwUserId;
    }
    public void setDetailAddress(String detailAddress) 
    {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() 
    {
        return detailAddress;
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
    public void setPoolTime(Date poolTime) 
    {
        this.poolTime = poolTime;
    }

    public Date getPoolTime() 
    {
        return poolTime;
    }
    public void setIsReceive(Integer isReceive) 
    {
        this.isReceive = isReceive;
    }

    public Integer getIsReceive() 
    {
        return isReceive;
    }
    public void setLastContent(String lastContent) 
    {
        this.lastContent = lastContent;
    }

    public String getLastContent() 
    {
        return lastContent;
    }
    public void setReceiveTime(Date receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() 
    {
        return receiveTime;
    }
    public void setPreOwnerUserId(Long preOwnerUserId) 
    {
        this.preOwnerUserId = preOwnerUserId;
    }

    public Long getPreOwnerUserId() 
    {
        return preOwnerUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("nextTime", getNextTime())
            .append("dealStatus", getDealStatus())
            .append("dealTime", getDealTime())
            .append("mobile", getMobile())
            .append("telephone", getTelephone())
            .append("website", getWebsite())
            .append("email", getEmail())
            .append("remark", getRemark())
            .append("createUserId", getCreateUserId())
            .append("ownerUserId", getOwnerUserId())
            .append("roUserId", getRoUserId())
            .append("rwUserId", getRwUserId())
            .append("detailAddress", getDetailAddress())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("batchId", getBatchId())
            .append("lastTime", getLastTime())
            .append("poolTime", getPoolTime())
            .append("isReceive", getIsReceive())
            .append("lastContent", getLastContent())
            .append("receiveTime", getReceiveTime())
            .append("preOwnerUserId", getPreOwnerUserId())
            .toString();
    }
}
