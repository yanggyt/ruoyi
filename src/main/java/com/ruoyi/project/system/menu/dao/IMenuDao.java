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
     * 根据用户ID查询权限表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> findMenusByUserId(Long userId);

}
