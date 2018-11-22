package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.GoodsTradeModel;
import java.util.List;	

/**
 * 品种计划模型 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface GoodsTradeModelMapper 
{
	/**
     * 查询品种计划模型信息
     * 
     * @param modelId 品种计划模型ID
     * @return 品种计划模型信息
     */
	public GoodsTradeModel selectGoodsTradeModelById(Integer modelId);
	
	/**
     * 查询品种计划模型列表
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 品种计划模型集合
     */
	public List<GoodsTradeModel> selectGoodsTradeModelList(GoodsTradeModel goodsTradeModel);
	
	/**
     * 新增品种计划模型
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 结果
     */
	public int insertGoodsTradeModel(GoodsTradeModel goodsTradeModel);
	
	/**
     * 修改品种计划模型
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 结果
     */
	public int updateGoodsTradeModel(GoodsTradeModel goodsTradeModel);
	
	/**
     * 删除品种计划模型
     * 
     * @param modelId 品种计划模型ID
     * @return 结果
     */
	public int deleteGoodsTradeModelById(Integer modelId);
	
	/**
     * 批量删除品种计划模型
     * 
     * @param modelIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsTradeModelByIds(String[] modelIds);
	
}