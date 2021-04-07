package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WkCrmCustomerPool;

/**
 * 公海Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface WkCrmCustomerPoolMapper 
{
    /**
     * 查询公海
     * 
     * @param poolId 公海ID
     * @return 公海
     */
    public WkCrmCustomerPool selectWkCrmCustomerPoolById(Long poolId);

    /**
     * 查询公海列表
     * 
     * @param wkCrmCustomerPool 公海
     * @return 公海集合
     */
    public List<WkCrmCustomerPool> selectWkCrmCustomerPoolList(WkCrmCustomerPool wkCrmCustomerPool);

    /**
     * 新增公海
     * 
     * @param wkCrmCustomerPool 公海
     * @return 结果
     */
    public int insertWkCrmCustomerPool(WkCrmCustomerPool wkCrmCustomerPool);

    /**
     * 修改公海
     * 
     * @param wkCrmCustomerPool 公海
     * @return 结果
     */
    public int updateWkCrmCustomerPool(WkCrmCustomerPool wkCrmCustomerPool);

    /**
     * 删除公海
     * 
     * @param poolId 公海ID
     * @return 结果
     */
    public int deleteWkCrmCustomerPoolById(Long poolId);

    /**
     * 批量删除公海
     * 
     * @param poolIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmCustomerPoolByIds(String[] poolIds);
}
