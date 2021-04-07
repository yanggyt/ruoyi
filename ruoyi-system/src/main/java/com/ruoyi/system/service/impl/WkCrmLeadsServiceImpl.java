package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmLeadsMapper;
import com.ruoyi.system.domain.WkCrmLeads;
import com.ruoyi.system.service.IWkCrmLeadsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 线索Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmLeadsServiceImpl implements IWkCrmLeadsService 
{
    @Autowired
    private WkCrmLeadsMapper wkCrmLeadsMapper;

    /**
     * 查询线索
     * 
     * @param leadsId 线索ID
     * @return 线索
     */
    @Override
    public WkCrmLeads selectWkCrmLeadsById(Long leadsId)
    {
        return wkCrmLeadsMapper.selectWkCrmLeadsById(leadsId);
    }

    /**
     * 查询线索列表
     * 
     * @param wkCrmLeads 线索
     * @return 线索
     */
    @Override
    public List<WkCrmLeads> selectWkCrmLeadsList(WkCrmLeads wkCrmLeads)
    {
        return wkCrmLeadsMapper.selectWkCrmLeadsList(wkCrmLeads);
    }

    /**
     * 新增线索
     * 
     * @param wkCrmLeads 线索
     * @return 结果
     */
    @Override
    public int insertWkCrmLeads(WkCrmLeads wkCrmLeads)
    {
        wkCrmLeads.setCreateTime(DateUtils.getNowDate());
        return wkCrmLeadsMapper.insertWkCrmLeads(wkCrmLeads);
    }

    /**
     * 修改线索
     * 
     * @param wkCrmLeads 线索
     * @return 结果
     */
    @Override
    public int updateWkCrmLeads(WkCrmLeads wkCrmLeads)
    {
        wkCrmLeads.setUpdateTime(DateUtils.getNowDate());
        return wkCrmLeadsMapper.updateWkCrmLeads(wkCrmLeads);
    }

    /**
     * 删除线索对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmLeadsByIds(String ids)
    {
        return wkCrmLeadsMapper.deleteWkCrmLeadsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线索信息
     * 
     * @param leadsId 线索ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmLeadsById(Long leadsId)
    {
        return wkCrmLeadsMapper.deleteWkCrmLeadsById(leadsId);
    }
}
