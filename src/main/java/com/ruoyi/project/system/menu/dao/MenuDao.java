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
     * 根据用户ID查询权限表
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

}
