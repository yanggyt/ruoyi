package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.GoodsTradeModelIns;
import java.util.List;	

/**
 * 计划模型实例 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface GoodsTradeModelInsMapper 
{
	/**
     * 查询计划模型实例信息
     * 
     * @param insId 计划模型实例ID
     * @return 计划模型实例信息
     */
	public GoodsTradeModelIns selectGoodsTradeModelInsById(Integer insId);
	
	/**
     * 查询计划模型实例列表
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 计划模型实例集合
     */
	public List<GoodsTradeModelIns> selectGoodsTradeModelInsList(GoodsTradeModelIns goodsTradeModelIns);
	
	/**
     * 新增计划模型实例
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 结果
     */
	public int insertGoodsTradeModelIns(GoodsTradeModelIns goodsTradeModelIns);
	
	/**
     * 修改计划模型实例
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 结果
     */
	public int updateGoodsTradeModelIns(GoodsTradeModelIns goodsTradeModelIns);
	
	/**
     * 删除计划模型实例
     * 
     * @param insId 计划模型实例ID
     * @return 结果
     */
	public int deleteGoodsTradeModelInsById(Integer insId);
	
	/**
     * 批量删除计划模型实例
     * 
     * @param insIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsTradeModelInsByIds(String[] insIds);
	
}