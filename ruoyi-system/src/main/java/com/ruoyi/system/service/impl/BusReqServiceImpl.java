package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusReqMapper;
import com.ruoyi.system.domain.BusReq;
import com.ruoyi.system.service.IBusReqService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源需求 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusReqServiceImpl implements IBusReqService 
{
	@Autowired
	private BusReqMapper busReqMapper;

	/**
     * 查询资源需求信息
     * 
     * @param reqId 资源需求ID
     * @return 资源需求信息
     */
    @Override
	public BusReq selectBusReqById(Long reqId)
	{
	    return busReqMapper.selectBusReqById(reqId);
	}
	
	/**
     * 查询资源需求列表
     * 
     * @param busReq 资源需求信息
     * @return 资源需求集合
     */
	@Override
	public List<BusReq> selectBusReqList(BusReq busReq)
	{
	    return busReqMapper.selectBusReqList(busReq);
	}
	
    /**
     * 新增资源需求
     * 
     * @param busReq 资源需求信息
     * @return 结果
     */
	@Override
	public int insertBusReq(BusReq busReq)
	{
	    return busReqMapper.insertBusReq(busReq);
	}
	
	/**
     * 修改资源需求
     * 
     * @param busReq 资源需求信息
     * @return 结果
     */
	@Override
	public int updateBusReq(BusReq busReq)
	{
	    return busReqMapper.updateBusReq(busReq);
	}

	/**
     * 删除资源需求对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusReqByIds(String ids)
	{
		return busReqMapper.deleteBusReqByIds(Convert.toStrArray(ids));
	}
	
}
