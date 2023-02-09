package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.common.core.text.Convert;

/**
 * 流程中间Service业务层处理
 *
 * @author SKaiL
 * @date 2022-09-26
 */
@Service
public class ProcessFlowServiceImpl implements IProcessFlowService
{
    @Autowired
    private ProcessFlowMapper processFlowMapper;

    /**
     * 查询流程中间
     *
     * @param id 流程中间主键
     * @return 流程中间
     */
    @Override
    public ProcessFlow selectProcessFlowById(Long id)
    {
        return processFlowMapper.selectProcessFlowById(id);
    }

    /**
     * 查询流程中间列表
     * @param orderId 父id
     * @param type 类型
     * @return
     */
    @Override
    public List<ProcessFlow> selectProcessFlowListByOrderId(Long orderId, Integer type){
        return processFlowMapper.selectProcessFlowListByOrderId(orderId, type);
    }

    /**
     * 查询流程中间列表
     *
     * @param processFlow 流程中间
     * @return 流程中间
     */
    @Override
    public List<ProcessFlow> selectProcessFlowList(ProcessFlow processFlow)
    {
        return processFlowMapper.selectProcessFlowList(processFlow);
    }

    /**
     * 新增流程中间
     *
     * @param processFlow 流程中间
     * @return 结果
     */
    @Override
    public int insertProcessFlow(ProcessFlow processFlow)
    {
        processFlow.setCreateTime(DateUtils.getNowDate());
        return processFlowMapper.insertProcessFlow(processFlow);
    }

    /**
     * 修改流程中间
     *
     * @param processFlow 流程中间
     * @return 结果
     */
    @Override
    public int updateProcessFlow(ProcessFlow processFlow)
    {
        processFlow.setUpdateTime(DateUtils.getNowDate());
        return processFlowMapper.updateProcessFlow(processFlow);
    }

    /**
     * 批量删除流程中间
     *
     * @param ids 需要删除的流程中间主键
     * @return 结果
     */
    @Override
    public int deleteProcessFlowByIds(String ids)
    {
        return processFlowMapper.deleteProcessFlowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程中间信息
     *
     * @param id 流程中间主键
     * @return 结果
     */
    @Override
    public int deleteProcessFlowById(Long id)
    {
        return processFlowMapper.deleteProcessFlowById(id);
    }
}
