package com.ruoyi.project.system.menu.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.core.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 用户 数据层处理
 * 
 * @author yangzz
 */
@Repository("menuDao")
public class MenuDao extends DynamicObjectBaseDao implements IMenuDao
{

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @SuppressWarnings("unchecked")
    public List<Menu> findMenusByUserId(Long userId)
    {
        List<Menu> permsList = null;
        try
        {
            permsList = (List<Menu>) this.findForList("SystemMenuMapper.findMenusByUserId", userId);
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
    @SuppressWarnings("unchecked")
    public List<String> findPermsByUserId(Long userId)
    {
        List<String> permsList = null;
        try
        {
            permsList = (List<String>) this.findForList("SystemMenuMapper.findPermsByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @SuppressWarnings("unchecked")
    public List<Menu> findAllPerms()
    {
        List<Menu> permsList = null;
        try
        {
            permsList = (List<Menu>) this.findForList("SystemMenuMapper.findAllPerms");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

}
