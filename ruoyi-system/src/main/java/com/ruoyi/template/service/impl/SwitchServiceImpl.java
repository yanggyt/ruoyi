package com.ruoyi.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.template.domain.Switch;
import com.ruoyi.template.mapper.SwitchMapper;
import com.ruoyi.template.service.ISwitchService;

/**
 * 交换机模板 服务层实现
 * 
 * @author TP
 * @date 2019-06-11
 */
@Service
public class SwitchServiceImpl implements ISwitchService 
{
	@Autowired
	private SwitchMapper switchMapper;

	/**
     * 查询交换机模板信息
     * 
     * @param serverId 交换机模板ID
     * @return 交换机模板信息
     */
    @Override
	public Switch selectSwitchById(Integer serverId)
	{
	    return switchMapper.selectSwitchById(serverId);
	}
	
	/**
     * 查询交换机模板列表
     * 
     * @param switchTempalte 交换机模板信息
     * @return 交换机模板集合
     */
	@Override
	public List<Switch> selectSwitchList(Switch switchTempalte)
	{
	    return switchMapper.selectSwitchList(switchTempalte);
	}
	
    /**
     * 新增交换机模板
     * 
     * @param switchTempalte 交换机模板信息
     * @return 结果
     */
	@Override
	public int insertSwitch(Switch switchTempalte)
	{
	    return switchMapper.insertSwitch(switchTempalte);
	}
	
	/**
     * 修改交换机模板
     * 
     * @param switchTempalte 交换机模板信息
     * @return 结果
     */
	@Override
	public int updateSwitch(Switch switchTempalte)
	{
	    return switchMapper.updateSwitch(switchTempalte);
	}

	/**
     * 删除交换机模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSwitchByIds(String ids)
	{
		return switchMapper.deleteSwitchByIds(Convert.toStrArray(ids));
	}
	
}
