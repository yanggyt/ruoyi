package com.ruoyi.content.service;

import com.ruoyi.content.domain.CmsSysRole;
import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 说明：系统角色
 */
public interface CmsRoleService {

    /**
     * 添加新角色
     */
    public Message insertRole(CmsSysRole role);

    /**
     * 删除角色
     */
    public Message delRole(String id);

    /**
     * 修改角色信息
     */
    public Message updateRole(CmsSysRole role);

    /**
     * 查询所有的角色,分页展示
     */
    public List<?> queryRole(int startRow, int rows, String name, String email);

    /**
     * 查询单个角色
     */
    public CmsSysRole queryRoleById(String id);

    /**
     * 查询角色数量
     */
    public int countRole(String name);

}
