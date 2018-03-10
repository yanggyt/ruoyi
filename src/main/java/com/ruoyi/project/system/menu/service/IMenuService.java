package com.ruoyi.project.system.menu.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface IMenuService
{

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> selectMenuTree(Role role);

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll();

}
