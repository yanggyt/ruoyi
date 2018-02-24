package com.ruoyi.project.system.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.online.dao.IUserOnlineDao;
import com.ruoyi.project.system.online.domain.UserOnline;

@Service("userOnlineService")
public class UserOnlineServiceImpl implements IUserOnlineService
{
    @Autowired
    private IUserOnlineDao userOnlineDao;

    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectByOnlineId(String sessionId)
    {
        return userOnlineDao.selectByOnlineId(sessionId);
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteByOnlineId(String sessionId)
    {
        UserOnline userOnline = selectByOnlineId(sessionId);
        if (userOnline != null)
        {
            userOnlineDao.deleteByOnlineId(sessionId);
        }
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    public void saveByOnline(UserOnline online)
    {

    }
}
