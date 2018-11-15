package com.cronie.mengyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronie.mengyu.domain.Goods;
import com.cronie.mengyu.mapper.GoodsMapper;
import com.cronie.mengyu.service.IGoodsService;
import com.ruoyi.common.support.Convert;

/**
 * 投资品种 服务层实现
 * 
 * @author cronie
 * @date 2018-11-15
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
	@Autowired
	private GoodsMapper goodsMapper;

	/**
     * 查询投资品种信息
     * 
     * @param code 投资品种ID
     * @return 投资品种信息
     */
    @Override
	public Goods selectGoodsById(String code)
	{
	    return goodsMapper.selectGoodsById(code);
	}
	
	/**
     * 查询投资品种列表
     * 
     * @param goods 投资品种信息
     * @return 投资品种集合
     */
	@Override
	public List<Goods> selectGoodsList(Goods goods)
	{
	    return goodsMapper.selectGoodsList(goods);
	}
	
    /**
     * 新增投资品种
     * 
     * @param goods 投资品种信息
     * @return 结果
     */
	@Override
	public int insertGoods(Goods goods)
	{
	    return goodsMapper.insertGoods(goods);
	}
	
	/**
     * 修改投资品种
     * 
     * @param goods 投资品种信息
     * @return 结果
     */
	@Override
	public int updateGoods(Goods goods)
	{
	    return goodsMapper.updateGoods(goods);
	}

	/**
     * 删除投资品种对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsByIds(String ids)
	{
		return goodsMapper.deleteGoodsByIds(Convert.toStrArray(ids));
	}
	
}
