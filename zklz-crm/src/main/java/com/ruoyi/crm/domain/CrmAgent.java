package com.ruoyi.crm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 代理商管理对象 crm_agent
 * 
 * @author wendukeji.cn
 * @date 2021-06-30
 */
public class CrmAgent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据编号 */
    private Long id;

    /** 代理商名称 */
    @Excel(name = "代理商名称")
    private String agentName;

    /** 代理商类型 */
    @Excel(name = "代理商类型")
    private String agentType;

    /** 代理商级别 */
    @Excel(name = "代理商级别")
    private String agentLevel;

    /** 销售地址 */
    @Excel(name = "销售地址")
    private String saleAddr;

    /** 授权开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "授权开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date authStartTime;

    /** 授权结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "授权结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date authEndTime;

    /** 授权有效性(0:失效；1:有效) */
    @Excel(name = "授权有效性(0:失效；1:有效)")
    private String authStatus;

    /** 首次提货量 */
    @Excel(name = "首次提货量")
    private Long firstSale;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String agentCode;

    /** 天眼查地址 */
    @Excel(name = "天眼查地址")
    private String tycUri;

    /** 成立日期 */
    @Excel(name = "成立日期")
    private String createDate;

    /** 注册资本(万元) */
    @Excel(name = "注册资本(万元)")
    private Long capital;

    /** 员工数量 */
    @Excel(name = "员工数量")
    private Long memberNum;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalPerson;

    /** 第一联系人 */
    @Excel(name = "第一联系人")
    private String contactName;

    /** 第一联系人电话 */
    @Excel(name = "第一联系人电话")
    private String contactTel;

    /** 第二联系人 */
    @Excel(name = "第二联系人")
    private String contactName2;

    /** 第二联系人电话 */
    @Excel(name = "第二联系人电话")
    private String contactTel2;

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
    public void setAgentName(String agentName) 
    {
        this.agentName = agentName;
    }

    public String getAgentName() 
    {
        return agentName;
    }
    public void setAgentType(String agentType) 
    {
        this.agentType = agentType;
    }

    public String getAgentType() 
    {
        return agentType;
    }
    public void setAgentLevel(String agentLevel) 
    {
        this.agentLevel = agentLevel;
    }

    public String getAgentLevel() 
    {
        return agentLevel;
    }
    public void setSaleAddr(String saleAddr) 
    {
        this.saleAddr = saleAddr;
    }

    public String getSaleAddr() 
    {
        return saleAddr;
    }
    public void setAuthStartTime(Date authStartTime) 
    {
        this.authStartTime = authStartTime;
    }

    public Date getAuthStartTime() 
    {
        return authStartTime;
    }
    public void setAuthEndTime(Date authEndTime) 
    {
        this.authEndTime = authEndTime;
    }

    public Date getAuthEndTime() 
    {
        return authEndTime;
    }
    public void setAuthStatus(String authStatus) 
    {
        this.authStatus = authStatus;
    }

    public String getAuthStatus() 
    {
        return authStatus;
    }
    public void setFirstSale(Long firstSale) 
    {
        this.firstSale = firstSale;
    }

    public Long getFirstSale() 
    {
        return firstSale;
    }
    public void setAgentCode(String agentCode) 
    {
        this.agentCode = agentCode;
    }

    public String getAgentCode() 
    {
        return agentCode;
    }
    public void setTycUri(String tycUri) 
    {
        this.tycUri = tycUri;
    }

    public String getTycUri() 
    {
        return tycUri;
    }
    public void setCreateDate(String createDate) 
    {
        this.createDate = createDate;
    }

    public String getCreateDate() 
    {
        return createDate;
    }
    public void setCapital(Long capital) 
    {
        this.capital = capital;
    }

    public Long getCapital() 
    {
        return capital;
    }
    public void setMemberNum(Long memberNum) 
    {
        this.memberNum = memberNum;
    }

    public Long getMemberNum() 
    {
        return memberNum;
    }
    public void setLegalPerson(String legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() 
    {
        return legalPerson;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactTel(String contactTel) 
    {
        this.contactTel = contactTel;
    }

    public String getContactTel() 
    {
        return contactTel;
    }
    public void setContactName2(String contactName2) 
    {
        this.contactName2 = contactName2;
    }

    public String getContactName2() 
    {
        return contactName2;
    }
    public void setContactTel2(String contactTel2) 
    {
        this.contactTel2 = contactTel2;
    }

    public String getContactTel2() 
    {
        return contactTel2;
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
            .append("agentName", getAgentName())
            .append("agentType", getAgentType())
            .append("agentLevel", getAgentLevel())
            .append("saleAddr", getSaleAddr())
            .append("authStartTime", getAuthStartTime())
            .append("authEndTime", getAuthEndTime())
            .append("authStatus", getAuthStatus())
            .append("firstSale", getFirstSale())
            .append("agentCode", getAgentCode())
            .append("tycUri", getTycUri())
            .append("createDate", getCreateDate())
            .append("capital", getCapital())
            .append("memberNum", getMemberNum())
            .append("legalPerson", getLegalPerson())
            .append("contactName", getContactName())
            .append("contactTel", getContactTel())
            .append("contactName2", getContactName2())
            .append("contactTel2", getContactTel2())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
