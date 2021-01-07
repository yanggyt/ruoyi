package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusAccessoryMapper;
import com.ruoyi.system.domain.BusAccessory;
import com.ruoyi.system.service.IBusAccessoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 附件 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusAccessoryServiceImpl implements IBusAccessoryService 
{
	@Autowired
	private BusAccessoryMapper busAccessoryMapper;

	/**
     * 查询附件信息
     * 
     * @param accessoryId 附件ID
     * @return 附件信息
     */
    @Override
	public BusAccessory selectBusAccessoryById(Long accessoryId)
	{
	    return busAccessoryMapper.selectBusAccessoryById(accessoryId);
	}
	
	/**
     * 查询附件列表
     * 
     * @param busAccessory 附件信息
     * @return 附件集合
     */
	@Override
	public List<BusAccessory> selectBusAccessoryList(BusAccessory busAccessory)
	{
	    return busAccessoryMapper.selectBusAccessoryList(busAccessory);
	}
	
    /**
     * 新增附件
     * 
     * @param busAccessory 附件信息
     * @return 结果
     */
	@Override
	public int insertBusAccessory(BusAccessory busAccessory)
	{
	    return busAccessoryMapper.insertBusAccessory(busAccessory);
	}
	
	/**
     * 修改附件
     * 
     * @param busAccessory 附件信息
     * @return 结果
     */
	@Override
	public int updateBusAccessory(BusAccessory busAccessory)
	{
	    return busAccessoryMapper.updateBusAccessory(busAccessory);
	}

	/**
     * 删除附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusAccessoryByIds(String ids)
	{
		return busAccessoryMapper.deleteBusAccessoryByIds(Convert.toStrArray(ids));
	}
	
}
