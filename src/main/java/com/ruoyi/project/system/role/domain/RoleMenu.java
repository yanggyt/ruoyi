package com.ruoyi.project.system.role.domain;

import lombok.Data;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author ruoyi
 */
@Data
public class RoleMenu
{
    /** 角色ID */
    private Long roleId;
    /** 菜单ID */
    private Long menuId;
}
