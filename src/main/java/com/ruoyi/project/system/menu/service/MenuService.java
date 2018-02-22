package com.ruoyi.project.system.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.system.menu.dao.IMenuDao;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.util.TreeUtil;

/**
 * 用户 业务层处理
 * 
 * @author yangzz
 */
@Service("menuService")
public class MenuService implements IMenuService
{

    @Autowired
    private IMenuDao menuDao;

    /**
     * 根据用户ID查询权限表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> findMenusByUserId(Long userId)
    {
        List<Menu> menus = menuDao.findMenusByUserId(userId);
        return TreeUtil.getChildPerms(menus, 0);
    }

}
