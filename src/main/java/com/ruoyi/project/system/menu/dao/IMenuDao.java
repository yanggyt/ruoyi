package com.ruoyi.project.system.menu.dao;

import java.util.List;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 角色表 数据层
 * 
 * @author yangzz
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
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    public List<Menu> selectPermsAll();

}
