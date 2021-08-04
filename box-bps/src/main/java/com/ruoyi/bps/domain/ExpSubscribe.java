package com.ruoyi.bps.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 快递订阅对象 exp_subscribe
 * 
 * @author box
 * @date 2021-05-20
 */
public class ExpSubscribe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** SID */
    private Long sid;

    /** 快递公司编码 */
    @Excel(name = "快递公司编码")
    private String company;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String number;

    /** 收/寄件人电话 */
    @Excel(name = "收/寄件人电话")
    private String phone;

    /** 盐 */
    @Excel(name = "盐")
    private String salt;

    /** 订阅时间 */
    @Excel(name = "订阅时间")
    private String subscribeTime;

    /** 订阅结果 */
    @Excel(name = "订阅结果")
    private String result;

    /** 返回码 */
    @Excel(name = "返回码")
    private String returnCode;

    /** 返回消息 */
    @Excel(name = "返回消息")
    private String message;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setSalt(String salt) 
    {
        this.salt = salt;
    }

    public String getSalt() 
    {
        return salt;
    }
    public void setSubscribeTime(String subscribeTime) 
    {
        this.subscribeTime = subscribeTime;
    }

    public String getSubscribeTime() 
    {
        return subscribeTime;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setReturnCode(String returnCode) 
    {
        this.returnCode = returnCode;
    }

    public String getReturnCode() 
    {
        return returnCode;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("company", getCompany())
            .append("number", getNumber())
            .append("phone", getPhone())
            .append("salt", getSalt())
            .append("subscribeTime", getSubscribeTime())
            .append("result", getResult())
            .append("returnCode", getReturnCode())
            .append("message", getMessage())
            .toString();
    }
}
