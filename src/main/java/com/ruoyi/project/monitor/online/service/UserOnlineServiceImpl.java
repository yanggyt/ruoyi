package com.ruoyi.project.monitor.online.service;

import java.util.Date;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.shiro.session.OnlineSessionDAO;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.project.monitor.online.dao.IUserOnlineDao;
import com.ruoyi.project.monitor.online.domain.UserOnline;

/**
 * 在线用户 服务层处理
 * 
 * @author ruoyi
 */
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
    @Override
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
    @Override
    public void deleteByOnlineId(String sessionId)
    {
        UserOnline userOnline = selectByOnlineId(sessionId);
        if (userOnline != null)
        {
            userOnlineDao.deleteByOnlineId(sessionId);
        }
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteByOnline(List<String> sessions)
    {
        for (String sessionId : sessions)
        {
            UserOnline userOnline = selectByOnlineId(sessionId);
            if (userOnline != null)
            {
                userOnlineDao.deleteByOnlineId(sessionId);
            }
        }
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    @Override
    public void saveByOnline(UserOnline online)
    {
        userOnlineDao.saveByOnline(online);
    }

    /**
     * 查询会话集合
     * 
     * @param pageUtilEntity 分页参数
     */
    @Override
    public List<UserOnline> pageInfoQueryUserOnline(PageUtilEntity pageUtilEntity)
    {
        return userOnlineDao.pageInfoQuery(pageUtilEntity);
    }

    /**
     * 读取Session信息
     * 
     * @param sessionId 会话ID
     */
    @Override
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

    /**
     * 查询会话集合
     * 
     * @param online 会话信息
     */
    @Override
    public List<UserOnline> selectByOnlineExpired(Date expiredDate)
    {
        String lastAccessTime = DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", expiredDate);
        return userOnlineDao.selectByOnlineExpired(lastAccessTime);
    }
}
