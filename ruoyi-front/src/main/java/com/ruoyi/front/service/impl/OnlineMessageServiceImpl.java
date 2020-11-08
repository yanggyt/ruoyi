package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.OnlineMessageMapper;
import com.ruoyi.front.domain.OnlineMessage;
import com.ruoyi.front.service.IOnlineMessageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 在线留言Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class OnlineMessageServiceImpl implements IOnlineMessageService 
{
    @Autowired
    private OnlineMessageMapper onlineMessageMapper;

    /**
     * 查询在线留言
     * 
     * @param id 在线留言ID
     * @return 在线留言
     */
    @Override
    public OnlineMessage selectOnlineMessageById(Long id)
    {
        return onlineMessageMapper.selectOnlineMessageById(id);
    }

    /**
     * 查询在线留言列表
     * 
     * @param onlineMessage 在线留言
     * @return 在线留言
     */
    @Override
    public List<OnlineMessage> selectOnlineMessageList(OnlineMessage onlineMessage)
    {
        return onlineMessageMapper.selectOnlineMessageList(onlineMessage);
    }

    /**
     * 新增在线留言
     * 
     * @param onlineMessage 在线留言
     * @return 结果
     */
    @Override
    public int insertOnlineMessage(OnlineMessage onlineMessage)
    {
        onlineMessage.setCreateTime(DateUtils.getNowDate());
        return onlineMessageMapper.insertOnlineMessage(onlineMessage);
    }

    /**
     * 修改在线留言
     * 
     * @param onlineMessage 在线留言
     * @return 结果
     */
    @Override
    public int updateOnlineMessage(OnlineMessage onlineMessage)
    {
        onlineMessage.setUpdateTime(DateUtils.getNowDate());
        return onlineMessageMapper.updateOnlineMessage(onlineMessage);
    }

    /**
     * 删除在线留言对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOnlineMessageByIds(String ids)
    {
        return onlineMessageMapper.deleteOnlineMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除在线留言信息
     * 
     * @param id 在线留言ID
     * @return 结果
     */
    @Override
    public int deleteOnlineMessageById(Long id)
    {
        return onlineMessageMapper.deleteOnlineMessageById(id);
    }
}
