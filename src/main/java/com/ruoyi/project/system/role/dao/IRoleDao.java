package com.ruoyi.project.system.role.dao;

import java.util.List;

import com.ruoyi.project.system.role.domain.Role;

/**
 * 角色表 数据层
 * 
 * @author ruoyi
 */
public interface IRoleDao
{

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> selectRolesByUserId(Long userId);

    /**
     * 查询角色列表
     * 
     * @return 角色列表
     */
    public List<Role> selectRolesAll();

}
