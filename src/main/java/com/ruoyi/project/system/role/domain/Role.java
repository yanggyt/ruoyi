package com.ruoyi.project.system.role.domain;

import lombok.Data;

/**
 * 角色对象 sys_role
 * 
 * @author yangzz
 */
@Data
public class Role
{
    /** 角色ID */
    private Long roleId;
    /** 角色名 */
    private String roleName;
    /** 角色权限 */
    private String roleKey;
    /** 角色状态:0正常,1禁用 */
    private String status;
    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String updateTime;
    /** 更新者 */
    private String updateBy;
    /** 备注 */
    private String remark;

}
