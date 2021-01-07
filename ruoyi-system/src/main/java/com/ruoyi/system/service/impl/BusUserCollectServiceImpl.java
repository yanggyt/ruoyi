package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusUserCollectMapper;
import com.ruoyi.system.domain.BusUserCollect;
import com.ruoyi.system.service.IBusUserCollectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户收藏 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusUserCollectServiceImpl implements IBusUserCollectService 
{
	@Autowired
	private BusUserCollectMapper busUserCollectMapper;

	/**
     * 查询用户收藏信息
     * 
     * @param collectId 用户收藏ID
     * @return 用户收藏信息
     */
    @Override
	public BusUserCollect selectBusUserCollectById(Long collectId)
	{
	    return busUserCollectMapper.selectBusUserCollectById(collectId);
	}
	
	/**
     * 查询用户收藏列表
     * 
     * @param busUserCollect 用户收藏信息
     * @return 用户收藏集合
     */
	@Override
	public List<BusUserCollect> selectBusUserCollectList(BusUserCollect busUserCollect)
	{
	    return busUserCollectMapper.selectBusUserCollectList(busUserCollect);
	}
	
    /**
     * 新增用户收藏
     * 
     * @param busUserCollect 用户收藏信息
     * @return 结果
     */
	@Override
	public int insertBusUserCollect(BusUserCollect busUserCollect)
	{
	    return busUserCollectMapper.insertBusUserCollect(busUserCollect);
	}
	
	/**
     * 修改用户收藏
     * 
     * @param busUserCollect 用户收藏信息
     * @return 结果
     */
	@Override
	public int updateBusUserCollect(BusUserCollect busUserCollect)
	{
	    return busUserCollectMapper.updateBusUserCollect(busUserCollect);
	}

	/**
     * 删除用户收藏对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusUserCollectByIds(String ids)
	{
		return busUserCollectMapper.deleteBusUserCollectByIds(Convert.toStrArray(ids));
	}
	
}
