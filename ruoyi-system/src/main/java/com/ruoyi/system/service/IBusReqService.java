package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusReq;
import java.util.List;

/**
 * 资源需求 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusReqService 
{
	/**
     * 查询资源需求信息
     * 
     * @param reqId 资源需求ID
     * @return 资源需求信息
     */
	public BusReq selectBusReqById(Long reqId);
	
	/**
     * 查询资源需求列表
     * 
     * @param busReq 资源需求信息
     * @return 资源需求集合
     */
	public List<BusReq> selectBusReqList(BusReq busReq);
	
	/**
     * 新增资源需求
     * 
     * @param busReq 资源需求信息
     * @return 结果
     */
	public int insertBusReq(BusReq busReq);
	
	/**
     * 修改资源需求
     * 
     * @param busReq 资源需求信息
     * @return 结果
     */
	public int updateBusReq(BusReq busReq);
		
	/**
     * 删除资源需求信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqByIds(String ids);
	
}
