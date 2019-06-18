package com.ruoyi.network.service;

import com.ruoyi.network.domain.NetIpAddress;
import java.util.List;

/**
 * 网络IP 服务层
 * 
 * @author TP
 * @date 2019-06-15
 */
public interface INetIpAddressService 
{
	/**
     * 查询网络IP信息
     * 
     * @param ipAddressId 网络IPID
     * @return 网络IP信息
     */
	public NetIpAddress selectNetIpAddressById(Integer ipAddressId);
	
	/**
     * 查询网络IP列表
     * 
     * @param netIpAddress 网络IP信息
     * @return 网络IP集合
     */
	public List<NetIpAddress> selectNetIpAddressList(NetIpAddress netIpAddress);
	
	/**
     * 新增网络IP
     * 
     * @param netIpAddress 网络IP信息
     * @return 结果
     */
	public int insertNetIpAddress(NetIpAddress netIpAddress);
	
	/**
     * 修改网络IP
     * 
     * @param netIpAddress 网络IP信息
     * @return 结果
     */
	public int updateNetIpAddress(NetIpAddress netIpAddress);
		
	/**
     * 删除网络IP信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNetIpAddressByIds(String ids);
	
}
