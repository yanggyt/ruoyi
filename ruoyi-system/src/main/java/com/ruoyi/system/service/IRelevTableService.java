package com.ruoyi.system.service;

import com.ruoyi.system.domain.RelevTable;

import java.util.List;

/**
 * 关联实体维护Service接口
 * 
 * @author dalin
 * @date 2020-12-17
 */
public interface IRelevTableService 
{
    /**
     * 查询关联实体维护
     * 
     * @param relevId 关联实体维护ID
     * @return 关联实体维护
     */
    public RelevTable selectRelevTableById(Long relevId);

    /**
     * 查询关联实体维护
     *
     * @param relevEntity 关联实体维护ID
     * @return 关联实体维护
     */
    public RelevTable selectRelevTableByRelevEntity(String relevEntity);

    /**
     * 查询关联实体维护列表
     * 
     * @param relevTable 关联实体维护
     * @return 关联实体维护集合
     */
    public List<RelevTable> selectRelevTableList(RelevTable relevTable);

    /**
     * 新增关联实体维护
     * 
     * @param relevTable 关联实体维护
     * @return 结果
     */
    public int insertRelevTable(RelevTable relevTable);

    /**
     * 修改关联实体维护
     * 
     * @param relevTable 关联实体维护
     * @return 结果
     */
    public int updateRelevTable(RelevTable relevTable);

    /**
     * 批量删除关联实体维护
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRelevTableByIds(String ids);

    /**
     * 删除关联实体维护信息
     * 
     * @param relevId 关联实体维护ID
     * @return 结果
     */
    public int deleteRelevTableById(Long relevId);
}
