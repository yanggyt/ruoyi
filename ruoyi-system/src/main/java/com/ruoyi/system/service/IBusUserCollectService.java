package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusUserCollect;
import java.util.List;

/**
 * 用户收藏 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusUserCollectService 
{
	/**
     * 查询用户收藏信息
     * 
     * @param collectId 用户收藏ID
     * @return 用户收藏信息
     */
	public BusUserCollect selectBusUserCollectById(Long collectId);
	
	/**
     * 查询用户收藏列表
     * 
     * @param busUserCollect 用户收藏信息
     * @return 用户收藏集合
     */
	public List<BusUserCollect> selectBusUserCollectList(BusUserCollect busUserCollect);
	
	/**
     * 新增用户收藏
     * 
     * @param busUserCollect 用户收藏信息
     * @return 结果
     */
	public int insertBusUserCollect(BusUserCollect busUserCollect);
	
	/**
     * 修改用户收藏
     * 
     * @param busUserCollect 用户收藏信息
     * @return 结果
     */
	public int updateBusUserCollect(BusUserCollect busUserCollect);
		
	/**
     * 删除用户收藏信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusUserCollectByIds(String ids);
	
}
