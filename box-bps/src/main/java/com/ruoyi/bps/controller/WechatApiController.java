package com.ruoyi.bps.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.bps.domain.WechatUserInfo;
import com.ruoyi.bps.service.IWechatApiService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Autowired
    private ISysUserService userService;

    @RequestMapping("anon/getAccessToken")
    public String getAccessToken() {
        return wechatApiService.GetAccessToken();
    }




    @PostMapping("/wxlogin")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {

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



    @GetMapping("anon/wechatLogin")
    public String codeTest(HttpServletRequest request)
    {
        String code= request.getParameter("code");
        String state = request.getParameter("state");

        //获取访问用户身份ID
        String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        String param = "access_token="+wechatApiService.GetAccessToken()+"&code="+code;
       // String userInfo = HttpUtils.sendGet(url,param);    //测试已能正常返回UserInfo Json,正式使用时打开
        String userInfo = "{\"UserId\":\"359\",\"DeviceId\":\"10000589102865WJ\",\"errcode\":0,\"errmsg\":\"ok\"}";  //为避免去微信获取code麻烦，开发调试时打开
        JSONObject jsonObjectUserInfo = JSONObject.parseObject(userInfo);
        //如果返回码不为0，则返回错误信息
        if ( Integer.parseInt(jsonObjectUserInfo.getString("errcode")) != 0){
            return jsonObjectUserInfo.getString("errmsg");
        }
        String userId = jsonObjectUserInfo.getString("UserId");

        //获取用户邮箱
        url="https://qyapi.weixin.qq.com/cgi-bin/user/get";
        param="access_token="+wechatApiService.GetAccessToken()+"&userid="+userId;
        String userInfoDetail=HttpUtils.sendGet(url,param); //获取成员信息
        JSONObject jsonObjectUserInfoDetail=JSONObject.parseObject(userInfoDetail);
        //如果返回码不为0，则返回错误信息
        if(Integer.parseInt(jsonObjectUserInfoDetail.getString("errcode")) != 0)
        {
            return jsonObjectUserInfo.getString("errmsg");
        }
        String userEmail= jsonObjectUserInfoDetail.getString("email");
        String userName= jsonObjectUserInfoDetail.getString("name");

        //根据邮箱名+用户名匹配本地用户对应的邮箱名与用户名
        SysUser sysUser=new SysUser();
        sysUser.setUserName(userName);
        sysUser.setEmail(userEmail);
        sysUser.setStatus("02"); //只获取从OA同步的用户，保持与企业微信一致。
        List<SysUser> userList= userService.selectUserList(sysUser);
        int count= userList.size();
        if(count <= 0){
            return "false"; //系统里没有用户，没有从OA同步？ 处理逻辑待定
        }
        if(count > 1){
            return "false"; //本地数据库存在多个姓名与邮箱相同的记录，如何处理？？
        }



        return userEmail;
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
