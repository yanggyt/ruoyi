package com.ruoyi.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.util.CustToken;
import com.ruoyi.system.service.IWechatApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
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
        String state = request.getParameter("state");
        String username=wechatApiService.GetLoginNameWithWechatCode(code);
        String password="";
        Boolean rememberMe=true;
        String loginType=request.getParameter("loginType");

        //UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
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


    @GetMapping("anon/SendTextMessageToWechatUser")
    @ResponseBody
    public Map<String, String> SendTextMessageToWechatUser() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("2342343243");
        userIdList.add("erqrqwe");
        userIdList.add("359");
        Map<String, String> resultMap = wechatApiService.SendTextMessageToWechatUser(userIdList,"<a href=\"www.baidu.com\">哈哈哈！</a>");
        return resultMap;
    }

    @GetMapping("anon/SendTextCardMessageToWechatUser")
    @ResponseBody
    public Map<String, String> SendTextCardMessageToWechatUser() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("23456667");
        userIdList.add("355354354");
        userIdList.add("359");
        userIdList.add("454");
        userIdList.add("408");
        String title="号外：特大优惠！限时抢购";
        String description="今年仅此一次，苹果手机1000元起！欢迎前来购买！走过路过，不要错过！";
        String dtailUrl="https://item.jd.com/100008348530.html";

        Map<String, String> resultMap = wechatApiService.SendTextCardMessageToWechatUser(userIdList,title,description,dtailUrl);
        return resultMap;
    }

}
