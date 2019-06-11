package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.Server;
import java.util.List;	

/**
 * 服务器模板 数据层
 * 
 * @author TP
 * @date 2019-06-11
 */
public interface ServerMapper 
{
	/**
     * 查询服务器模板信息
     * 
     * @param serverId 服务器模板ID
     * @return 服务器模板信息
     */
	public Server selectServerById(Integer serverId);
	
	/**
     * 查询服务器模板列表
     * 
     * @param server 服务器模板信息
     * @return 服务器模板集合
     */
	public List<Server> selectServerList(Server server);
	
	/**
     * 新增服务器模板
     * 
     * @param server 服务器模板信息
     * @return 结果
     */
	public int insertServer(Server server);
	
	/**
     * 修改服务器模板
     * 
     * @param server 服务器模板信息
     * @return 结果
     */
	public int updateServer(Server server);
	
	/**
     * 删除服务器模板
     * 
     * @param serverId 服务器模板ID
     * @return 结果
     */
	public int deleteServerById(Integer serverId);
	
	/**
     * 批量删除服务器模板
     * 
     * @param serverIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteServerByIds(String[] serverIds);
	
}