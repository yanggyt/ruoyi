package com.ruoyi.project.system.role.domain;

import java.util.Date;
import lombok.Data;

/**
 * 角色对象 sys_role
 * 
 * @author ruoyi
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
    /** 角色排序 */
    private String roleSort;
    /** 角色状态:0正常,1禁用 */
    private int status;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 更新者 */
    private String updateBy;
    /** 备注 */
    private String remark;
    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

}
