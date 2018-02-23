package com.ruoyi.project.system.menu.service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.tools.StringTools;
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
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private IMenuDao menuDao;

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> findMenusByUserId(Long userId)
    {
        List<Menu> menus = menuDao.findMenusByUserId(userId);
        return TreeUtil.getChildPerms(menus, 0);
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> findPermsByUserId(Long userId)
    {
        List<String> perms = menuDao.findPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotBlank(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public LinkedHashMap<String, String> findAllPerms()
    {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<Menu> permissions = menuDao.findAllPerms();
        if (StringTools.isNotEmpty(permissions))
        {
            for (Menu menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

}
