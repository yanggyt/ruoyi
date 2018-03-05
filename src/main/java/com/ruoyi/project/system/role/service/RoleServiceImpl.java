package com.ruoyi.project.system.role.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.role.dao.IRoleDao;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 角色 业务层处理
 * 
 * @author ruoyi
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService
{

    @Autowired
    private IRoleDao roleDao;

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId)
    {
        List<Role> perms = roleDao.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<Role> selectRolesByUserId(Long userId)
    {
        List<Role> userRoles = roleDao.selectRolesByUserId(userId);
        List<Role> roles = roleDao.selectRolesAll();
        for (Role role : roles)
        {
            for (Role userRole : userRoles)
            {
                if (role.getRoleId() == userRole.getRoleId())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

}
