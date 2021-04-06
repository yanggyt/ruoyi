package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmCustomerMapper;
import com.ruoyi.system.domain.WkCrmCustomer;
import com.ruoyi.system.service.IWkCrmCustomerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmCustomerServiceImpl implements IWkCrmCustomerService 
{
    @Autowired
    private WkCrmCustomerMapper wkCrmCustomerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    @Override
    public WkCrmCustomer selectWkCrmCustomerById(Long customerId)
    {
        return wkCrmCustomerMapper.selectWkCrmCustomerById(customerId);
    }

    /**
     * 查询客户列表
     * 
     * @param wkCrmCustomer 客户
     * @return 客户
     */
    @Override
    public List<WkCrmCustomer> selectWkCrmCustomerList(WkCrmCustomer wkCrmCustomer)
    {
        return wkCrmCustomerMapper.selectWkCrmCustomerList(wkCrmCustomer);
    }

    /**
     * 新增客户
     * 
     * @param wkCrmCustomer 客户
     * @return 结果
     */
    @Override
    public int insertWkCrmCustomer(WkCrmCustomer wkCrmCustomer)
    {
        wkCrmCustomer.setCreateTime(DateUtils.getNowDate());
        return wkCrmCustomerMapper.insertWkCrmCustomer(wkCrmCustomer);
    }

    /**
     * 修改客户
     * 
     * @param wkCrmCustomer 客户
     * @return 结果
     */
    @Override
    public int updateWkCrmCustomer(WkCrmCustomer wkCrmCustomer)
    {
        wkCrmCustomer.setUpdateTime(DateUtils.getNowDate());
        return wkCrmCustomerMapper.updateWkCrmCustomer(wkCrmCustomer);
    }

    /**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCustomerByIds(String ids)
    {
        return wkCrmCustomerMapper.deleteWkCrmCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCustomerById(Long customerId)
    {
        return wkCrmCustomerMapper.deleteWkCrmCustomerById(customerId);
    }
}
