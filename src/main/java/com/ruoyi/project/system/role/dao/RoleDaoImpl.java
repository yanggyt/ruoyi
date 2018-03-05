package com.ruoyi.project.system.role.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 角色 数据层处理
 * 
 * @author ruoyi
 */
@Repository("roleDao")
public class RoleDaoImpl extends DynamicObjectBaseDao implements IRoleDao
{

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId)
    {
        List<Role> roleList = null;
        try
        {
            roleList = this.findForList("SystemRoleMapper.selectRolesByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> selectRolesAll()
    {
        List<Role> roleList = null;
        try
        {
            roleList = this.findForList("SystemRoleMapper.selectRolesAll");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roleList;
    }

}
