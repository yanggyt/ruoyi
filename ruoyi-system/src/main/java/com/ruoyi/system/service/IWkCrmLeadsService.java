package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WkCrmLeads;

/**
 * 线索Service接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface IWkCrmLeadsService 
{
    /**
     * 查询线索
     * 
     * @param leadsId 线索ID
     * @return 线索
     */
    public WkCrmLeads selectWkCrmLeadsById(Long leadsId);

    /**
     * 查询线索列表
     * 
     * @param wkCrmLeads 线索
     * @return 线索集合
     */
    public List<WkCrmLeads> selectWkCrmLeadsList(WkCrmLeads wkCrmLeads);

    /**
     * 新增线索
     * 
     * @param wkCrmLeads 线索
     * @return 结果
     */
    public int insertWkCrmLeads(WkCrmLeads wkCrmLeads);

    /**
     * 修改线索
     * 
     * @param wkCrmLeads 线索
     * @return 结果
     */
    public int updateWkCrmLeads(WkCrmLeads wkCrmLeads);

    /**
     * 批量删除线索
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmLeadsByIds(String ids);

    /**
     * 删除线索信息
     * 
     * @param leadsId 线索ID
     * @return 结果
     */
    public int deleteWkCrmLeadsById(Long leadsId);
}
