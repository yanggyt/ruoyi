package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusReqProgress;
import java.util.List;

/**
 * 资源需求进度 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusReqProgressService 
{
	/**
     * 查询资源需求进度信息
     * 
     * @param progressId 资源需求进度ID
     * @return 资源需求进度信息
     */
	public BusReqProgress selectBusReqProgressById(Long progressId);
	
	/**
     * 查询资源需求进度列表
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 资源需求进度集合
     */
	public List<BusReqProgress> selectBusReqProgressList(BusReqProgress busReqProgress);
	
	/**
     * 新增资源需求进度
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 结果
     */
	public int insertBusReqProgress(BusReqProgress busReqProgress);
	
	/**
     * 修改资源需求进度
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 结果
     */
	public int updateBusReqProgress(BusReqProgress busReqProgress);
		
	/**
     * 删除资源需求进度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqProgressByIds(String ids);
	
}
