package com.cronie.mengyu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cronie.mengyu.mapper.GoodsTradeModelInsMapper;
import com.cronie.mengyu.domain.GoodsTradeModelIns;
import com.cronie.mengyu.service.IGoodsTradeModelInsService;
import com.ruoyi.common.support.Convert;

/**
 * 计划模型实例 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class GoodsTradeModelInsServiceImpl implements IGoodsTradeModelInsService 
{
	@Autowired
	private GoodsTradeModelInsMapper goodsTradeModelInsMapper;

	/**
     * 查询计划模型实例信息
     * 
     * @param insId 计划模型实例ID
     * @return 计划模型实例信息
     */
    @Override
	public GoodsTradeModelIns selectGoodsTradeModelInsById(Integer insId)
	{
	    return goodsTradeModelInsMapper.selectGoodsTradeModelInsById(insId);
	}
	
	/**
     * 查询计划模型实例列表
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 计划模型实例集合
     */
	@Override
	public List<GoodsTradeModelIns> selectGoodsTradeModelInsList(GoodsTradeModelIns goodsTradeModelIns)
	{
	    return goodsTradeModelInsMapper.selectGoodsTradeModelInsList(goodsTradeModelIns);
	}
	
    /**
     * 新增计划模型实例
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 结果
     */
	@Override
	public int insertGoodsTradeModelIns(GoodsTradeModelIns goodsTradeModelIns)
	{
	    return goodsTradeModelInsMapper.insertGoodsTradeModelIns(goodsTradeModelIns);
	}
	
	/**
     * 修改计划模型实例
     * 
     * @param goodsTradeModelIns 计划模型实例信息
     * @return 结果
     */
	@Override
	public int updateGoodsTradeModelIns(GoodsTradeModelIns goodsTradeModelIns)
	{
	    return goodsTradeModelInsMapper.updateGoodsTradeModelIns(goodsTradeModelIns);
	}

	/**
     * 删除计划模型实例对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsTradeModelInsByIds(String ids)
	{
		return goodsTradeModelInsMapper.deleteGoodsTradeModelInsByIds(Convert.toStrArray(ids));
	}
	
}
