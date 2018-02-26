package com.ruoyi.project.system.role.dao;

import java.util.List;

/**
 * 角色表 数据层
 * 
 * @author yangzz
 */
public interface IRoleDao
{

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<String> selectRolesByUserId(Long userId);

}
