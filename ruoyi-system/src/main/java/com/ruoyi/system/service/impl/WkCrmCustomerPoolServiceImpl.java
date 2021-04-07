package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmCustomerPoolMapper;
import com.ruoyi.system.domain.WkCrmCustomerPool;
import com.ruoyi.system.service.IWkCrmCustomerPoolService;
import com.ruoyi.common.core.text.Convert;

/**
 * 公海Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmCustomerPoolServiceImpl implements IWkCrmCustomerPoolService 
{
    @Autowired
    private WkCrmCustomerPoolMapper wkCrmCustomerPoolMapper;

    /**
     * 查询公海
     * 
     * @param poolId 公海ID
     * @return 公海
     */
    @Override
    public WkCrmCustomerPool selectWkCrmCustomerPoolById(Long poolId)
    {
        return wkCrmCustomerPoolMapper.selectWkCrmCustomerPoolById(poolId);
    }

    /**
     * 查询公海列表
     * 
     * @param wkCrmCustomerPool 公海
     * @return 公海
     */
    @Override
    public List<WkCrmCustomerPool> selectWkCrmCustomerPoolList(WkCrmCustomerPool wkCrmCustomerPool)
    {
        return wkCrmCustomerPoolMapper.selectWkCrmCustomerPoolList(wkCrmCustomerPool);
    }

    /**
     * 新增公海
     * 
     * @param wkCrmCustomerPool 公海
     * @return 结果
     */
    @Override
    public int insertWkCrmCustomerPool(WkCrmCustomerPool wkCrmCustomerPool)
    {
        wkCrmCustomerPool.setCreateTime(DateUtils.getNowDate());
        return wkCrmCustomerPoolMapper.insertWkCrmCustomerPool(wkCrmCustomerPool);
    }

    /**
     * 修改公海
     * 
     * @param wkCrmCustomerPool 公海
     * @return 结果
     */
    @Override
    public int updateWkCrmCustomerPool(WkCrmCustomerPool wkCrmCustomerPool)
    {
        return wkCrmCustomerPoolMapper.updateWkCrmCustomerPool(wkCrmCustomerPool);
    }

    /**
     * 删除公海对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCustomerPoolByIds(String ids)
    {
        return wkCrmCustomerPoolMapper.deleteWkCrmCustomerPoolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除公海信息
     * 
     * @param poolId 公海ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCustomerPoolById(Long poolId)
    {
        return wkCrmCustomerPoolMapper.deleteWkCrmCustomerPoolById(poolId);
    }
}
