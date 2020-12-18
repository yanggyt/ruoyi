package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RelevTableMapper;
import com.ruoyi.system.domain.RelevTable;
import com.ruoyi.system.service.IRelevTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 关联实体维护Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-17
 */
@Service
public class RelevTableServiceImpl implements IRelevTableService 
{
    @Autowired
    private RelevTableMapper relevTableMapper;

    /**
     * 查询关联实体维护
     * 
     * @param relevId 关联实体维护ID
     * @return 关联实体维护
     */
    @Override
    public RelevTable selectRelevTableById(Long relevId)
    {
        return relevTableMapper.selectRelevTableById(relevId);
    }

    /**
     * 查询关联实体维护
     *
     * @param relevEntity 关联实体维护ID
     * @return 关联实体维护
     */
    @Override
    public RelevTable selectRelevTableByRelevEntity(String relevEntity)
    {
        return relevTableMapper.selectRelevTableByRelevEntity(relevEntity);
    }
    /**
     * 查询关联实体维护列表
     *
     * @return 关联实体维护
     */
    public List<RelevTable> selectRelevTableAll()
    {
        return relevTableMapper.selectRelevTableAll();
    }

    /**
     * 查询关联实体维护列表
     * 
     * @param relevTable 关联实体维护
     * @return 关联实体维护
     */
    @Override
    public List<RelevTable> selectRelevTableList(RelevTable relevTable)
    {
        return relevTableMapper.selectRelevTableList(relevTable);
    }

    /**
     * 新增关联实体维护
     * 
     * @param relevTable 关联实体维护
     * @return 结果
     */
    @Override
    public int insertRelevTable(RelevTable relevTable)
    {
        relevTable.setCreateTime(DateUtils.getNowDate());
        return relevTableMapper.insertRelevTable(relevTable);
    }

    /**
     * 修改关联实体维护
     * 
     * @param relevTable 关联实体维护
     * @return 结果
     */
    @Override
    public int updateRelevTable(RelevTable relevTable)
    {
        relevTable.setUpdateTime(DateUtils.getNowDate());
        return relevTableMapper.updateRelevTable(relevTable);
    }

    /**
     * 删除关联实体维护对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRelevTableByIds(String ids)
    {
        return relevTableMapper.deleteRelevTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关联实体维护信息
     * 
     * @param relevId 关联实体维护ID
     * @return 结果
     */
    @Override
    public int deleteRelevTableById(Long relevId)
    {
        return relevTableMapper.deleteRelevTableById(relevId);
    }
}
