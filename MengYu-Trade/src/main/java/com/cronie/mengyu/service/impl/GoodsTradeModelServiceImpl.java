package com.cronie.mengyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cronie.mengyu.mapper.GoodsTradeModelMapper;
import com.cronie.mengyu.domain.GoodsTradeModel;
import com.cronie.mengyu.service.IGoodsTradeModelService;
import com.ruoyi.common.support.Convert;

/**
 * 品种计划模型 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class GoodsTradeModelServiceImpl implements IGoodsTradeModelService 
{
	@Autowired
	private GoodsTradeModelMapper goodsTradeModelMapper;

	/**
     * 查询品种计划模型信息
     * 
     * @param modelId 品种计划模型ID
     * @return 品种计划模型信息
     */
    @Override
	public GoodsTradeModel selectGoodsTradeModelById(Integer modelId)
	{
	    return goodsTradeModelMapper.selectGoodsTradeModelById(modelId);
	}
	
	/**
     * 查询品种计划模型列表
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 品种计划模型集合
     */
	@Override
	public List<GoodsTradeModel> selectGoodsTradeModelList(GoodsTradeModel goodsTradeModel)
	{
	    return goodsTradeModelMapper.selectGoodsTradeModelList(goodsTradeModel);
	}
	
    /**
     * 新增品种计划模型
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 结果
     */
	@Override
	public int insertGoodsTradeModel(GoodsTradeModel goodsTradeModel)
	{
	    return goodsTradeModelMapper.insertGoodsTradeModel(goodsTradeModel);
	}
	
	/**
     * 修改品种计划模型
     * 
     * @param goodsTradeModel 品种计划模型信息
     * @return 结果
     */
	@Override
	public int updateGoodsTradeModel(GoodsTradeModel goodsTradeModel)
	{
	    return goodsTradeModelMapper.updateGoodsTradeModel(goodsTradeModel);
	}

	/**
     * 删除品种计划模型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsTradeModelByIds(String ids)
	{
		return goodsTradeModelMapper.deleteGoodsTradeModelByIds(Convert.toStrArray(ids));
	}
	
}
