package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.WechatAccessToken;
import com.ruoyi.system.mapper.WechatAccessTokenMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IWechatApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WechatApiServiceImpl implements IWechatApiService {
    @Value("${wechat.corpId}")
    private String corpId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.agentId}")
    private String agentId;

    @Autowired
    WechatAccessTokenMapper wechatAccessTokenMapper;

    @Autowired
    IWechatApiService wechatApiService;

    @Autowired
    private ISysUserService userService;

    /**
     *
     * 获取企业微信Access Token
     * @return Access Token
     */
    @Override
    public String GetAccessToken() {
        //获取本地数据库中的Token
        WechatAccessToken wat = new WechatAccessToken();
        wat.setCorpId(corpId);
        wat.setSecret(secret);
        List<WechatAccessToken> list= wechatAccessTokenMapper.selectWechatAccessTokenList(wat);
        WechatAccessToken returnWat;
        //如果数据库中没有对应corpId+corpSecret的数据,或者数据多于一条，则从企业微信获取Token，并插入数据库
        if(list.isEmpty() || list.size() <=0)
        {
            returnWat= getAccessTokenFromWechat(corpId,secret,agentId);
            wechatAccessTokenMapper.insertWechatAccessToken(returnWat);
            return returnWat.getAccess_token();
        }

        //如果数据库中存在多条corpId+corpSecret的数据，则全部删除再从企业微信获取Token,并插入数据库
        if(list.size()>1 || StringUtils.isEmpty(list.get(0).getAccess_token()))
        {
            for(WechatAccessToken token:list){
                wechatAccessTokenMapper.deleteWechatAccessTokenById(token.getSid());
            }
            returnWat= getAccessTokenFromWechat(corpId,secret,agentId);
            wechatAccessTokenMapper.insertWechatAccessToken(returnWat);
            return returnWat.getAccess_token();
        }

        //如果获取到的数据中，Token或更新时间为空，或者离过期时间小于1000秒，则从企业微信获取Token,并更新数据库
        int a= differenceSecond(DateUtils.getNowDate(),list.get(0).getGetTokenTime())-Integer.parseInt(list.get(0).getExpires_in())-1000;
        if(StringUtils.isEmpty(list.get(0).getAccess_token()) || list.get(0).getGetTokenTime() ==null
                || differenceSecond(DateUtils.getNowDate(),list.get(0).getGetTokenTime())>Integer.parseInt(list.get(0).getExpires_in())-1000){
            returnWat= getAccessTokenFromWechat(corpId,secret,agentId);
            returnWat.setSid(list.get(0).getSid());
            wechatAccessTokenMapper.updateWechatAccessToken(returnWat);
            return returnWat.getAccess_token();
        }

        //如果以上情况皆不是，则返回本地数据库的token
        return list.get(0).getAccess_token();

    }

    //根据corpId与corpSecret获取Token
    private WechatAccessToken  getAccessTokenFromWechat(String corpId,String secret, String agentId){
        String param ="corpid=" + corpId + "&corpsecret=" + secret;
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        WechatAccessToken wechatAccessToken= new Gson().fromJson(HttpUtils.sendGet(url,param),WechatAccessToken.class);
        wechatAccessToken.setCorpId(corpId);
        wechatAccessToken.setSecret(secret);
        wechatAccessToken.setAgentId(agentId);
        wechatAccessToken.setGetTokenTime(DateUtils.getNowDate());
        return wechatAccessToken;
    }

    //获取相两个日期相差秒数
    private   int differenceSecond(Date minuendDate, Date subtractionDate ) {
        return  (int)((minuendDate.getTime() - subtractionDate.getTime()) / 1000);
    }


    /**
     * 根据企业微信登录身份获取本地LoginName
     *
     * @param code
     * @return LoginName
     */
    @Override
    public String GetLoginNameWithWechatCode(String code)
    {
        //获取访问用户身份ID
        String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        String param = "access_token="+wechatApiService.GetAccessToken()+"&code="+code;
        //String userInfo = HttpUtils.sendGet(url,param);    //测试已能正常返回UserInfo Json,正式使用时打开
        String userInfo = "{\"UserId\":\"359\",\"DeviceId\":\"10000589102865WJ\",\"errcode\":0,\"errmsg\":\"ok\"}";  //为避免去微信获取code麻烦，开发调试时打开
        JSONObject jsonObjectUserInfo = JSONObject.parseObject(userInfo);
        //如果返回码不为0，则输出错误信息，并返回空值
        if ( Integer.parseInt(jsonObjectUserInfo.getString("errcode")) != 0){
             System.out.println(jsonObjectUserInfo.getString("errmsg"));
             return "";
        }
        String userId = jsonObjectUserInfo.getString("UserId");

        //获取用户邮箱与姓名
        url="https://qyapi.weixin.qq.com/cgi-bin/user/get";
        param="access_token="+wechatApiService.GetAccessToken()+"&userid="+userId;
        String userInfoDetail=HttpUtils.sendGet(url,param); //获取成员信息
        JSONObject jsonObjectUserInfoDetail=JSONObject.parseObject(userInfoDetail);
        //如果返回码不为0，则返回错误信息
        if(Integer.parseInt(jsonObjectUserInfoDetail.getString("errcode")) != 0)
        {
            System.out.println(jsonObjectUserInfo.getString("errmsg"));
            return "";
        }
        String userEmail= jsonObjectUserInfoDetail.getString("email");
        String userName= jsonObjectUserInfoDetail.getString("name");

        //根据邮箱名+用户名匹配本地用户对应的邮箱名与用户名
        SysUser sysUser=new SysUser();
        sysUser.setUserName(userName);
        sysUser.setEmail(userEmail);
        sysUser.setUserType("02"); //只获取从OA同步的用户，保持与企业微信一致。
        List<SysUser> userList= userService.selectUserLists(sysUser);
        int count= userList.size();
        if(count <= 0){
            return ""; //系统里没有用户，没有从OA同步？ 处理逻辑待定
        }
        if(count > 1){
            return ""; //本地数据库存在多个姓名与邮箱相同的记录，如何处理？？
        }
        String loginName= userList.get(0).getLoginName();
      return loginName;

    }

}
