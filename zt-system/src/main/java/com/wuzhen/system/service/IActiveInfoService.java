package com.wuzhen.system.service;

import com.wuzhen.system.domain.ActiveInfo;

import java.util.List;

/**
 *  活动信息服务层
 * 
 * @author zhengzheng
 */
public interface IActiveInfoService
{
    /**
     * 根据条件分页查询角色数据
     *
     * @param activeInfo 活动信息
     * @return 活动数据集合信息
     */
    public List<ActiveInfo> selectActiveList(ActiveInfo activeInfo);


    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    public List<ActiveInfo> selectActiveAll();

    /**
     * 通过角色ID查询角色
     *
     * @param id 活动ID
     * @return 角色对象信息
     */
    public ActiveInfo selectActiveById(Long id);

    /**
     * 通过活动ID删除角色
     *
     * @param id 角色ID
     * @return 结果
     */
    public boolean deleteActiveById(Long id);

    /**
     * 批量删除角色用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteActiveByIds(String ids);

    /**
     * 新增保存角色信息
     *
     * @param activeInfo 角色信息
     * @return 结果
     */
    public int insertActive(ActiveInfo activeInfo);

    /**
     * 修改保存角色信息
     *
     * @param activeInfo 角色信息
     * @return 结果
     */
    public int updateActive(ActiveInfo activeInfo);


}
