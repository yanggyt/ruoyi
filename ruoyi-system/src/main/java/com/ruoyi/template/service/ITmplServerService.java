package com.ruoyi.template.service;

import com.ruoyi.template.domain.TmplServer;
import java.util.List;

/**
 * 服务器模板 服务层
 * 
 * @author TP
 * @date 2019-06-12
 */
public interface ITmplServerService 
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
     * 删除服务器模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmplServerByIds(String ids);
	
}
