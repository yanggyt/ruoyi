package com.ruoyi.test.service;

import com.ruoyi.test.domain.SysCustomer;

import java.util.List;

/**
 * 客户Service接口
 * 
 * @author box
 * @date 2021-02-13
 */
public interface ISysCustomerService 
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
     * 批量删除客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(String ids);

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long customerId);
}
