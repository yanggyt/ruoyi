package com.cronie.mengyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronie.mengyu.domain.GoodsTradeModelInsNode;
import com.cronie.mengyu.mapper.GoodsTradeModelInsNodeMapper;
import com.cronie.mengyu.service.IGoodsTradeModelInsNodeService;
import com.ruoyi.common.support.Convert;

/**
 * 交易计划操作 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class GoodsTradeModelInsNodeServiceImpl implements IGoodsTradeModelInsNodeService 
{
	@Autowired
	private GoodsTradeModelInsNodeMapper goodsTradeModelInsNodeMapper;

	/**
     * 查询交易计划操作信息
     * 
     * @param nodeId 交易计划操作ID
     * @return 交易计划操作信息
     */
    @Override
	public GoodsTradeModelInsNode selectGoodsTradeModelInsNodeById(Integer nodeId)
	{
	    return goodsTradeModelInsNodeMapper.selectGoodsTradeModelInsNodeById(nodeId);
	}
	
	/**
     * 查询交易计划操作列表
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 交易计划操作集合
     */
	@Override
	public List<GoodsTradeModelInsNode> selectGoodsTradeModelInsNodeList(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{
	    return goodsTradeModelInsNodeMapper.selectGoodsTradeModelInsNodeList(goodsTradeModelInsNode);
	}
	
    /**
     * 新增交易计划操作
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 结果
     */
	@Override
	public int insertGoodsTradeModelInsNode(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{
	    return goodsTradeModelInsNodeMapper.insertGoodsTradeModelInsNode(goodsTradeModelInsNode);
	}
	
	/**
     * 修改交易计划操作
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 结果
     */
	@Override
	public int updateGoodsTradeModelInsNode(GoodsTradeModelInsNode goodsTradeModelInsNode)
	{
	    return goodsTradeModelInsNodeMapper.updateGoodsTradeModelInsNode(goodsTradeModelInsNode);
	}

	/**
     * 删除交易计划操作对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsTradeModelInsNodeByIds(String ids)
	{
		return goodsTradeModelInsNodeMapper.deleteGoodsTradeModelInsNodeByIds(Convert.toStrArray(ids));
	}
	
}
