package com.ruoyi.project.system.online.dao;

import com.ruoyi.project.system.online.domain.UserOnline;

public interface IUserOnlineDao
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
    public int deleteByOnlineId(String sessionId);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    public int saveByOnline(UserOnline online);
}
