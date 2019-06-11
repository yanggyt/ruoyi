package com.ruoyi.template.service;

import com.ruoyi.template.domain.Server;
import java.util.List;

/**
 * 服务器模板 服务层
 * 
 * @author TP
 * @date 2019-06-11
 */
public interface IServerService 
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
     * 删除服务器模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteServerByIds(String ids);
	
}
