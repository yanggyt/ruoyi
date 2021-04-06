package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WkCrmContract;

/**
 * 合同Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface WkCrmContractMapper 
{
    /**
     * 查询合同
     * 
     * @param contractId 合同ID
     * @return 合同
     */
    public WkCrmContract selectWkCrmContractById(Integer contractId);

    /**
     * 查询合同列表
     * 
     * @param wkCrmContract 合同
     * @return 合同集合
     */
    public List<WkCrmContract> selectWkCrmContractList(WkCrmContract wkCrmContract);

    /**
     * 新增合同
     * 
     * @param wkCrmContract 合同
     * @return 结果
     */
    public int insertWkCrmContract(WkCrmContract wkCrmContract);

    /**
     * 修改合同
     * 
     * @param wkCrmContract 合同
     * @return 结果
     */
    public int updateWkCrmContract(WkCrmContract wkCrmContract);

    /**
     * 删除合同
     * 
     * @param contractId 合同ID
     * @return 结果
     */
    public int deleteWkCrmContractById(Integer contractId);

    /**
     * 批量删除合同
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmContractByIds(String[] contractIds);
}
