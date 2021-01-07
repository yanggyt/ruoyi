package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusResourceTypeMapper;
import com.ruoyi.system.domain.BusResourceType;
import com.ruoyi.system.service.IBusResourceTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源分类 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusResourceTypeServiceImpl implements IBusResourceTypeService 
{
	@Autowired
	private BusResourceTypeMapper busResourceTypeMapper;

	/**
     * 查询资源分类信息
     * 
     * @param typeId 资源分类ID
     * @return 资源分类信息
     */
    @Override
	public BusResourceType selectBusResourceTypeById(Long typeId)
	{
	    return busResourceTypeMapper.selectBusResourceTypeById(typeId);
	}
	
	/**
     * 查询资源分类列表
     * 
     * @param busResourceType 资源分类信息
     * @return 资源分类集合
     */
	@Override
	public List<BusResourceType> selectBusResourceTypeList(BusResourceType busResourceType)
	{
	    return busResourceTypeMapper.selectBusResourceTypeList(busResourceType);
	}
	
    /**
     * 新增资源分类
     * 
     * @param busResourceType 资源分类信息
     * @return 结果
     */
	@Override
	public int insertBusResourceType(BusResourceType busResourceType)
	{
	    return busResourceTypeMapper.insertBusResourceType(busResourceType);
	}
	
	/**
     * 修改资源分类
     * 
     * @param busResourceType 资源分类信息
     * @return 结果
     */
	@Override
	public int updateBusResourceType(BusResourceType busResourceType)
	{
	    return busResourceTypeMapper.updateBusResourceType(busResourceType);
	}

	/**
     * 删除资源分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusResourceTypeByIds(String ids)
	{
		return busResourceTypeMapper.deleteBusResourceTypeByIds(Convert.toStrArray(ids));
	}
	
}
