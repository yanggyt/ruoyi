package com.ruoyi.fq.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 封铅袋对象 fq_package
 * 
 * @author mario
 * @date 2020-07-02
 */
public class FqPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String pName;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String bName;

    /** 袋内数量 */
    @Excel(name = "袋内数量")
    private Long num;

    public Long getNumUse() {
        return numUse;
    }

    public void setNumUse(Long numUse) {
        this.numUse = numUse;
    }

    /** 使用数量 */
    @Excel(name = "使用数量")
    private Long numUse;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 起始编码 */
    @Excel(name = "起始编码")
    private String startNo;

    /** 结束编码 */
    @Excel(name = "结束编码")
    private String endNo;

    /** 区域领取人 */
    @Excel(name = "区域领取人")
    private Long aUserid;

    /** 区域领取时间 */
    @Excel(name = "区域领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date aGetTime;

    /** 客户经理领取人 */
    @Excel(name = "客户经理领取人")
    private Long kUserid;

    /** 客户经理领取时间 */
    @Excel(name = "客户经理领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date kGetTime;

    /** 归还人 */
    @Excel(name = "归还人")
    private Long gUserid;

    /** 归还时间 */
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gReTime;

    /** 当前拥有者 */
    @Excel(name = "当前拥有者")
    private Long userId;

    /** 出库区域 */
    @Excel(name = "出库区域")
    private Long deptId;

    /** 状态(0 代表未出库 1 代表已出库  2 已归还) */
    @Excel(name = "状态(0 代表未出库 1 代表已出库  2 已归还)")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    //区域
    private SysUser aUser;

    //经理
    private SysUser kUser;

    //区域
    private SysDept dept;

    public SysUser getaUser() {
        return aUser;
    }

    public void setaUser(SysUser aUser) {
        this.aUser = aUser;
    }

    public SysUser getkUser() {
        return kUser;
    }

    public void setkUser(SysUser kUser) {
        this.kUser = kUser;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setpName(String pName)
    {
        this.pName = pName;
    }

    public String getpName()
    {
        return pName;
    }
    public void setbName(String bName)
    {
        this.bName = bName;
    }

    public String getbName()
    {
        return bName;
    }
    public void setNum(Long num)
    {
        this.num = num;
    }

    public Long getNum()
    {
        return num;
    }
    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }
    public void setStartNo(String startNo)
    {
        this.startNo = startNo;
    }

    public String getStartNo()
    {
        return startNo;
    }
    public void setEndNo(String endNo)
    {
        this.endNo = endNo;
    }

    public String getEndNo()
    {
        return endNo;
    }
    public void setaUserid(Long aUserid)
    {
        this.aUserid = aUserid;
    }

    public Long getaUserid()
    {
        return aUserid;
    }
    public void setaGetTime(Date aGetTime)
    {
        this.aGetTime = aGetTime;
    }

    public Date getaGetTime()
    {
        return aGetTime;
    }
    public void setkUserid(Long kUserid)
    {
        this.kUserid = kUserid;
    }

    public Long getkUserid()
    {
        return kUserid;
    }
    public void setkGetTime(Date kGetTime)
    {
        this.kGetTime = kGetTime;
    }

    public Date getkGetTime()
    {
        return kGetTime;
    }

    public void setgUserid(Long gUserid)
    {
        this.gUserid = gUserid;
    }

    public Long getgUserid()
    {
        return gUserid;
    }
    public void setgReTime(Date gReTime)
    {
        this.gReTime = gReTime;
    }

    public Date getgReTime()
    {
        return gReTime;
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
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
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
                .append("pName", getpName())
                .append("bName", getbName())
                .append("num", getNum())
                .append("numUse",getNumUse())
                .append("color", getColor())
                .append("startNo", getStartNo())
                .append("endNo", getEndNo())
                .append("remark", getRemark())
                .append("aUserid", getaUserid())
                .append("aGetTime", getaGetTime())
                .append("kUserid", getkUserid())
                .append("kGetTime", getkGetTime())
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
