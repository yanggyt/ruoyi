package com.ruoyi.project.shiro.session;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;

import com.ruoyi.project.shiro.common.utils.IpUtils;
import com.ruoyi.project.system.online.domain.OnlineSession;
import com.ruoyi.project.system.online.domain.UserOnline;

@Component
public class OnlineSessionFactory implements SessionFactory
{
    public Session createSession(UserOnline userOnline)
    {
        return userOnline.getSession();
    }

    @Override
    public Session createSession(SessionContext initData)
    {
        OnlineSession session = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext)
        {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null)
            {
                session.setHost(IpUtils.getIpAddr(request));
                session.setBrowser(request.getHeader("User-Agent"));
                session.setHost(request.getLocalAddr() + ":" + request.getLocalPort());
            }
        }
        return session;
    }
}
