package com.ruoyi.project.system.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.tools.StringTools;
import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.framework.core.domain.R;

/**
 * 登录验证
 * 
 * @author yangzz
 */
// @RestController
@Controller
public class LoginController extends BaseController
{

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public R ajaxLogin(String username, String password)
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

    @GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }
}
