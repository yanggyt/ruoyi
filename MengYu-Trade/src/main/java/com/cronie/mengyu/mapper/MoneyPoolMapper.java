package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.MoneyPool;
import java.util.List;	

/**
 * 资金池 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface MoneyPoolMapper 
{
	/**
     * 查询资金池信息
     * 
     * @param creater 资金池ID
     * @return 资金池信息
     */
	public MoneyPool selectMoneyPoolById(Integer creater);
	
	/**
     * 查询资金池列表
     * 
     * @param moneyPool 资金池信息
     * @return 资金池集合
     */
	public List<MoneyPool> selectMoneyPoolList(MoneyPool moneyPool);
	
	/**
     * 新增资金池
     * 
     * @param moneyPool 资金池信息
     * @return 结果
     */
	public int insertMoneyPool(MoneyPool moneyPool);
	
	/**
     * 修改资金池
     * 
     * @param moneyPool 资金池信息
     * @return 结果
     */
	public int updateMoneyPool(MoneyPool moneyPool);
	
	/**
     * 删除资金池
     * 
     * @param creater 资金池ID
     * @return 结果
     */
	public int deleteMoneyPoolById(Integer creater);
	
	/**
     * 批量删除资金池
     * 
     * @param creaters 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoneyPoolByIds(String[] creaters);
	
}