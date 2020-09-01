package com.ruoyi.his.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 科室对象 his_department
 * 
 * @author bend
 * @date 2020-07-01
 */
public class HisDepartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 科室ID */
    @Excel(name = "科室ID")
    private String deptId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 机构ID */
    private String orgCode;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 科室简介 */
    @Excel(name = "科室简介")
    private String deptProfile;

    /** 显示状态（0否 1是） */
    @Excel(name = "显示状态", readConverterExp = "0=否,1=是")
    private Integer isShow;

    /** 序号 */
    @Excel(name = "序号")
    private Integer sortNo;

    /** 删除标记（0否 1是） */
    private Integer deleted;

    /** 优秀医生标识 默认不存在 */
    private boolean flag = false;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setOrgCode(String orgCode) 
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode() 
    {
        return orgCode;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setDeptProfile(String deptProfile) 
    {
        this.deptProfile = deptProfile;
    }

    public String getDeptProfile() 
    {
        return deptProfile;
    }
    public void setIsShow(Integer isShow) 
    {
        this.isShow = isShow;
    }

    public Integer getIsShow() 
    {
        return isShow;
    }
    public void setSortNo(Integer sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Integer getSortNo() 
    {
        return sortNo;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("deptProfile", getDeptProfile())
            .append("isShow", getIsShow())
            .append("sortNo", getSortNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}
