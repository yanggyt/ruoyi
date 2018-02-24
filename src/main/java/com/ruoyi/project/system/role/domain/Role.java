package com.ruoyi.project.system.role.domain;

import java.io.Serializable;

/**
 * 角色对象 sys_role
 * 
 * @author yangzz
 */
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;

    // 角色ID
    private Long roleId;
    // 角色名
    private String roleName;
    // 角色状态:0正常,1禁用
    private String status;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 更新者
    private String updateBy;
    // 备注
    private String remark;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    @Override
    public String toString()
    {
        return "{\"roleId\":\"" + roleId + "\",\"roleName\":\"" + roleName + "\",\"status\":\"" + status
                + "\",\"createTime\":\"" + createTime + "\",\"updateTime\":\"" + updateTime + "\",\"updateBy\":\""
                + updateBy + "\",\"remark\":\"" + remark + "\"}  ";
    }

}
