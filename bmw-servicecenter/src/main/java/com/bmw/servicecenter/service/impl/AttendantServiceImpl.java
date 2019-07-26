package com.bmw.servicecenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bmw.servicecenter.mapper.AttendantMapper;
import com.bmw.servicecenter.domain.Attendant;
import com.bmw.servicecenter.service.IAttendantService;
import com.bmw.common.core.text.Convert;

/**
 * 服务员 服务层实现
 * 
 * @author bmw
 * @date 2019-07-26
 */
@Service
public class AttendantServiceImpl implements IAttendantService 
{
	@Autowired
	private AttendantMapper attendantMapper;

	/**
     * 查询服务员信息
     * 
     * @param attendantId 服务员ID
     * @return 服务员信息
     */
    @Override
	public Attendant selectAttendantById(Long attendantId)
	{
	    return attendantMapper.selectAttendantById(attendantId);
	}
	
	/**
     * 查询服务员列表
     * 
     * @param attendant 服务员信息
     * @return 服务员集合
     */
	@Override
	public List<Attendant> selectAttendantList(Attendant attendant)
	{
	    return attendantMapper.selectAttendantList(attendant);
	}
	
    /**
     * 新增服务员
     * 
     * @param attendant 服务员信息
     * @return 结果
     */
	@Override
	public int insertAttendant(Attendant attendant)
	{
	    return attendantMapper.insertAttendant(attendant);
	}
	
	/**
     * 修改服务员
     * 
     * @param attendant 服务员信息
     * @return 结果
     */
	@Override
	public int updateAttendant(Attendant attendant)
	{
	    return attendantMapper.updateAttendant(attendant);
	}

	/**
     * 删除服务员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAttendantByIds(String ids)
	{
		return attendantMapper.deleteAttendantByIds(Convert.toStrArray(ids));
	}
	
}
