package com.ruoyi.wish.mapper;

import com.ruoyi.wish.domain.WishInfo;
import java.util.List;	

/**
 * 记录微心愿的 数据层
 * 
 * @author jyking
 * @date 2019-06-14
 */
public interface WishInfoMapper 
{
	/**
     * 查询记录微心愿的信息
     * 
     * @param wishId 记录微心愿的ID
     * @return 记录微心愿的信息
     */
	public WishInfo selectWishInfoById(Integer wishId);
	
	/**
     * 查询记录微心愿的列表
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 记录微心愿的集合
     */
	public List<WishInfo> selectWishInfoList(WishInfo wishInfo);
	
	/**
     * 新增记录微心愿的
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 结果
     */
	public int insertWishInfo(WishInfo wishInfo);
	
	/**
     * 修改记录微心愿的
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 结果
     */
	public int updateWishInfo(WishInfo wishInfo);
	
	/**
     * 删除记录微心愿的
     * 
     * @param wishId 记录微心愿的ID
     * @return 结果
     */
	public int deleteWishInfoById(Integer wishId);
	
	/**
     * 批量删除记录微心愿的
     * 
     * @param wishIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWishInfoByIds(String[] wishIds);
	
}