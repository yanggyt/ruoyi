package com.ruoyi.test.mapper;

import com.ruoyi.test.domain.SysCustomer;
import com.ruoyi.test.domain.SysGoods;

import java.util.List;

/**
 * 客户Mapper接口
 * 
 * @author box
 * @date 2021-02-13
 */
public interface SysCustomerMapper 
{
    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    public SysCustomer selectSysCustomerById(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param sysCustomer 客户
     * @return 客户集合
     */
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer);

    /**
     * 新增客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    public int insertSysCustomer(SysCustomer sysCustomer);

    /**
     * 修改客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    public int updateSysCustomer(SysCustomer sysCustomer);

    /**
     * 删除客户
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long customerId);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(String[] customerIds);

    /**
     * 批量删除商品
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysGoodsByCustomerIds(String[] customerIds);
    
    /**
     * 批量新增商品
     * 
     * @param sysGoodsList 商品列表
     * @return 结果
     */
    public int batchSysGoods(List<SysGoods> sysGoodsList);
    

    /**
     * 通过客户ID删除商品信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteSysGoodsByCustomerId(Long customerId);
}
