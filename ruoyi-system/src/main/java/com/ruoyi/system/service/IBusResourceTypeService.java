package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusResourceType;
import java.util.List;

/**
 * 资源分类 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusResourceTypeService 
{
	/**
     * 查询资源分类信息
     * 
     * @param typeId 资源分类ID
     * @return 资源分类信息
     */
	public BusResourceType selectBusResourceTypeById(Long typeId);
	
	/**
     * 查询资源分类列表
     * 
     * @param busResourceType 资源分类信息
     * @return 资源分类集合
     */
	public List<BusResourceType> selectBusResourceTypeList(BusResourceType busResourceType);
	
	/**
     * 新增资源分类
     * 
     * @param busResourceType 资源分类信息
     * @return 结果
     */
	public int insertBusResourceType(BusResourceType busResourceType);
	
	/**
     * 修改资源分类
     * 
     * @param busResourceType 资源分类信息
     * @return 结果
     */
	public int updateBusResourceType(BusResourceType busResourceType);
		
	/**
     * 删除资源分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceTypeByIds(String ids);
	
}
