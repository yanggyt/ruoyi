package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusResource;
import java.util.List;

/**
 * 资源 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusResourceService 
{
	/**
     * 查询资源信息
     * 
     * @param resourceId 资源ID
     * @return 资源信息
     */
	public BusResource selectBusResourceById(Long resourceId);
	
	/**
     * 查询资源列表
     * 
     * @param busResource 资源信息
     * @return 资源集合
     */
	public List<BusResource> selectBusResourceList(BusResource busResource);
	
	/**
     * 新增资源
     * 
     * @param busResource 资源信息
     * @return 结果
     */
	public int insertBusResource(BusResource busResource);
	
	/**
     * 修改资源
     * 
     * @param busResource 资源信息
     * @return 结果
     */
	public int updateBusResource(BusResource busResource);
		
	/**
     * 删除资源信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceByIds(String ids);
	
}
