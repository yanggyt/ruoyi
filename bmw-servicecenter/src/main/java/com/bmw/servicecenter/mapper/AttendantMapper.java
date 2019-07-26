package com.bmw.servicecenter.mapper;

import com.bmw.servicecenter.domain.Attendant;
import java.util.List;	

/**
 * 服务员 数据层
 * 
 * @author bmw
 * @date 2019-07-26
 */
public interface AttendantMapper 
{
	/**
     * 查询服务员信息
     * 
     * @param attendantId 服务员ID
     * @return 服务员信息
     */
	public Attendant selectAttendantById(Long attendantId);
	
	/**
     * 查询服务员列表
     * 
     * @param attendant 服务员信息
     * @return 服务员集合
     */
	public List<Attendant> selectAttendantList(Attendant attendant);
	
	/**
     * 新增服务员
     * 
     * @param attendant 服务员信息
     * @return 结果
     */
	public int insertAttendant(Attendant attendant);
	
	/**
     * 修改服务员
     * 
     * @param attendant 服务员信息
     * @return 结果
     */
	public int updateAttendant(Attendant attendant);
	
	/**
     * 删除服务员
     * 
     * @param attendantId 服务员ID
     * @return 结果
     */
	public int deleteAttendantById(Long attendantId);
	
	/**
     * 批量删除服务员
     * 
     * @param attendantIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAttendantByIds(String[] attendantIds);
	
}