package com.wuzhen.system.mapper;

import com.wuzhen.system.domain.ActiveInfo;

import java.util.List;

/**
 * 角色表 数据层
 * 
 * @author zhengzheng
 */
public interface ActiveInfoMapper
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param activeInfo 活动信息
     * @return 活动数据集合信息
     */
    public List<ActiveInfo> selectActiveList(ActiveInfo activeInfo);



    /**
     * 通过活动ID查询角色
     * 
     * @param id ID
     * @return 角色对象信息
     */
    public ActiveInfo selectActiveById(Long id);

    /**
     * 通过角色ID删除角色
     * 
     * @param id 角色ID
     * @return 结果
     */
    public int deleteActiveById(Long id);

    /**
     * 批量活动信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActiveByIds(Long[] ids);

    /**
     * 修改角色信息
     * 
     * @param activeInfo 角色信息
     * @return 结果
     */
    public int updateActive(ActiveInfo activeInfo);

    /**
     * 新增角色信息
     * 
     * @param activeInfo 角色信息
     * @return 结果
     */
    public int insertActive(ActiveInfo activeInfo);


}
