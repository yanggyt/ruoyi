package com.ruoyi.bps.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.bps.domain.WechatUserInfo;
import com.ruoyi.bps.service.IWechatApiService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
public class WechatApiController {
    @Autowired
    IWechatApiService wechatApiService;

    @RequestMapping("anon/getAccessToken")
    public String getAccessToken() {
        return wechatApiService.GetAccessToken();
    }

    @GetMapping("anon/wechatLogin")
    public String codeTest(HttpServletRequest request)
    {
        String code= request.getParameter("code");
        String state = request.getParameter("state");

        String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        String param = "access_token="+wechatApiService.GetAccessToken()+"&code="+code;
        String userInfo = HttpUtils.sendGet(url,code);
        return code;
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
