package com.ruoyi.project.system.dept.domain;

import java.io.Serializable;

/**
 * 部门对象 sys_dept
 * 
 * @author yangzz
 */
public class Dept implements Serializable
{
    private static final long serialVersionUID = 1L;

    // 部门ID
    private Long deptId;
    // 父部门ID
    private Long parentId;
    // 部门名称
    private String deptName;
    // 显示顺序
    private String orderNum;
    // 部门状态:0正常,1停用
    private String status;

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "{\"deptId\":\"" + deptId + "\",\"parentId\":\"" + parentId + "\",\"deptName\":\"" + deptName
                + "\",\"orderNum\":\"" + orderNum + "\",\"status\":\"" + status + "\"}  ";
    }

}
