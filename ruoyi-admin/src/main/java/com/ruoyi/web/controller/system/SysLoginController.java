package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.ConfigService;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{
    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;
    
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        // 是否开启记住我
        mmap.put("isRemembered", rememberMe);
        // 是否开启用户注册
        mmap.put("isAllowRegister", Convert.toBool(configService.getKey("sys.account.registerUser"), false));
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            SavedRequest savedRequest = (SavedRequest) ShiroUtils.getSession().getAttribute(WebUtils.SAVED_REQUEST_KEY);
            subject.login(token);
            String url = "";
            if (savedRequest != null && savedRequest.getRequestUrl() != null) {
                if (savedRequest.getRequestUrl().contains("/redirect")) {
                    url = savedRequest.getRequestUrl().replace("/redirect", "");
                }
            }
            return success(url);
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @PostMapping("/jwt/login")
    @ResponseBody
    public AjaxResult jwtLogin(String username, String password)
    {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return AjaxResult.error("账号和密码不能为空!");
        }

        SysUser user = userService.selectUserByLoginName(username);
        if (user == null)
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            return AjaxResult.error("对不起，您的账号已被删除!");
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            return AjaxResult.error("用户已封禁，请联系管理员!");
        }

        if (!passwordService.matches(user, password))
        {
            return AjaxResult.error("用户不存在/密码错误!");
        }

        String token = JwtUtils.createToken(username, user.getPassword());
        return AjaxResult.success("登录成功,请妥善保管您的token信息").put("token", token);
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }

    /*@RequestMapping("api/get/jsessionid")
    @ResponseBody
    public AjaxResult getSessionId(@CookieValue("JSESSIONID") String sessionID)
    {
        System.out.println(sessionID);
        return AjaxResult.success(sessionID);
    }

    @RequestMapping("api/auto/login")
    @ResponseBody
    public AjaxResult autoLogin(@CookieValue("JSESSIONID") String sessionID)
    {
        String url = "http://192.168.5.8:8901/index.php?m=apiapp&f=scanLoginCheck";
        String param = "type=WO&seid=" + sessionID;
        String httpRes = HttpUtils.sendPost(url, param).replaceAll("&","");
        Result result = JSONObject.parseObject(httpRes, Result.class);
        if(result.getCode().equals(0)){
            String email = result.getData().getEmail();
            User user = iUserService.selectUserByEmail(email);
            UserToken token = new UserToken(user.getLoginName(), LoginType.NOPASSWD);
            Subject subject = SecurityUtils.getSubject();
            try
            {
                subject.login(token);
                return success("扫码成功");
            }
            catch (AuthenticationException e)
            {
                String msg = "扫码登录失败";
                if (StringUtils.isNotEmpty(e.getMessage()))
                {
                    msg = e.getMessage();
                }
                return error(msg);
            }
        }else {
            return error("扫码登录失败");
        }
    }*/
}
