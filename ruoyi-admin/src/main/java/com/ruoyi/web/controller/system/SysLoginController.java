package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.jwt.service.IJwtTokenService;
import com.ruoyi.framework.shiro.util.CustToken;
import com.ruoyi.system.service.IWechatApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.ConfigService;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysUserService;

import java.util.Map;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Api(tags = "生成AccessToken接口")
@Controller
public class SysLoginController extends BaseController
{
    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;

    @Autowired
    private ConfigService configService;

    @Autowired
    private IJwtTokenService jwtTokenService;

    @Autowired
    private IWechatApiService wechatApiService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        String loginType= request.getParameter("loginType");
        if(StringUtils.isNotEmpty(loginType) && request.getParameter("loginType").equals("wechat")){
            String code= request.getParameter("code");
            //String state = request.getParameter("state");
            String username=wechatApiService.GetLoginNameWithWechatCode(code);
            //如果没有获取到登录名，说明验证失败，跳转登录页
            if(StringUtils.isEmpty(username)){
               return "login";
            }

            String password="";
            mmap.put("loginType","wechat");
            mmap.put("username",username);
            mmap.put("password",password);
            return "loginwechat";


        }

        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        // 是否开启记住我
        mmap.put("isRemembered", rememberMe);
        // 是否开启用户注册
        mmap.put("isAllowRegister", configService.getKey("sys.account.registerUser"));
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe,String loginType)
    {
       // UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        CustToken token=new CustToken(username,password,rememberMe,loginType);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
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

    @ApiOperation("获取Json格式AccessToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", dataTypeClass = String.class),
    })
    @PostMapping("/jwt/login")
    @ResponseBody
    public AjaxResult jwtLogin(String username, String password)
    {
       return jwtTokenService.AjaxResultJwtToken(username,password);
    }

    @ApiOperation("获取String格式AccessToken")
    @PostMapping("/jwt/topgplogin")
    @ResponseBody
    public String topgpJwtLogin(String username, String password)
    {
         return JSONObject.toJSONString(jwtTokenService.AjaxResultJwtToken(username,password));
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
