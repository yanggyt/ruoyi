package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmContractMapper;
import com.ruoyi.system.domain.WkCrmContract;
import com.ruoyi.system.service.IWkCrmContractService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmContractServiceImpl implements IWkCrmContractService 
{
    @Autowired
    private WkCrmContractMapper wkCrmContractMapper;

    /**
     * 查询合同
     * 
     * @param contractId 合同ID
     * @return 合同
     */
    @Override
    public WkCrmContract selectWkCrmContractById(Integer contractId)
    {
        return wkCrmContractMapper.selectWkCrmContractById(contractId);
    }

    /**
     * 查询合同列表
     * 
     * @param wkCrmContract 合同
     * @return 合同
     */
    @Override
    public List<WkCrmContract> selectWkCrmContractList(WkCrmContract wkCrmContract)
    {
        return wkCrmContractMapper.selectWkCrmContractList(wkCrmContract);
    }

    /**
     * 新增合同
     * 
     * @param wkCrmContract 合同
     * @return 结果
     */
    @Override
    public int insertWkCrmContract(WkCrmContract wkCrmContract)
    {
        wkCrmContract.setCreateTime(DateUtils.getNowDate());
        return wkCrmContractMapper.insertWkCrmContract(wkCrmContract);
    }

    /**
     * 修改合同
     * 
     * @param wkCrmContract 合同
     * @return 结果
     */
    @Override
    public int updateWkCrmContract(WkCrmContract wkCrmContract)
    {
        wkCrmContract.setUpdateTime(DateUtils.getNowDate());
        return wkCrmContractMapper.updateWkCrmContract(wkCrmContract);
    }

    /**
     * 删除合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmContractByIds(String ids)
    {
        return wkCrmContractMapper.deleteWkCrmContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同信息
     * 
     * @param contractId 合同ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmContractById(Integer contractId)
    {
        return wkCrmContractMapper.deleteWkCrmContractById(contractId);
    }
}
