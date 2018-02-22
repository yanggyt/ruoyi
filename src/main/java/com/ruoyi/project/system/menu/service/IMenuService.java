package com.ruoyi.project.system.menu.service;

import java.util.List;
import com.ruoyi.project.system.menu.domain.Menu;

/**
 * 菜单 业务层
 * 
 * @author yangzz
 */
public interface IMenuService
{

    /**
     * 根据用户ID查询权限表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> findMenusByUserId(Long userId);

}
