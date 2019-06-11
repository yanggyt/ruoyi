package com.ruoyi.template.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.template.domain.Server;
import com.ruoyi.template.mapper.ServerMapper;
import com.ruoyi.template.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务器模板 服务层实现
 * 
 * @author TP
 * @date 2019-06-11
 */
@Service
public class ServerServiceImpl implements IServerService 
{
	@Autowired
	private ServerMapper serverMapper;

	/**
     * 查询服务器模板信息
     * 
     * @param serverId 服务器模板ID
     * @return 服务器模板信息
     */
    @Override
	public Server selectServerById(Integer serverId)
	{
	    return serverMapper.selectServerById(serverId);
	}
	
	/**
     * 查询服务器模板列表
     * 
     * @param server 服务器模板信息
     * @return 服务器模板集合
     */
	@Override
	public List<Server> selectServerList(Server server)
	{
	    return serverMapper.selectServerList(server);
	}
	
    /**
     * 新增服务器模板
     * 
     * @param server 服务器模板信息
     * @return 结果
     */
	@Override
	public int insertServer(Server server)
	{
	    return serverMapper.insertServer(server);
	}
	
	/**
     * 修改服务器模板
     * 
     * @param server 服务器模板信息
     * @return 结果
     */
	@Override
	public int updateServer(Server server)
	{
	    return serverMapper.updateServer(server);
	}

	/**
     * 删除服务器模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteServerByIds(String ids)
	{
		return serverMapper.deleteServerByIds(Convert.toStrArray(ids));
	}
	
}
