package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线索对象 wk_crm_leads
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public class WkCrmLeads extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long leadsId;

    /** 跟进状态 0未跟进1已跟进 */
    @Excel(name = "跟进状态 0未跟进1已跟进")
    private Long followup;

    /** 线索名称 */
    @Excel(name = "线索名称")
    private String leadsName;

    /** 下次联系时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次联系时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextTime;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createUserId;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long ownerUserId;

    /** 最后跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastTime;

    /** 最后一条跟进记录 */
    @Excel(name = "最后一条跟进记录")
    private String lastContent;

    public void setLeadsId(Long leadsId) 
    {
        this.leadsId = leadsId;
    }

    public Long getLeadsId() 
    {
        return leadsId;
    }
    public void setFollowup(Long followup) 
    {
        this.followup = followup;
    }

    public Long getFollowup() 
    {
        return followup;
    }
    public void setLeadsName(String leadsName) 
    {
        this.leadsName = leadsName;
    }

    public String getLeadsName() 
    {
        return leadsName;
    }
    public void setNextTime(Date nextTime) 
    {
        this.nextTime = nextTime;
    }

    public Date getNextTime() 
    {
        return nextTime;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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
    public void setLastTime(Date lastTime) 
    {
        this.lastTime = lastTime;
    }

    public Date getLastTime() 
    {
        return lastTime;
    }
    public void setLastContent(String lastContent) 
    {
        this.lastContent = lastContent;
    }

    public String getLastContent() 
    {
        return lastContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("leadsId", getLeadsId())
            .append("followup", getFollowup())
            .append("leadsName", getLeadsName())
            .append("nextTime", getNextTime())
            .append("telephone", getTelephone())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("createUserId", getCreateUserId())
            .append("ownerUserId", getOwnerUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("lastTime", getLastTime())
            .append("lastContent", getLastContent())
            .toString();
    }
}
