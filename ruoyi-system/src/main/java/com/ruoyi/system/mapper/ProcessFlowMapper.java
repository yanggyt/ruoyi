package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ProcessFlow;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程中间Mapper接口
 *
 * @author SKaiL
 * @date 2022-09-26
 */
public interface ProcessFlowMapper
{
    /**
     * 查询流程中间
     *
     * @param id 流程中间主键
     * @return 流程中间
     */
    public ProcessFlow selectProcessFlowById(Long id);

    /**
     * 查询流程中间列表
     * @param orderId 父id
     * @param type 类型
     * @return
     */
    public List<ProcessFlow> selectProcessFlowListByOrderId(@Param("orderId") Long orderId, @Param("type")Integer type);

    /**
     * 查询流程中间列表
     *
     * @param processFlow 流程中间
     * @return 流程中间集合
     */
    public List<ProcessFlow> selectProcessFlowList(ProcessFlow processFlow);

    /**
     * 新增流程中间
     *
     * @param processFlow 流程中间
     * @return 结果
     */
    public int insertProcessFlow(ProcessFlow processFlow);

    /**
     * 修改流程中间
     *
     * @param processFlow 流程中间
     * @return 结果
     */
    @Transactional
    public int updateProcessFlow(ProcessFlow processFlow);

    /**
     * 删除流程中间
     *
     * @param id 流程中间主键
     * @return 结果
     */
    public int deleteProcessFlowById(Long id);

    /**
     * 批量删除流程中间
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProcessFlowByIds(String[] ids);


    /**
     * 批量添加
     * @param processFlowList
     * @return
     */
    public int batchInsertProcessFlow(List<ProcessFlow> processFlowList);
}
