package com.ruoyi.bps.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 快递信息对象 expressInfo
 * 
 * @author box
 * @date 2021-05-06
 */
public class ExpressInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息 */
    @Excel(name = "消息")
    private String message;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String nu;

    /** 签收状态 */
    @Excel(name = "签收状态")
    private String ischeck;

    /** 快递公司 */
    @Excel(name = "快递公司")
    private String com;

    /** 通信状态 */
    @Excel(name = "通信状态")
    private String status;

    /** 运单详情 */
    @Excel(name = "运单详情")
    private String data;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String state;

    /** 状态标志 */
    @Excel(name = "状态标志")
    private String condition;

    /** 路由信息 */
    @Excel(name = "路由信息")
    private String routeInfo;

    /** 返回码 */
    @Excel(name = "返回码")
    private String returnCode;

    /** 返回结果 */
    @Excel(name = "返回结果")
    private String result;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setNu(String nu) 
    {
        this.nu = nu;
    }

    public String getNu() 
    {
        return nu;
    }
    public void setIscheck(String ischeck) 
    {
        this.ischeck = ischeck;
    }

    public String getIscheck() 
    {
        return ischeck;
    }
    public void setCom(String com) 
    {
        this.com = com;
    }

    public String getCom() 
    {
        return com;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setData(String data) 
    {
        this.data = data;
    }

    public String getData() 
    {
        return data;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setCondition(String condition) 
    {
        this.condition = condition;
    }

    public String getCondition() 
    {
        return condition;
    }
    public void setRouteInfo(String routeInfo) 
    {
        this.routeInfo = routeInfo;
    }

    public String getRouteInfo() 
    {
        return routeInfo;
    }
    public void setReturnCode(String returnCode) 
    {
        this.returnCode = returnCode;
    }

    public String getReturnCode() 
    {
        return returnCode;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("message", getMessage())
            .append("nu", getNu())
            .append("ischeck", getIscheck())
            .append("com", getCom())
            .append("status", getStatus())
            .append("data", getData())
            .append("state", getState())
            .append("condition", getCondition())
            .append("routeInfo", getRouteInfo())
            .append("returnCode", getReturnCode())
            .append("result", getResult())
            .append("phone", getPhone())
            .toString();
    }
}
