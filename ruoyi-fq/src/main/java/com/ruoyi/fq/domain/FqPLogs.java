package com.ruoyi.fq.domain;

import java.util.Date;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 封铅袋出入库记录对象 fq_p_logs
 * 
 * @author mario
 * @date 2020-07-09
 */
public class FqPLogs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private String id;

    /** 封铅袋id */
    @Excel(name = "封铅袋id")
    private Long pId;

    /** 袋内数量 */
    @Excel(name = "袋内数量")
    private Long num;

    /** 使用数量 */
    @Excel(name = "使用数量")
    private Long numUse;

    public Long getNumUse() {
        return numUse;
    }

    public void setNumUse(Long numUse) {
        this.numUse = numUse;
    }

    /** 归还人 */
    @Excel(name = "归还人")
    private Long gUserid;

    /** 归还时间 */
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gReTime;

    /** 操作类型(0 代表区域领取 1 代表区域归还 2 入库 3客户经理领取 4客户经理归还) */
    @Excel(name = "操作类型(0 代表区域领取 1 代表区域归还 2 入库 3客户经理领取 4客户经理归还)")
    private String optType;

    /** 领取人 */
    @Excel(name = "领取人")
    private Long userId;

    /** 领取时间 */
    @Excel(name = "领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date getTime;

    /** 当前区域 */
    @Excel(name = "当前区域")
    private Long deptId;

    private SysUser user;
    private SysUser gUser;
    private SysDept dept;
    private FqPackage fqPackage;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysUser getgUser() {
        return gUser;
    }

    public void setgUser(SysUser gUser) {
        this.gUser = gUser;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public FqPackage getFqPackage() {
        return fqPackage;
    }

    public void setFqPackage(FqPackage fqPackage) {
        this.fqPackage = fqPackage;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setpId(Long pId)
    {
        this.pId = pId;
    }

    public Long getpId()
    {
        return pId;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
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
    public void setOptType(String optType) 
    {
        this.optType = optType;
    }

    public String getOptType() 
    {
        return optType;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setGetTime(Date getTime) 
    {
        this.getTime = getTime;
    }

    public Date getGetTime() 
    {
        return getTime;
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
            .append("pId", getpId())
            .append("num", getNum())
            .append("numUse",getNumUse())
            .append("gUserid", getgUserid())
            .append("gReTime", getgReTime())
            .append("optType", getOptType())
            .append("userId", getUserId())
            .append("getTime", getGetTime())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
