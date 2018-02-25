package com.ruoyi.project.system.online.service;

import java.util.List;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.shiro.session.OnlineSessionDAO;
import com.ruoyi.project.system.online.dao.IUserOnlineDao;
import com.ruoyi.project.system.online.domain.UserOnline;

@Service("userOnlineService")
public class UserOnlineServiceImpl implements IUserOnlineService
{
    @Autowired
    private IUserOnlineDao userOnlineDao;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

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
        userOnlineDao.saveByOnline(online);
    }

    /**
     * 查询会话集合
     * 
     * @param online 会话信息
     */
    public List<UserOnline> selectUserOnlines()
    {
        return userOnlineDao.selectUserOnlines();
    }

    /**
     * 读取Session信息
     * 
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId)
    {
        Session session = onlineSessionDAO.readSession(sessionId);
        if (session == null)
        {
            return;
        }
        session.setTimeout(1000);
        userOnlineDao.deleteByOnlineId(sessionId);
    }
}
