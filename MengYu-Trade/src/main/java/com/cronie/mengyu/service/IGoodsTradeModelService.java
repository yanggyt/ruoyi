package com.cronie.mengyu.service;

import com.cronie.mengyu.domain.GoodsTradeModel;
import java.util.List;

/**
 * 品种计划模型 服务层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface IGoodsTradeModelService 
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
     * 删除品种计划模型信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsTradeModelByIds(String ids);
	
}
