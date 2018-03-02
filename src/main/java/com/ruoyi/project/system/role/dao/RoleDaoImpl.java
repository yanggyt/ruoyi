package com.ruoyi.project.system.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;

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
    public List<String> selectRolesByUserId(Long userId)
    {
        List<String> permsList = null;
        try
        {
            permsList = this.findForList("SystemRoleMapper.selectRolesByUserId", userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return permsList;
    }

}
