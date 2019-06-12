package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplSwitch;
import java.util.List;	

/**
 * 交换机模板 数据层
 * 
 * @author TP
 * @date 2019-06-12
 */
public interface TmplSwitchMapper 
{
	/**
     * 查询交换机模板信息
     * 
     * @param switchId 交换机模板ID
     * @return 交换机模板信息
     */
	public TmplSwitch selectTmplSwitchById(Integer switchId);
	
	/**
     * 查询交换机模板列表
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 交换机模板集合
     */
	public List<TmplSwitch> selectTmplSwitchList(TmplSwitch tmplSwitch);
	
	/**
     * 新增交换机模板
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
	public int insertTmplSwitch(TmplSwitch tmplSwitch);
	
	/**
     * 修改交换机模板
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
	public int updateTmplSwitch(TmplSwitch tmplSwitch);
	
	/**
     * 删除交换机模板
     * 
     * @param switchId 交换机模板ID
     * @return 结果
     */
	public int deleteTmplSwitchById(Integer switchId);
	
	/**
     * 批量删除交换机模板
     * 
     * @param switchIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmplSwitchByIds(String[] switchIds);
	
}