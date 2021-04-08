package com.sinosoft.activity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动用户信息对象 act_page_config_userinfo
 * 
 * @author dy
 * @date 2021-04-08
 */
public class ActPageConfigUserinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;

    /** 活动代码 */
    @Excel(name = "活动代码")
    private String actCode;

    /**标题  */
    @Excel(name = "标题")
    private String title;

    /**描述  */
    @Excel(name = "描述")
    private String description;

    /**图片  */
    @Excel(name = "图片")
    private String bgImg;

    /**按钮  */
    @Excel(name = "按钮")
    private String btnText;

    /**按钮连接  */
    @Excel(name = "按钮连接")
    private String btnLink;

    /**协议内容  */
    @Excel(name = "协议内容")
    private String agreement;

    /**姓名  */
    @Excel(name = "姓名")
    private String userName;

    /**手机号  */
    @Excel(name = "手机号")
    private String mobile;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /**地址  */
    @Excel(name = "地址")
    private String address;

    /**代理人姓名 */
    @Excel(name = "代理人姓名")
    private String agentName;

    /**代理人手机号  */
    @Excel(name = "代理人手机号")
    private String agentMobile;

    /**代理人性别  */
    @Excel(name = "代理人性别")
    private String agentGender;

    /** 代理人工号 */
    @Excel(name = "代理人工号")
    private String agentNo;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setActCode(String actCode) 
    {
        this.actCode = actCode;
    }

    public String getActCode() 
    {
        return actCode;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setBgImg(String bgImg) 
    {
        this.bgImg = bgImg;
    }

    public String getBgImg() 
    {
        return bgImg;
    }
    public void setBtnText(String btnText) 
    {
        this.btnText = btnText;
    }

    public String getBtnText() 
    {
        return btnText;
    }
    public void setBtnLink(String btnLink) 
    {
        this.btnLink = btnLink;
    }

    public String getBtnLink() 
    {
        return btnLink;
    }
    public void setAgreement(String agreement) 
    {
        this.agreement = agreement;
    }

    public String getAgreement() 
    {
        return agreement;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAgentName(String agentName) 
    {
        this.agentName = agentName;
    }

    public String getAgentName() 
    {
        return agentName;
    }
    public void setAgentMobile(String agentMobile) 
    {
        this.agentMobile = agentMobile;
    }

    public String getAgentMobile() 
    {
        return agentMobile;
    }
    public void setAgentGender(String agentGender) 
    {
        this.agentGender = agentGender;
    }

    public String getAgentGender() 
    {
        return agentGender;
    }
    public void setAgentNo(String agentNo) 
    {
        this.agentNo = agentNo;
    }

    public String getAgentNo() 
    {
        return agentNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("actCode", getActCode())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("bgImg", getBgImg())
            .append("btnText", getBtnText())
            .append("btnLink", getBtnLink())
            .append("agreement", getAgreement())
            .append("userName", getUserName())
            .append("mobile", getMobile())
            .append("gender", getGender())
            .append("address", getAddress())
            .append("agentName", getAgentName())
            .append("agentMobile", getAgentMobile())
            .append("agentGender", getAgentGender())
            .append("agentNo", getAgentNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
