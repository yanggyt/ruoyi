package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusReqProgressMapper;
import com.ruoyi.system.domain.BusReqProgress;
import com.ruoyi.system.service.IBusReqProgressService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源需求进度 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusReqProgressServiceImpl implements IBusReqProgressService 
{
	@Autowired
	private BusReqProgressMapper busReqProgressMapper;

	/**
     * 查询资源需求进度信息
     * 
     * @param progressId 资源需求进度ID
     * @return 资源需求进度信息
     */
    @Override
	public BusReqProgress selectBusReqProgressById(Long progressId)
	{
	    return busReqProgressMapper.selectBusReqProgressById(progressId);
	}
	
	/**
     * 查询资源需求进度列表
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 资源需求进度集合
     */
	@Override
	public List<BusReqProgress> selectBusReqProgressList(BusReqProgress busReqProgress)
	{
	    return busReqProgressMapper.selectBusReqProgressList(busReqProgress);
	}
	
    /**
     * 新增资源需求进度
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 结果
     */
	@Override
	public int insertBusReqProgress(BusReqProgress busReqProgress)
	{
	    return busReqProgressMapper.insertBusReqProgress(busReqProgress);
	}
	
	/**
     * 修改资源需求进度
     * 
     * @param busReqProgress 资源需求进度信息
     * @return 结果
     */
	@Override
	public int updateBusReqProgress(BusReqProgress busReqProgress)
	{
	    return busReqProgressMapper.updateBusReqProgress(busReqProgress);
	}

	/**
     * 删除资源需求进度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusReqProgressByIds(String ids)
	{
		return busReqProgressMapper.deleteBusReqProgressByIds(Convert.toStrArray(ids));
	}
	
}
