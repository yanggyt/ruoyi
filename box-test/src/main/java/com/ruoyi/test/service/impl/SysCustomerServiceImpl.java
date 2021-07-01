package com.ruoyi.test.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.test.domain.SysCustomer;
import com.ruoyi.test.domain.SysGoods;
import com.ruoyi.test.mapper.SysCustomerMapper;
import com.ruoyi.test.service.ISysCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户Service业务层处理
 * 
 * @author box
 * @date 2021-02-13
 */
@Service
public class SysCustomerServiceImpl implements ISysCustomerService
{
    @Autowired
    private SysCustomerMapper sysCustomerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    @Override
    public SysCustomer selectSysCustomerById(Long customerId)
    {
        return sysCustomerMapper.selectSysCustomerById(customerId);
    }

    /**
     * 查询客户列表
     * 
     * @param sysCustomer 客户
     * @return 客户
     */
    @Override
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.selectSysCustomerList(sysCustomer);
    }

    /**
     * 新增客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysCustomer(SysCustomer sysCustomer)
    {
        int rows = sysCustomerMapper.insertSysCustomer(sysCustomer);
        insertSysGoods(sysCustomer);
        return rows;
    }

    /**
     * 修改客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysCustomer(SysCustomer sysCustomer)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerId(sysCustomer.getCustomerId());
        insertSysGoods(sysCustomer);
        return sysCustomerMapper.updateSysCustomer(sysCustomer);
    }

    /**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysCustomerByIds(String ids)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerIds(Convert.toStrArray(ids));
        return sysCustomerMapper.deleteSysCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    @Override
    public int deleteSysCustomerById(Long customerId)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerId(customerId);
        return sysCustomerMapper.deleteSysCustomerById(customerId);
    }

    /**
     * 新增商品信息
     * 
     * @param sysCustomer 客户对象
     */
    public void insertSysGoods(SysCustomer sysCustomer)
    {
        List<SysGoods> sysGoodsList = sysCustomer.getSysGoodsList();
        Long customerId = sysCustomer.getCustomerId();
        if (StringUtils.isNotNull(sysGoodsList))
        {
            List<SysGoods> list = new ArrayList<SysGoods>();
            for (SysGoods sysGoods : sysGoodsList)
            {
                sysGoods.setCustomerId(customerId);
                list.add(sysGoods);
            }
            if (list.size() > 0)
            {
                sysCustomerMapper.batchSysGoods(list);
            }
        }
    }
}
