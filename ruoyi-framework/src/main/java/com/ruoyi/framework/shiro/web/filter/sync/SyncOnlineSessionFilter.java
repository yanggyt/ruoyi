package com.ruoyi.framework.shiro.web.filter.sync;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.framework.shiro.session.OnlineSession;
import com.ruoyi.framework.shiro.session.OnlineSessionDAO;
import org.apache.shiro.web.util.WebUtils;

/**
 * 同步Session数据到Db
 * 
 * @author ruoyi
 */
public class SyncOnlineSessionFilter extends PathMatchingFilter
{
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 同步会话数据到DB 一次请求最多同步一次 防止过多处理 需要放到Shiro过滤器之前
     */
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        OnlineSession session = (OnlineSession) request.getAttribute(ShiroConstants.ONLINE_SESSION);
        // 如果session stop了 也不同步
        // session停止时间，如果stopTimestamp不为null，则代表已停止
        if (session != null && session.getUserId() != null && session.getStopTimestamp() == null)
        {
            onlineSessionDAO.syncToDb(session);
        }

        if (session != null && session.getUserId() != null && session.getStopTimestamp() == null)
        {
            // 获取请求的地址URI
            String requestURI = getPathWithinApplication(request);
            if (requestURI.contains("/redirect")) {
                HttpServletResponse response1 = (HttpServletResponse) response;
                String value = requestURI.replace("/redirect", "");
                //实例化一个Cookie对象
                Cookie cookie = new Cookie("name", value);
                //设置Cookie生命周期(有效时间);单位:秒
                cookie.setMaxAge(60);
                cookie.setPath("/");
                response1.addCookie(cookie);
                WebUtils.issueRedirect(request, response1, "/index");
                return false;
            }
        }
        return true;
    }

    public void setOnlineSessionDAO(OnlineSessionDAO onlineSessionDAO)
    {
        this.onlineSessionDAO = onlineSessionDAO;
    }
}
