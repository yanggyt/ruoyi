package com.ruoyi.network.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.network.domain.NetIpAddress;
import com.ruoyi.network.mapper.NetIpAddressMapper;
import com.ruoyi.network.service.INetIpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 网络IP 服务层实现
 * 
 * @author TP
 * @date 2019-06-15
 */
@Service
public class NetIpAddressServiceImpl implements INetIpAddressService 
{
	@Autowired
	private NetIpAddressMapper netIpAddressMapper;

	/**
     * 查询网络IP信息
     * 
     * @param ipAddressId 网络IPID
     * @return 网络IP信息
     */
    @Override
	public NetIpAddress selectNetIpAddressById(Integer ipAddressId)
	{
	    return netIpAddressMapper.selectNetIpAddressById(ipAddressId);
	}
	
	/**
     * 查询网络IP列表
     * 
     * @param netIpAddress 网络IP信息
     * @return 网络IP集合
     */
	@Override
	public List<NetIpAddress> selectNetIpAddressList(NetIpAddress netIpAddress)
	{
	    return netIpAddressMapper.selectNetIpAddressList(netIpAddress);
	}
	
    /**
     * 新增网络IP
     * 
     * @param netIpAddress 网络IP信息
     * @return 结果
     */
	@Override
	public int insertNetIpAddress(NetIpAddress netIpAddress)
	{
	    return netIpAddressMapper.insertNetIpAddress(netIpAddress);
	}
	
	/**
     * 修改网络IP
     * 
     * @param netIpAddress 网络IP信息
     * @return 结果
     */
	@Override
	public int updateNetIpAddress(NetIpAddress netIpAddress)
	{
	    return netIpAddressMapper.updateNetIpAddress(netIpAddress);
	}

	/**
     * 删除网络IP对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNetIpAddressByIds(String ids)
	{
		return netIpAddressMapper.deleteNetIpAddressByIds(Convert.toStrArray(ids));
	}
	
}
