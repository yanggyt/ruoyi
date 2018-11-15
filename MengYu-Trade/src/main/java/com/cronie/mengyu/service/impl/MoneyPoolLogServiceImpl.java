package com.cronie.mengyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cronie.mengyu.mapper.MoneyPoolLogMapper;
import com.cronie.mengyu.domain.MoneyPoolLog;
import com.cronie.mengyu.service.IMoneyPoolLogService;
import com.ruoyi.common.support.Convert;

/**
 * 资金池交明细 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class MoneyPoolLogServiceImpl implements IMoneyPoolLogService 
{
	@Autowired
	private MoneyPoolLogMapper moneyPoolLogMapper;

	/**
     * 查询资金池交明细信息
     * 
     * @param billType 资金池交明细ID
     * @return 资金池交明细信息
     */
    @Override
	public MoneyPoolLog selectMoneyPoolLogById(Integer billType)
	{
	    return moneyPoolLogMapper.selectMoneyPoolLogById(billType);
	}
	
	/**
     * 查询资金池交明细列表
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 资金池交明细集合
     */
	@Override
	public List<MoneyPoolLog> selectMoneyPoolLogList(MoneyPoolLog moneyPoolLog)
	{
	    return moneyPoolLogMapper.selectMoneyPoolLogList(moneyPoolLog);
	}
	
    /**
     * 新增资金池交明细
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 结果
     */
	@Override
	public int insertMoneyPoolLog(MoneyPoolLog moneyPoolLog)
	{
	    return moneyPoolLogMapper.insertMoneyPoolLog(moneyPoolLog);
	}
	
	/**
     * 修改资金池交明细
     * 
     * @param moneyPoolLog 资金池交明细信息
     * @return 结果
     */
	@Override
	public int updateMoneyPoolLog(MoneyPoolLog moneyPoolLog)
	{
	    return moneyPoolLogMapper.updateMoneyPoolLog(moneyPoolLog);
	}

	/**
     * 删除资金池交明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMoneyPoolLogByIds(String ids)
	{
		return moneyPoolLogMapper.deleteMoneyPoolLogByIds(Convert.toStrArray(ids));
	}
	
}
