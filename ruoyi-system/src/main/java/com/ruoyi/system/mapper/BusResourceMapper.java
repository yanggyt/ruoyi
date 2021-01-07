package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusResource;
import java.util.List;	

/**
 * 资源 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusResourceMapper 
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
     * 删除资源
     * 
     * @param resourceId 资源ID
     * @return 结果
     */
	public int deleteBusResourceById(Long resourceId);
	
	/**
     * 批量删除资源
     * 
     * @param resourceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceByIds(String[] resourceIds);
	
}