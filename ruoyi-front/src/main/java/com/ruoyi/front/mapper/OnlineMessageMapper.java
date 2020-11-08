package com.ruoyi.front.mapper;

import java.util.List;
import com.ruoyi.front.domain.OnlineMessage;

/**
 * 在线留言Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface OnlineMessageMapper 
{
    /**
     * 查询在线留言
     * 
     * @param id 在线留言ID
     * @return 在线留言
     */
    public OnlineMessage selectOnlineMessageById(Long id);

    /**
     * 查询在线留言列表
     * 
     * @param onlineMessage 在线留言
     * @return 在线留言集合
     */
    public List<OnlineMessage> selectOnlineMessageList(OnlineMessage onlineMessage);

    /**
     * 新增在线留言
     * 
     * @param onlineMessage 在线留言
     * @return 结果
     */
    public int insertOnlineMessage(OnlineMessage onlineMessage);

    /**
     * 修改在线留言
     * 
     * @param onlineMessage 在线留言
     * @return 结果
     */
    public int updateOnlineMessage(OnlineMessage onlineMessage);

    /**
     * 删除在线留言
     * 
     * @param id 在线留言ID
     * @return 结果
     */
    public int deleteOnlineMessageById(Long id);

    /**
     * 批量删除在线留言
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOnlineMessageByIds(String[] ids);
}
