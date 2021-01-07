package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusUserMessageMapper;
import com.ruoyi.system.domain.BusUserMessage;
import com.ruoyi.system.service.IBusUserMessageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户消息 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusUserMessageServiceImpl implements IBusUserMessageService 
{
	@Autowired
	private BusUserMessageMapper busUserMessageMapper;

	/**
     * 查询用户消息信息
     * 
     * @param messageId 用户消息ID
     * @return 用户消息信息
     */
    @Override
	public BusUserMessage selectBusUserMessageById(Long messageId)
	{
	    return busUserMessageMapper.selectBusUserMessageById(messageId);
	}
	
	/**
     * 查询用户消息列表
     * 
     * @param busUserMessage 用户消息信息
     * @return 用户消息集合
     */
	@Override
	public List<BusUserMessage> selectBusUserMessageList(BusUserMessage busUserMessage)
	{
	    return busUserMessageMapper.selectBusUserMessageList(busUserMessage);
	}
	
    /**
     * 新增用户消息
     * 
     * @param busUserMessage 用户消息信息
     * @return 结果
     */
	@Override
	public int insertBusUserMessage(BusUserMessage busUserMessage)
	{
	    return busUserMessageMapper.insertBusUserMessage(busUserMessage);
	}
	
	/**
     * 修改用户消息
     * 
     * @param busUserMessage 用户消息信息
     * @return 结果
     */
	@Override
	public int updateBusUserMessage(BusUserMessage busUserMessage)
	{
	    return busUserMessageMapper.updateBusUserMessage(busUserMessage);
	}

	/**
     * 删除用户消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusUserMessageByIds(String ids)
	{
		return busUserMessageMapper.deleteBusUserMessageByIds(Convert.toStrArray(ids));
	}
	
}
