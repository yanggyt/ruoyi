package com.ruoyi.project.system.menu.dao;

import java.util.List;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 菜单表 数据层
 * 
 * @author ruoyi
 */
public interface IMenuDao
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
    public List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(Long roleId);

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    public List<Menu> selectPermsAll();

}
