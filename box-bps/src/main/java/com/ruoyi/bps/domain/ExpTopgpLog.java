package com.ruoyi.bps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ERP订阅推送日志对象 exp_topgp_log
 * 
 * @author Bo
 * @date 2021-08-11
 */
public class ExpTopgpLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** SID */
    private Long sid;

    /** 请求ID */
    @Excel(name = "请求ID")
    private String requestId;

    /** 请求类型（FromTopgp：ERP请求订阅、ToTopgp：Java推送签收指令) */
    @Excel(name = "请求类型", readConverterExp = "请求类型（FromTopgp：ERP请求订阅、ToTopgp：Java推送签收指令)")
    private String requestType;

    /** 快递单 */
    @Excel(name = "快递单")
    private String expressNum;

    /** 出货单号 */
    @Excel(name = "出货单号")
    private String deliveryNum;

    /** 请求报文 */
    @Excel(name = "请求报文")
    private String requestStr;

    /** 请求时间 */
    @Excel(name = "请求时间")
    private String requestTime;

    /** 返回code */
    @Excel(name = "返回code")
    private String responseCode;

    /** 返回报文 */
    @Excel(name = "返回报文")
    private String responseStr;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setRequestId(String requestId) 
    {
        this.requestId = requestId;
    }

    public String getRequestId() 
    {
        return requestId;
    }
    public void setRequestType(String requestType) 
    {
        this.requestType = requestType;
    }

    public String getRequestType() 
    {
        return requestType;
    }
    public void setExpressNum(String expressNum)
    {
        this.expressNum = expressNum;
    }

    public String getExpressNum()
    {
        return expressNum;
    }
    public void setDeliveryNum(String deliveryNum)
    {
        this.deliveryNum = deliveryNum;
    }

    public String getDeliveryNum()
    {
        return deliveryNum;
    }
    public void setRequestStr(String requestStr) 
    {
        this.requestStr = requestStr;
    }

    public String getRequestStr() 
    {
        return requestStr;
    }
    public void setRequestTime(String requestTime) 
    {
        this.requestTime = requestTime;
    }

    public String getRequestTime() 
    {
        return requestTime;
    }
    public void setResponseCode(String responseCode) 
    {
        this.responseCode = responseCode;
    }

    public String getResponseCode() 
    {
        return responseCode;
    }
    public void setResponseStr(String responseStr) 
    {
        this.responseStr = responseStr;
    }

    public String getResponseStr() 
    {
        return responseStr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("requestId", getRequestId())
            .append("requestType", getRequestType())
            .append("expressNum", getExpressNum())
            .append("deliveryNum", getDeliveryNum())
            .append("requestStr", getRequestStr())
            .append("requestTime", getRequestTime())
            .append("responseCode", getResponseCode())
            .append("responseStr", getResponseStr())
            .toString();
    }
}
