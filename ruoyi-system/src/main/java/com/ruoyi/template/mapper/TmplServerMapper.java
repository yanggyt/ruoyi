package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplServer;
import java.util.List;	

/**
 * 服务器模板 数据层
 * 
 * @author TP
 * @date 2019-06-12
 */
public interface TmplServerMapper 
{
	/**
     * 查询服务器模板信息
     * 
     * @param serverId 服务器模板ID
     * @return 服务器模板信息
     */
	public TmplServer selectTmplServerById(Integer serverId);
	
	/**
     * 查询服务器模板列表
     * 
     * @param tmplServer 服务器模板信息
     * @return 服务器模板集合
     */
	public List<TmplServer> selectTmplServerList(TmplServer tmplServer);
	
	/**
     * 新增服务器模板
     * 
     * @param tmplServer 服务器模板信息
     * @return 结果
     */
	public int insertTmplServer(TmplServer tmplServer);
	
	/**
     * 修改服务器模板
     * 
     * @param tmplServer 服务器模板信息
     * @return 结果
     */
	public int updateTmplServer(TmplServer tmplServer);
	
	/**
     * 删除服务器模板
     * 
     * @param serverId 服务器模板ID
     * @return 结果
     */
	public int deleteTmplServerById(Integer serverId);
	
	/**
     * 批量删除服务器模板
     * 
     * @param serverIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmplServerByIds(String[] serverIds);
	
}