package com.cronie.mengyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cronie.mengyu.mapper.MoneyPoolMapper;
import com.cronie.mengyu.domain.MoneyPool;
import com.cronie.mengyu.service.IMoneyPoolService;
import com.ruoyi.common.support.Convert;

/**
 * 资金池 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class MoneyPoolServiceImpl implements IMoneyPoolService 
{
	@Autowired
	private MoneyPoolMapper moneyPoolMapper;

	/**
     * 查询资金池信息
     * 
     * @param creater 资金池ID
     * @return 资金池信息
     */
    @Override
	public MoneyPool selectMoneyPoolById(Integer creater)
	{
	    return moneyPoolMapper.selectMoneyPoolById(creater);
	}
	
	/**
     * 查询资金池列表
     * 
     * @param moneyPool 资金池信息
     * @return 资金池集合
     */
	@Override
	public List<MoneyPool> selectMoneyPoolList(MoneyPool moneyPool)
	{
	    return moneyPoolMapper.selectMoneyPoolList(moneyPool);
	}
	
    /**
     * 新增资金池
     * 
     * @param moneyPool 资金池信息
     * @return 结果
     */
	@Override
	public int insertMoneyPool(MoneyPool moneyPool)
	{
	    return moneyPoolMapper.insertMoneyPool(moneyPool);
	}
	
	/**
     * 修改资金池
     * 
     * @param moneyPool 资金池信息
     * @return 结果
     */
	@Override
	public int updateMoneyPool(MoneyPool moneyPool)
	{
	    return moneyPoolMapper.updateMoneyPool(moneyPool);
	}

	/**
     * 删除资金池对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoneyPoolByIds(String ids)
	{
		return moneyPoolMapper.deleteMoneyPoolByIds(Convert.toStrArray(ids));
	}
	
}
