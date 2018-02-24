package com.ruoyi.project.system.online.service;

import java.util.List;
import com.ruoyi.project.system.online.domain.UserOnline;

public interface IUserOnlineService
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectByOnlineId(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteByOnlineId(String sessionId);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    public void saveByOnline(UserOnline online);

    /**
     * 查询会话集合
     * 
     * @param online 会话信息
     */
    public List<UserOnline> selectUserOnlines();

    /**
     * 读取Session信息
     * 
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId);
}
