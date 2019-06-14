package com.ruoyi.wish.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wish.mapper.WishInfoMapper;
import com.ruoyi.wish.domain.WishInfo;
import com.ruoyi.wish.service.IWishInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 记录微心愿的 服务层实现
 * 
 * @author jyking
 * @date 2019-06-14
 */
@Service
public class WishInfoServiceImpl implements IWishInfoService 
{
	@Autowired
	private WishInfoMapper wishInfoMapper;

	/**
     * 查询记录微心愿的信息
     * 
     * @param wishId 记录微心愿的ID
     * @return 记录微心愿的信息
     */
    @Override
	public WishInfo selectWishInfoById(Integer wishId)
	{
	    return wishInfoMapper.selectWishInfoById(wishId);
	}
	
	/**
     * 查询记录微心愿的列表
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 记录微心愿的集合
     */
	@Override
	public List<WishInfo> selectWishInfoList(WishInfo wishInfo)
	{
	    return wishInfoMapper.selectWishInfoList(wishInfo);
	}
	
    /**
     * 新增记录微心愿的
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 结果
     */
	@Override
	public int insertWishInfo(WishInfo wishInfo)
	{
	    return wishInfoMapper.insertWishInfo(wishInfo);
	}
	
	/**
     * 修改记录微心愿的
     * 
     * @param wishInfo 记录微心愿的信息
     * @return 结果
     */
	@Override
	public int updateWishInfo(WishInfo wishInfo)
	{
	    return wishInfoMapper.updateWishInfo(wishInfo);
	}

	/**
     * 删除记录微心愿的对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWishInfoByIds(String ids)
	{
		return wishInfoMapper.deleteWishInfoByIds(Convert.toStrArray(ids));
	}
	
}
