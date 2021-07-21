package com.ruoyi.bps.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Excel批量快递查询对象 exp_import_query
 * 
 * @author Bo
 * @date 2021-07-21
 */
public class ExpImportQuery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** sid */
    private Long sid;

    /** 查询时间 */
    @Excel(name = "查询时间")
    private String queryTime;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String queryUserId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String queryUserName;

    /** 查询IP */
    @Excel(name = "查询IP")
    private String queryIp;

    /** 完成时间 */
    @Excel(name = "完成时间")
    private String finishTime;

    /** 完成状态 */
    @Excel(name = "完成状态")
    private String status;

    /** 运单总量 */
    @Excel(name = "运单总量")
    private String queryQty;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setQueryTime(String queryTime) 
    {
        this.queryTime = queryTime;
    }

    public String getQueryTime() 
    {
        return queryTime;
    }
    public void setQueryUserId(String queryUserId) 
    {
        this.queryUserId = queryUserId;
    }

    public String getQueryUserId() 
    {
        return queryUserId;
    }
    public void setQueryUserName(String queryUserName) 
    {
        this.queryUserName = queryUserName;
    }

    public String getQueryUserName() 
    {
        return queryUserName;
    }
    public void setQueryIp(String queryIp) 
    {
        this.queryIp = queryIp;
    }

    public String getQueryIp() 
    {
        return queryIp;
    }
    public void setFinishTime(String finishTime) 
    {
        this.finishTime = finishTime;
    }

    public String getFinishTime() 
    {
        return finishTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setQueryQty(String queryQty) 
    {
        this.queryQty = queryQty;
    }

    public String getQueryQty() 
    {
        return queryQty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("queryTime", getQueryTime())
            .append("queryUserId", getQueryUserId())
            .append("queryUserName", getQueryUserName())
            .append("queryIp", getQueryIp())
            .append("finishTime", getFinishTime())
            .append("status", getStatus())
            .append("queryQty", getQueryQty())
            .toString();
    }
}
