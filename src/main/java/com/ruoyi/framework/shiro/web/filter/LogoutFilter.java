package com.ruoyi.framework.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import com.ruoyi.common.constant.CommonConstant;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SystemLogUtils;
import com.ruoyi.common.utils.security.ShiroUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 退出过滤器
 * 
 * @author ruoyi
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter
{

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
                String loginName = ShiroUtils.getLoginName();
                // 记录用户退出日志
                SystemLogUtils.log(loginName, CommonConstant.Logout, MessageUtils.message("user.logout.success"));
                // 退出登录
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
        String url = getLoginUrl();
        if (StringUtils.isNoneBlank(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }

}
