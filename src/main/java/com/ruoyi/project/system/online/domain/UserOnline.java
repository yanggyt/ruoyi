package com.ruoyi.project.system.online.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前在线会话 sys_user_online
 * 
 * @author yangzz
 */
public class UserOnline implements Serializable
{
    private static final long serialVersionUID = 1L;

    // 用户会话id
    private String sessionId;

    // 当前登录的用户Id
    private String userId;

    // 登录名
    private String loginName;

    // 登录IP地址
    private String host;

    // 浏览器类型
    private String browser;

    // 操作系统
    private String os;

    // 在线状态
    private OnlineSession.OnlineStatus status = OnlineSession.OnlineStatus.on_line;

    // 在线状态
    private Date startTimestamp;

    // session最后访问时间
    private Date lastAccessTime;

    // 超时时间
    private Long timeout;

    // 备份的当前用户会话
    private OnlineSession session;

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public OnlineSession.OnlineStatus getStatus()
    {
        return status;
    }

    public void setStatus(OnlineSession.OnlineStatus status)
    {
        this.status = status;
    }

    public Date getStartTimestamp()
    {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp)
    {
        this.startTimestamp = startTimestamp;
    }

    public Date getLastAccessTime()
    {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime)
    {
        this.lastAccessTime = lastAccessTime;
    }

    public Long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(Long timeout)
    {
        this.timeout = timeout;
    }

    public OnlineSession getSession()
    {
        return session;
    }

    public void setSession(OnlineSession session)
    {
        this.session = session;
    }

    public static final UserOnline fromOnlineSession(OnlineSession session)
    {
        UserOnline online = new UserOnline();
        online.setSessionId(String.valueOf(session.getId()));
        online.setUserId(session.getUserId());
        online.setLoginName(session.getLoginName());
        online.setStartTimestamp(session.getStartTimestamp());
        online.setLastAccessTime(session.getLastAccessTime());
        online.setTimeout(session.getTimeout());
        online.setHost(session.getHost());
        online.setBrowser(session.getBrowser());
        online.setOs(session.getOs());
        online.setSession(session);

        return online;
    }

}
