package com.ruoyi.content.service;

import com.ruoyi.content.domain.CmsSysAuthority;
import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 说明：系统菜单管理
 *
 * @author wang.q
 * @date 2017年9月15日
 */
public interface CmsSysAuthorityService {
    /**
     * 添加菜单
     *
     * @param authority
     * @return
     */
    public Message addMenu(CmsSysAuthority authority);

    /**
     * 修改菜单
     *
     * @param authority
     * @return
     */
    public Message upMenu(CmsSysAuthority authority);

    /**
     * 根据num删除菜单以及下级菜单
     *
     * @return
     */
    public Message delMenu(String nums);

    /**
     * 根据id查询下级菜单
     *
     * @param id
     * @return
     */
    public Message queryAuthoryById(String id);

    /**
     * 查询所有菜单集合
     *
     * @param startRow
     * @param rows
     * @return
     */
    public Message queryMenuList(int startRow, int rows, String id, String level, String type, String operatorRoleName);

    /**
     * 查询角色已有权限
     *
     * @param startRow
     * @param rows
     * @param id
     * @param level
     * @param type
     * @param operatorRoleName
     * @return
     */
    public Message queryRoleMenuList(int startRow, int rows, String id, String level, String type,
                                     String operatorRoleName, String role);

    /**
     * 查询所有菜单集合
     *
     * @return
     */
    public List<CmsSysAuthority> authorityList(String id, String level, String type, String operatorRoleName);

}
