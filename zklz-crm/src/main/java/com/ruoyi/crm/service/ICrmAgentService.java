package com.ruoyi.crm.service;

import java.util.List;
import com.ruoyi.crm.domain.CrmAgent;

/**
 * 代理商管理Service接口
 * 
 * @author wendukeji.cn
 * @date 2021-06-30
 */
public interface ICrmAgentService 
{
    /**
     * 查询代理商管理
     * 
     * @param id 代理商管理ID
     * @return 代理商管理
     */
    public CrmAgent selectCrmAgentById(Long id);

    /**
     * 查询代理商管理列表
     * 
     * @param crmAgent 代理商管理
     * @return 代理商管理集合
     */
    public List<CrmAgent> selectCrmAgentList(CrmAgent crmAgent);

    /**
     * 新增代理商管理
     * 
     * @param crmAgent 代理商管理
     * @return 结果
     */
    public int insertCrmAgent(CrmAgent crmAgent);

    /**
     * 修改代理商管理
     * 
     * @param crmAgent 代理商管理
     * @return 结果
     */
    public int updateCrmAgent(CrmAgent crmAgent);

    /**
     * 批量删除代理商管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmAgentByIds(String ids);

    /**
     * 删除代理商管理信息
     * 
     * @param id 代理商管理ID
     * @return 结果
     */
    public int deleteCrmAgentById(Long id);
}
