package com.cronie.mengyu.mapper;

import com.cronie.mengyu.domain.Goods;
import java.util.List;	

/**
 * 投资品种 数据层
 * 
 * @author cronie
 * @date 2018-11-15
 */
public interface GoodsMapper 
{
	/**
     * 查询投资品种信息
     * 
     * @param code 投资品种ID
     * @return 投资品种信息
     */
	public Goods selectGoodsById(String code);
	
	/**
     * 查询投资品种列表
     * 
     * @param goods 投资品种信息
     * @return 投资品种集合
     */
	public List<Goods> selectGoodsList(Goods goods);
	
	/**
     * 新增投资品种
     * 
     * @param goods 投资品种信息
     * @return 结果
     */
	public int insertGoods(Goods goods);
	
	/**
     * 修改投资品种
     * 
     * @param goods 投资品种信息
     * @return 结果
     */
	public int updateGoods(Goods goods);
	
	/**
     * 删除投资品种
     * 
     * @param code 投资品种ID
     * @return 结果
     */
	public int deleteGoodsById(String code);
	
	/**
     * 批量删除投资品种
     * 
     * @param codes 需要删除的数据ID
     * @return 结果
     */
	public int deleteGoodsByIds(String[] codes);
	
}