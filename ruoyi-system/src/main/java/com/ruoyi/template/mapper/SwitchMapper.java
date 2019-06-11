package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.Switch;
import java.util.List;	

/**
 * 交换机模板 数据层
 * 
 * @author TP
 * @date 2019-06-12
 */
public interface SwitchMapper 
{
	/**
     * 查询交换机模板信息
     * 
     * @param switchId 交换机模板ID
     * @return 交换机模板信息
     */
	public Switch selectSwitchById(Integer switchId);
	
	/**
     * 查询交换机模板列表
     * 
     * @param switchTemplate 交换机模板信息
     * @return 交换机模板集合
     */
	public List<Switch> selectSwitchList(Switch switchTemplate);
	
	/**
     * 新增交换机模板
     * 
     * @param switchTemplate 交换机模板信息
     * @return 结果
     */
	public int insertSwitch(Switch switchTemplate);
	
	/**
     * 修改交换机模板
     * 
     * @param switchTemplate 交换机模板信息
     * @return 结果
     */
	public int updateSwitch(Switch switchTemplate);
	
	/**
     * 删除交换机模板
     * 
     * @param switchId 交换机模板ID
     * @return 结果
     */
	public int deleteSwitchById(Integer switchId);
	
	/**
     * 批量删除交换机模板
     * 
     * @param switchIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSwitchByIds(String[] switchIds);
	
}