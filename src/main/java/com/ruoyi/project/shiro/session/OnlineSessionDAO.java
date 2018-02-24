package com.ruoyi.project.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.project.system.online.domain.OnlineSession;
import com.ruoyi.project.system.online.domain.UserOnline;
import com.ruoyi.project.system.online.service.IUserOnlineService;
import com.ruoyi.project.util.HttpContextUtils;

/**
 * 针对自定义的ShiroSession的db操作
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO
{
    // Session超时时间，单位为毫秒
    private long expireTime = 120000;

    /**
     * 同步session到数据库的周期 单位为毫秒（默认5分钟）
     */
    private long dbSyncPeriod = 5 * 60 * 1000;

    /**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    @Autowired
    private IUserOnlineService onlineService;

    @Autowired
    private OnlineSessionFactory onlineSessionFactory;

    public OnlineSessionDAO()
    {
        super();
    }

    public OnlineSessionDAO(long expireTime)
    {
        super();
        this.expireTime = expireTime;
    }

    /**
     * 根据会话ID获取会话
     *
     * @param sessionId 会话ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String uri = request.getServletPath();
        // 如果是静态文件，则不更新SESSION
        if (checkStaticLink(uri))
        {
            return null;
        }
        System.out.println("==============doReadSession url=================" + uri);
        UserOnline userOnline = onlineService.selectByOnlineId(String.valueOf(sessionId));
        if (userOnline == null)
        {
            return null;
        }
        return onlineSessionFactory.createSession(userOnline);
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     */
    @Override
    protected void doUpdate(Session session)
    {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String uri = request.getServletPath();
        // 如果是静态文件，则不更新SESSION
        if (checkStaticLink(uri))
        {
            return;
        }
        System.out.println("==============update url=================" + uri);
        if (session == null || session.getId() == null)
        {
            return;
        }

        Session session1 = doReadSession(session.getId());
        OnlineSession onlineSession = (OnlineSession) session1;

        Date lastSyncTimestamp = (Date) session.getAttribute(LAST_SYNC_DB_TIMESTAMP);
        if (lastSyncTimestamp != null)
        {
            boolean needSync = true;
            long deltaTime = session.getLastAccessTime().getTime() - lastSyncTimestamp.getTime();
            if (deltaTime < dbSyncPeriod)
            { // 时间差不足 无需同步
                needSync = false;
            }
            boolean isGuest = session.getId() == null;

            // 如果不是游客 且session 数据变更了 同步
            if (isGuest == false && onlineSession.isAttributeChanged())
            {
                needSync = true;
            }

            if (needSync == false)
            {
                return;
            }
        }
        session.setAttribute(LAST_SYNC_DB_TIMESTAMP, session.getLastAccessTime());
        // session.setTimeout(expireTime);
        // 更新完后 重置标识
        if (onlineSession.isAttributeChanged())
        {
            onlineSession.resetAttributeChanged();
        }
        onlineService.saveByOnline(UserOnline.fromOnlineSession(onlineSession));
    }

    /**
     * 当会话过期/停止（如用户退出时）属性等会调用
     */
    @Override
    protected void doDelete(Session session)
    {
        System.out.println("===============delete================");
        if (null == session)
        {
            return;
        }
        String sessionId = String.valueOf(session.getId());
        onlineService.deleteByOnlineId(sessionId);
    }

    /**
     * 获取当前所有活跃用户
     */
    @Override
    public Collection<Session> getActiveSessions()
    {
        System.out.println("==============getActiveSessions=================");
        return null;
    }

    public long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(long expireTime)
    {
        this.expireTime = expireTime;
    }

    /**
     * 检查是否为静态链接
     * 
     * @return true 是 false 否
     */
    public boolean checkStaticLink(String uri)
    {
        boolean linkFlag = false;
        // 如果是登录请求，则不更新SESSION
        if (StringUtils.endsWithAny(uri, new String[] { "/login", "/", "/favicon.ico" }))
        {
            linkFlag = true;
        }
        // 如果是静态文件，则不更新SESSION
        if (StringUtils.startsWith(uri, "/css") && StringUtils.endsWith(uri, ".css")
                || StringUtils.startsWith(uri, "/js") && StringUtils.endsWith(uri, ".js")
                || StringUtils.startsWith(uri, "/img") && StringUtils.endsWith(uri, ".jpg")
                || StringUtils.startsWith(uri, "/img") && StringUtils.endsWith(uri, ".png")
                || StringUtils.startsWith(uri, "/fonts") && StringUtils.endsWith(uri, ".woff2")
                || StringUtils.startsWith(uri, "/js") && StringUtils.endsWith(uri, ".css")
                || StringUtils.startsWith(uri, "/css") && StringUtils.endsWith(uri, ".png")
                || StringUtils.startsWith(uri, "/css") && StringUtils.endsWith(uri, ".woff2"))
        {
            linkFlag = true;
        }
        return linkFlag;
    }
}
