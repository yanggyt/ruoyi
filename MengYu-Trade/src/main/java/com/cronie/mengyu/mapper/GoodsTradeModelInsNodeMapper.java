package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.GoodsTradeModelInsNode;
import java.util.List;	

/**
 * 交易计划操作 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface GoodsTradeModelInsNodeMapper 
{
	/**
     * 查询交易计划操作信息
     * 
     * @param nodeId 交易计划操作ID
     * @return 交易计划操作信息
     */
	public GoodsTradeModelInsNode selectGoodsTradeModelInsNodeById(Integer nodeId);
	
	/**
     * 查询交易计划操作列表
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 交易计划操作集合
     */
	public List<GoodsTradeModelInsNode> selectGoodsTradeModelInsNodeList(GoodsTradeModelInsNode goodsTradeModelInsNode);
	
	/**
     * 新增交易计划操作
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 结果
     */
	public int insertGoodsTradeModelInsNode(GoodsTradeModelInsNode goodsTradeModelInsNode);
	
	/**
     * 修改交易计划操作
     * 
     * @param goodsTradeModelInsNode 交易计划操作信息
     * @return 结果
     */
	public int updateGoodsTradeModelInsNode(GoodsTradeModelInsNode goodsTradeModelInsNode);
	
	/**
     * 删除交易计划操作
     * 
     * @param nodeId 交易计划操作ID
     * @return 结果
     */
	public int deleteGoodsTradeModelInsNodeById(Integer nodeId);
	
	/**
     * 批量删除交易计划操作
     * 
     * @param nodeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsTradeModelInsNodeByIds(String[] nodeIds);
	
}