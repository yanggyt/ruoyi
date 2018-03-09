package com.ruoyi.project.system.menu.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 菜单 数据层处理
 * 
 * @author ruoyi
 */
@Repository("menuDao")
public class MenuDaoImpl extends DynamicObjectBaseDao implements IMenuDao
{

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenusByUserId(Long userId)
    {
        List<Menu> permsList = null;
        try
        {
            permsList = this.findForList("SystemMenuMapper.selectMenusByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<String> selectPermsByUserId(Long userId)
    {
        List<String> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectPermsByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(Long roleId)
    {
        List<String> menuList = null;
        try
        {
            menuList = this.findForList("SystemMenuMapper.selectMenuTree", roleId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return menuList;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public List<Menu> selectPermsAll()
    {
        List<Menu> permsList = null;
        try
        {
            permsList = this.findForList("SystemMenuMapper.selectPermsAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

}
