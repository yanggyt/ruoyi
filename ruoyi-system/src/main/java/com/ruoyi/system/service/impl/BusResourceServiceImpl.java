package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusResourceMapper;
import com.ruoyi.system.domain.BusResource;
import com.ruoyi.system.service.IBusResourceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusResourceServiceImpl implements IBusResourceService 
{
	@Autowired
	private BusResourceMapper busResourceMapper;

	/**
     * 查询资源信息
     * 
     * @param resourceId 资源ID
     * @return 资源信息
     */
    @Override
	public BusResource selectBusResourceById(Long resourceId)
	{
	    return busResourceMapper.selectBusResourceById(resourceId);
	}
	
	/**
     * 查询资源列表
     * 
     * @param busResource 资源信息
     * @return 资源集合
     */
	@Override
	public List<BusResource> selectBusResourceList(BusResource busResource)
	{
	    return busResourceMapper.selectBusResourceList(busResource);
	}
	
    /**
     * 新增资源
     * 
     * @param busResource 资源信息
     * @return 结果
     */
	@Override
	public int insertBusResource(BusResource busResource)
	{
	    return busResourceMapper.insertBusResource(busResource);
	}
	
	/**
     * 修改资源
     * 
     * @param busResource 资源信息
     * @return 结果
     */
	@Override
	public int updateBusResource(BusResource busResource)
	{
	    return busResourceMapper.updateBusResource(busResource);
	}

	/**
     * 删除资源对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusResourceByIds(String ids)
	{
		return busResourceMapper.deleteBusResourceByIds(Convert.toStrArray(ids));
	}
	
}
