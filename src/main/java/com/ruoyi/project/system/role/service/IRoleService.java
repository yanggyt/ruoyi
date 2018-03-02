package com.ruoyi.project.system.role.service;

import java.util.Set;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface IRoleService
{

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRolesByUserId(Long userId);

}
