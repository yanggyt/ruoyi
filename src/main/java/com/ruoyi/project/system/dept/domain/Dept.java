package com.ruoyi.project.system.dept.domain;

import lombok.Data;

/**
 * 部门对象 sys_dept
 * 
 * @author ruoyi
 */
@Data
public class Dept
{
    /** 部门ID */
    private Long deptId;
    /** 父部门ID */
    private Long parentId;
    /** 部门名称 */
    private String deptName;
    /** 显示顺序 */
    private String orderNum;
    /** 部门状态:0正常,1停用 */
    private String status;

}
