package com.ruoyi.project.system.role.service;

import java.util.List;
import java.util.Set;

import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 角色业务层
 * 
 * @author ruoyi
 */
public interface IRoleService
{

    /**
     * 根据条件分页查询角色对象
     * 
     * @param pageUtilEntity 分页对象
     * @return 角色信息集合信息
     */
    public TableDataInfo pageInfoQuery(PageUtilEntity pageUtilEntity);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<Role> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 权限列表
     */
    public List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId);

    /**
     * 批量角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteRole(Long[] ids);

}
