package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.MoneyPoolLog;
import java.util.List;	

/**
 * 资金池交明细 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface MoneyPoolLogMapper 
{
	/**
     * 查询资金池交明细信息
     * 
     * @param billType 资金池交明细ID
     * @return 资金池交明细信息
     */
	public MoneyPoolLog selectMoneyPoolLogById(Integer billType);
	
	/**
     * 查询资金池交明细列表
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 资金池交明细集合
     */
	public List<MoneyPoolLog> selectMoneyPoolLogList(MoneyPoolLog moneyPoolLog);
	
	/**
     * 新增资金池交明细
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 结果
     */
	public int insertMoneyPoolLog(MoneyPoolLog moneyPoolLog);
	
	/**
     * 修改资金池交明细
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 结果
     */
	public int updateMoneyPoolLog(MoneyPoolLog moneyPoolLog);
	
	/**
     * 删除资金池交明细
     * 
     * @param billType 资金池交明细ID
     * @return 结果
     */
	public int deleteMoneyPoolLogById(Integer billType);
	
	/**
     * 批量删除资金池交明细
     * 
     * @param billTypes 需要删除的数据ID
     * @return 结果
     */
	public int deleteMoneyPoolLogByIds(String[] billTypes);
	
}