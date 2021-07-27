package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IWechatApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@RestController
public class WechatApiController extends BaseController {
    @Autowired
    IWechatApiService wechatApiService;



    @RequestMapping("anon/getAccessToken")
    public String getAccessToken() {
        return wechatApiService.GetAccessToken();
    }








    @GetMapping("anon/wechatLogin")
    @ResponseBody
    public AjaxResult WechatLogin(HttpServletRequest request)
    {
        String code= request.getParameter("code");
        //String state = request.getParameter("state");
        String username=wechatApiService.GetLoginNameWithWechatCode(code);
        String password=code;
        String rememberMe="0";

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
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

    @GetMapping("anon/userInfo")
    public Map<String, Object> getJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        Map<String, Object> params = JSON.parseObject(responseStrBuilder.toString(), Map.class);
        return params;
    }


}
