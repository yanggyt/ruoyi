package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusReq;
import java.util.List;	

/**
 * 资源需求 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusReqMapper 
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
     * 删除资源需求
     * 
     * @param reqId 资源需求ID
     * @return 结果
     */
	public int deleteBusReqById(Long reqId);
	
	/**
     * 批量删除资源需求
     * 
     * @param reqIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqByIds(String[] reqIds);
	
}