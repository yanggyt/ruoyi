package com.ruoyi.project.system.role.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.role.dao.IRoleDao;

/**
 * 角色 业务层处理
 * 
 * @author yangzz
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
    public Set<String> selectRolesByUserId(Long userId)
    {
        List<String> perms = roleDao.selectRolesByUserId(userId);
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

}
