package com.ruoyi.fq.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 封铅登记对象 fq_table
 * 
 * @author mario
 * @date 2020-07-09
 */
public class FqTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String kName;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String dName;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String dNo;

    /** 封签袋id */
    @Excel(name = "封签袋id")
    private Long pId;

    /** 封铅编码 */
    @Excel(name = "封铅编码")
    private String fNo;

    /** 加封日期 */
    @Excel(name = "加封日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 拆封日期 */
    @Excel(name = "拆封日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date splitTime;

    /** 执行人签字 */
    @Excel(name = "执行人签字")
    private String optSign;

    /** 用户签字 */
    @Excel(name = "用户签字")
    private String userSign;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 使用时间 */
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date optTime;

    /** 客户经理 */
    @Excel(name = "客户经理")
    private Long userId;

    /** 区域 */
    @Excel(name = "区域")
    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setkName(String kName) 
    {
        this.kName = kName;
    }

    public String getkName() 
    {
        return kName;
    }
    public void setdName(String dName) 
    {
        this.dName = dName;
    }

    public String getdName() 
    {
        return dName;
    }
    public void setdNo(String dNo) 
    {
        this.dNo = dNo;
    }

    public String getdNo() 
    {
        return dNo;
    }
    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
    }
    public void setfNo(String fNo) 
    {
        this.fNo = fNo;
    }

    public String getfNo() 
    {
        return fNo;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setSplitTime(Date splitTime) 
    {
        this.splitTime = splitTime;
    }

    public Date getSplitTime() 
    {
        return splitTime;
    }
    public void setOptSign(String optSign) 
    {
        this.optSign = optSign;
    }

    public String getOptSign() 
    {
        return optSign;
    }
    public void setUserSign(String userSign) 
    {
        this.userSign = userSign;
    }

    public String getUserSign() 
    {
        return userSign;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOptTime(Date optTime) 
    {
        this.optTime = optTime;
    }

    public Date getOptTime() 
    {
        return optTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("kName", getkName())
            .append("dName", getdName())
            .append("dNo", getdNo())
            .append("pId", getpId())
            .append("fNo", getfNo())
            .append("addTime", getAddTime())
            .append("splitTime", getSplitTime())
            .append("optSign", getOptSign())
            .append("userSign", getUserSign())
            .append("status", getStatus())
            .append("optTime", getOptTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .toString();
    }
}
