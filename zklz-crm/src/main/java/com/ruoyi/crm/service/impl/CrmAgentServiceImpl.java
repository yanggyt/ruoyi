package com.ruoyi.crm.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.crm.mapper.CrmAgentMapper;
import com.ruoyi.crm.domain.CrmAgent;
import com.ruoyi.crm.service.ICrmAgentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 代理商管理Service业务层处理
 * 
 * @author wendukeji.cn
 * @date 2021-06-30
 */
@Service
public class CrmAgentServiceImpl implements ICrmAgentService 
{
    @Autowired
    private CrmAgentMapper crmAgentMapper;

    /**
     * 查询代理商管理
     * 
     * @param id 代理商管理ID
     * @return 代理商管理
     */
    @Override
    public CrmAgent selectCrmAgentById(Long id)
    {
        return crmAgentMapper.selectCrmAgentById(id);
    }

    /**
     * 查询代理商管理列表
     * 
     * @param crmAgent 代理商管理
     * @return 代理商管理
     */
    @Override
    public List<CrmAgent> selectCrmAgentList(CrmAgent crmAgent)
    {
        return crmAgentMapper.selectCrmAgentList(crmAgent);
    }

    /**
     * 新增代理商管理
     * 
     * @param crmAgent 代理商管理
     * @return 结果
     */
    @Override
    public int insertCrmAgent(CrmAgent crmAgent)
    {
        crmAgent.setCreateTime(DateUtils.getNowDate());
        return crmAgentMapper.insertCrmAgent(crmAgent);
    }

    /**
     * 修改代理商管理
     * 
     * @param crmAgent 代理商管理
     * @return 结果
     */
    @Override
    public int updateCrmAgent(CrmAgent crmAgent)
    {
        crmAgent.setUpdateTime(DateUtils.getNowDate());
        return crmAgentMapper.updateCrmAgent(crmAgent);
    }

    /**
     * 删除代理商管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmAgentByIds(String ids)
    {
        return crmAgentMapper.deleteCrmAgentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代理商管理信息
     * 
     * @param id 代理商管理ID
     * @return 结果
     */
    @Override
    public int deleteCrmAgentById(Long id)
    {
        return crmAgentMapper.deleteCrmAgentById(id);
    }
}
