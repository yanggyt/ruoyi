package com.ruoyi.project.system.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.tools.StringTools;
import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.framework.core.domain.R;

/**
 * 登录验证
 * 
 * @author yangzz
 */
@RestController
public class LoginController extends BaseController
{
    @RequestMapping("/login")
    R ajaxLogin(String username, String password)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return R.ok();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringTools.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return R.error(msg);
        }
    }
}
