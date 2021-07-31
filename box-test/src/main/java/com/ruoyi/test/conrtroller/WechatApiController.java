package com.ruoyi.test.conrtroller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.service.IWechatApiService;
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
    @ResponseBody
    public String getAccessToken() {
        return wechatApiService.GetAccessToken();
    }

    @GetMapping("anon/userInfo")
    public Map<String, Object> getJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        return JSON.parseObject(responseStrBuilder.toString(), Map.class);

    }


    @GetMapping("anon/SendTextMessageToWechatUser")
    @ResponseBody
    public Map<String, String> SendTextMessageToWechatUser() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("2342343243");//错误userId示例
        userIdList.add("erqrqwe");//错误userId示例
        userIdList.add("");    //空UserId示例
        userIdList.add("359");
        if(! String.valueOf(ShiroUtils.getUserId()).equals("359")){
        userIdList.add(String.valueOf(ShiroUtils.getUserId()));
        }
        Map<String, String> resultMap = wechatApiService.SendTextMessageToWechatUser(userIdList,"<a href=\"www.baidu.com\">哈哈哈！</a>");
        return resultMap;
    }

    @GetMapping("anon/SendTextCardMessageToWechatUser")
    @ResponseBody
    public Map<String, String> SendTextCardMessageToWechatUser() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("23456667");   //错误userId示例
        userIdList.add("355354354");  //错误userId示例
        userIdList.add("");    //空UserId示例
        userIdList.add("359");
        //userIdList.add("454");
        //userIdList.add("408");
        if(!String.valueOf(ShiroUtils.getUserId()).equals("359")){
            userIdList.add(String.valueOf(ShiroUtils.getUserId()));
        }
        String title="号外：特大优惠！限时抢购";
        String description="今年仅此一次，苹果手机1000元起！欢迎前来购买！走过路过，不要错过！";
        String dtailUrl="https://item.jd.com/100008348530.html";

        Map<String, String> resultMap = wechatApiService.SendTextCardMessageToWechatUser(userIdList,title,description,dtailUrl);
        return resultMap;
    }

}
