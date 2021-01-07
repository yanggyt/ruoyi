package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusUserBrowseMapper;
import com.ruoyi.system.domain.BusUserBrowse;
import com.ruoyi.system.service.IBusUserBrowseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户浏览足迹 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusUserBrowseServiceImpl implements IBusUserBrowseService 
{
	@Autowired
	private BusUserBrowseMapper busUserBrowseMapper;

	/**
     * 查询用户浏览足迹信息
     * 
     * @param browseId 用户浏览足迹ID
     * @return 用户浏览足迹信息
     */
    @Override
	public BusUserBrowse selectBusUserBrowseById(Long browseId)
	{
	    return busUserBrowseMapper.selectBusUserBrowseById(browseId);
	}
	
	/**
     * 查询用户浏览足迹列表
     * 
     * @param busUserBrowse 用户浏览足迹信息
     * @return 用户浏览足迹集合
     */
	@Override
	public List<BusUserBrowse> selectBusUserBrowseList(BusUserBrowse busUserBrowse)
	{
	    return busUserBrowseMapper.selectBusUserBrowseList(busUserBrowse);
	}
	
    /**
     * 新增用户浏览足迹
     * 
     * @param busUserBrowse 用户浏览足迹信息
     * @return 结果
     */
	@Override
	public int insertBusUserBrowse(BusUserBrowse busUserBrowse)
	{
	    return busUserBrowseMapper.insertBusUserBrowse(busUserBrowse);
	}
	
	/**
     * 修改用户浏览足迹
     * 
     * @param busUserBrowse 用户浏览足迹信息
     * @return 结果
     */
	@Override
	public int updateBusUserBrowse(BusUserBrowse busUserBrowse)
	{
	    return busUserBrowseMapper.updateBusUserBrowse(busUserBrowse);
	}

	/**
     * 删除用户浏览足迹对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusUserBrowseByIds(String ids)
	{
		return busUserBrowseMapper.deleteBusUserBrowseByIds(Convert.toStrArray(ids));
	}
	
}
