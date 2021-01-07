package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusAccessory;
import java.util.List;

/**
 * 附件 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusAccessoryService 
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
     * 删除附件信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusAccessoryByIds(String ids);
	
}
