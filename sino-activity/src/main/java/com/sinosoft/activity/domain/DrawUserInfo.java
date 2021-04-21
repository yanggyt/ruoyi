package com.sinosoft.activity.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息收集页面对象 draw_user_info
 *
 * @author xlh
 * @date 2021-04-20
 *
 */
public class DrawUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long awarDrecordId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 代理人姓名 */
    @Excel(name = "代理人姓名")
    private String agentName;

    /** 代理人手机号 */
    @Excel(name = "代理人手机号")
    private String agentMobile;

    /** 代理人性别 */
    @Excel(name = "代理人性别")
    private String agentGender;

    /** 代理人工号 */
    @Excel(name = "代理人工号")
    private String agentNo;

    public void setAwarDrecordId(Long awarDrecordId)
    {
        this.awarDrecordId = awarDrecordId;
    }

    public Long getAwarDrecordId()
    {
        return awarDrecordId;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("awarDrecordId", getAwarDrecordId())
                .append("userName", getUserName())
                .append("mobile", getMobile())
                .append("gender", getGender())
                .append("address", getAddress())
                .append("agentName", getAgentName())
                .append("agentMobile", getAgentMobile())
                .append("agentGender", getAgentGender())
                .append("agentNo", getAgentNo())
                .toString();
    }

}
