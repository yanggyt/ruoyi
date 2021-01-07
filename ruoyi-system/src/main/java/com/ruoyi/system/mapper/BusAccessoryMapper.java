package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusAccessory;
import java.util.List;	

/**
 * 附件 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusAccessoryMapper 
{
	/**
     * 查询附件信息
     * 
     * @param accessoryId 附件ID
     * @return 附件信息
     */
	public BusAccessory selectBusAccessoryById(Long accessoryId);
	
	/**
     * 查询附件列表
     * 
     * @param busAccessory 附件信息
     * @return 附件集合
     */
	public List<BusAccessory> selectBusAccessoryList(BusAccessory busAccessory);
	
	/**
     * 新增附件
     * 
     * @param busAccessory 附件信息
     * @return 结果
     */
	public int insertBusAccessory(BusAccessory busAccessory);
	
	/**
     * 修改附件
     * 
     * @param busAccessory 附件信息
     * @return 结果
     */
	public int updateBusAccessory(BusAccessory busAccessory);
	
	/**
     * 删除附件
     * 
     * @param accessoryId 附件ID
     * @return 结果
     */
	public int deleteBusAccessoryById(Long accessoryId);
	
	/**
     * 批量删除附件
     * 
     * @param accessoryIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusAccessoryByIds(String[] accessoryIds);
	
}