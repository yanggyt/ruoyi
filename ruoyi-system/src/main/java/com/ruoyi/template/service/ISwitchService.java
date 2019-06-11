package com.ruoyi.template.service;

import java.util.List;

import com.ruoyi.template.domain.Switch;

/**
 * 交换机模板 服务层
 * 
 * @author TP
 * @date 2019-06-11
 */
public interface ISwitchService 
{
	/**
     * 查询交换机模板信息
     * 
     * @param serverId 交换机模板ID
     * @return 交换机模板信息
     */
	public Switch selectSwitchById(Integer serverId);
	
	/**
     * 查询交换机模板列表
     * 
     * @param switchTmpl 交换机模板信息
     * @return 交换机模板集合
     */
	public List<Switch> selectSwitchList(Switch switchTmpl);
	
	/**
     * 新增交换机模板
     * 
     * @param switchTmpl 交换机模板信息
     * @return 结果
     */
	public int insertSwitch(Switch switchTmpl);
	
	/**
     * 修改交换机模板
     * 
     * @param switchTmpl 交换机模板信息
     * @return 结果
     */
	public int updateSwitch(Switch switchTmpl);
		
	/**
     * 删除交换机模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSwitchByIds(String ids);
	
}
