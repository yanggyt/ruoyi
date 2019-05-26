package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Customer;
import java.util.List;

/**
 * 客户 服务层
 * 
 * @author pengc
 * @date 2019-05-26
 */
public interface ICustomerService 
{
	/**
     * 查询客户信息
     * 
     * @param customerid 客户ID
     * @return 客户信息
     */
	public Customer selectCustomerById(Integer customerid);
	
	/**
     * 查询客户列表
     * 
     * @param customer 客户信息
     * @return 客户集合
     */
	public List<Customer> selectCustomerList(Customer customer);
	
	/**
     * 新增客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int insertCustomer(Customer customer);
	
	/**
     * 修改客户
     * 
     * @param customer 客户信息
     * @return 结果
     */
	public int updateCustomer(Customer customer);
		
	/**
     * 删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCustomerByIds(String ids);
	
}
