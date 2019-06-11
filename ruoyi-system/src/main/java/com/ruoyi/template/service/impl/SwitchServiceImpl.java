package com.ruoyi.template.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.template.mapper.SwitchMapper;
import com.ruoyi.template.domain.Switch;
import com.ruoyi.template.service.ISwitchService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交换机模板 服务层实现
 * 
 * @author TP
 * @date 2019-06-12
 */
@Service
public class SwitchServiceImpl implements ISwitchService 
{
	@Autowired
	private SwitchMapper switchMapper;

	/**
     * 查询交换机模板信息
     * 
     * @param switchId 交换机模板ID
     * @return 交换机模板信息
     */
    @Override
	public Switch selectSwitchById(Integer switchId)
	{
	    return switchMapper.selectSwitchById(switchId);
	}
	
	/**
     * 查询交换机模板列表
     * 
     * @param switchTemplate 交换机模板信息
     * @return 交换机模板集合
     */
	@Override
	public List<Switch> selectSwitchList(Switch switchTemplate)
	{
	    return switchMapper.selectSwitchList(switchTemplate);
	}
	
    /**
     * 新增交换机模板
     * 
     * @param switchTemplate 交换机模板信息
     * @return 结果
     */
	@Override
	public int insertSwitch(Switch switchTemplate)
	{
	    return switchMapper.insertSwitch(switchTemplate);
	}
	
	/**
     * 修改交换机模板
     * 
     * @param switchTemplate 交换机模板信息
     * @return 结果
     */
	@Override
	public int updateSwitch(Switch switchTemplate)
	{
	    return switchMapper.updateSwitch(switchTemplate);
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
