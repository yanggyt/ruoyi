package com.ruoyi.project.system.role.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
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
     * 根据条件分页查询用户对象
     * 
     * @param pageUtilEntity 分页对象
     * @return 用户信息集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return this.findForList("SystemRoleMapper.pageInfoQuery", pageUtilEntity);
    }

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

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role selectRoleById(Long roleId)
    {
        return this.findForObject("SystemRoleMapper.selectRoleById", roleId);
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId)
    {
        return this.delete("SystemRoleMapper.deleteRoleById", roleId);
    }

    /**
     * 批量角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteRole(Long[] ids)
    {
        return this.delete("SystemRoleMapper.batchDeleteRole", ids);
    }

}
