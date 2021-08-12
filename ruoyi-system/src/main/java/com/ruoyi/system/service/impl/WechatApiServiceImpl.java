package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.WechatAccessToken;
import com.ruoyi.system.domain.WechatUserInfo;
import com.ruoyi.system.mapper.WechatAccessTokenMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IWechatApiService;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String cacheName = "wechatToke:" + corpId + "," + agentId;

        //获取缓存中accessToken与缓存的时间信息
        Cache<String, Object> cache = CacheUtils.getCache(cacheName);
        String accessToken = (String) cache.get("accessToken");
        Date getTokenTime = ObjectToDate(cache.get("getTokenTime"));
        Integer expires_in = ObjectToInteger(cache.get("expires_in"));
        //如果没有获取到cache或者accessToken为空或者cache即将过期，则重新获取并返回新的accessToken;
        if (StringUtils.isEmpty(accessToken) || null == getTokenTime ? true : (differenceSecond(DateUtils.getNowDate(), getTokenTime) + 1000 > expires_in ? true : false)) {
            //清空wechatAccessTokenCache
            CacheUtils.removeAll(cacheName);
            //从企业微信获取新的accessToken
            WechatAccessToken wechatAccessToken = getAccessTokenFromWechat(corpId, secret, agentId);
            //将Token写入缓存
            CacheUtils.put(cacheName, "accessToken", wechatAccessToken.getAccess_token());
            CacheUtils.put(cacheName, "expires_in", wechatAccessToken.getExpires_in());
            CacheUtils.put(cacheName, "getTokenTime", DateUtils.getNowDate());
            //返回token
            return wechatAccessToken.getAccess_token();
        }
        return accessToken;
    }

        /*
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
            //将accessToken写入数据库
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

    }*/

    //将对象转化为日期，如果无法转换则返回空值，避免抛出异常。
    private Date ObjectToDate(Object obj){
        try {
            return (Date) obj;
        }catch (Exception e){
           return null;
        }
    }

    //将对象转化为Integer,如果对象为空或无法转换返回0，
    private Integer ObjectToInteger(Object obj){
        //如果为空返回零
        if(null==obj){
            return  0;
        }
        try{
            return Integer.parseInt(obj.toString());
        }catch (Exception e){
            //发生异常返回0
            return 0;
        }
    }

    //获取相两个日期相差秒数
    private   int differenceSecond(Date minuendDate, Date subtractionDate ) {
        return  (int)((minuendDate.getTime() - subtractionDate.getTime()) / 1000);
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

    /**
     * 根据企业微信登录身份获取本地LoginName
     *
     * @param code
     * @return LoginName
     */
    @Override
    public String GetLoginNameWithWechatCode(String code)
    {
        if(StringUtils.isEmpty(code)){
            return "";
        }
        //获取访问用户身份ID
        String userId= GetUseridByWechatLogin(code);
        if(StringUtils.isEmpty(userId)){
            return "";
        }
        //获取用户邮箱与姓名
        WechatUserInfo wechatUserInfo = GetWechatUserInfoDetailByUserId(userId);
        if (null==wechatUserInfo || null==wechatUserInfo.getName()){
            return "";
        }
        //根据用户id+邮箱名+用户名匹配本地用户对应的userId+邮箱名与用户名
        SysUser sysUserWechat=new SysUser();
        sysUserWechat.setUserId(Long.parseLong(wechatUserInfo.getUserid()));
        sysUserWechat.setUserName(wechatUserInfo.getName());
        sysUserWechat.setEmail(wechatUserInfo.getEmail());
        sysUserWechat.setUserType("02"); //只获取从OA同步的用户，保持与企业微信一致。

        List<SysUser> userList= userService.selectUserLists(sysUserWechat);
        int count= userList.size();
        if(count <= 0){
            return ""; //系统里没有用户，没有从OA同步？ 处理逻辑待定
        }
        if(count > 1){
            return ""; //本地数据库存在多个姓名与邮箱相同的记录，如何处理？？--加上了sysUserWechat.setUserId(Long.parseLong(wechatUserInfo.getUserid()));，不存在这种情况了。
        }
        String loginName= userList.get(0).getLoginName();
      return loginName;

    }

    /**
     * 根据企业微信登录链接的code用户获取userid;
     *
     * @param code
     * @return userid
     */
    @Override
    public String GetUseridByWechatLogin(String code) {
        String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        String param = "access_token="+wechatApiService.GetAccessToken()+"&code="+code;
        String userInfo = HttpUtils.sendGet(url,param);    //测试已能正常返回UserInfo Json,正式使用时打开
        //String userInfo = "{\"UserId\":\"359\",\"DeviceId\":\"10000589102865WJ\",\"errcode\":0,\"errmsg\":\"ok\"}";  //为避免去微信获取code麻烦，开发调试时打开
        JSONObject jsonObjectUserInfo = JSONObject.parseObject(userInfo);
        //如果返回码不为0，则输出错误信息，并返回空值
        if ( Integer.parseInt(jsonObjectUserInfo.getString("errcode")) != 0){
            System.out.println(jsonObjectUserInfo.getString("errmsg"));
            return "";
        }
        return jsonObjectUserInfo.getString("UserId");
    }

    /**
     * 根据企业微信userid获取用户详细信息
     *
     * @param userId
     * @return 用户详细信息
     */
    @Override
    public WechatUserInfo GetWechatUserInfoDetailByUserId(String userId) {
        String url="https://qyapi.weixin.qq.com/cgi-bin/user/get";
        String param="access_token="+wechatApiService.GetAccessToken()+"&userid="+userId;
        String userInfoDetail=HttpUtils.sendGet(url,param); //获取成员信息
        WechatUserInfo wechatUserInfo = JSONObject.parseObject(userInfoDetail,WechatUserInfo.class);
        //如果返回码不为0，则返回空，显示错误信息
        if (wechatUserInfo.getErrcode() !=0)
        {
            System.out.println(wechatUserInfo.getErrmsg());
            return null;
        }
        return wechatUserInfo;
    }

    //将List转换为企业微信人员格式，例[359|358]
    private String CovertListToWechatTouserFormat(List<String> toUserList){
        StringBuilder toUser = new StringBuilder();
        for(String user:toUserList){
            toUser.append(user);
            if(toUserList.indexOf(user) < toUserList.size()-1){
                toUser.append("|");
            }
        }
        return toUser.toString();
    }

    /**
     * 推送text消息到企业微信用户
     * 其中text参数的content字段可以支持换行、以及A标签，即可打开自定义的网页
     * 示例："content" : "你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看<a href=\"http://work.weixin.qq.com\">邮件中心视频实况</a>，聪明避开排队。"
     * @param  toUserList 发送的用户列表
     * @param  message 发送的消息内容
     * @return 消息发送结果
     */
    @Override
    public Map<String,String> SendTextMessageToWechatUser(List<String> toUserList,String message) {
        Map<String,String> resultMap;
        Map<String, Object> param = new HashMap<>(16);
        param.put("touser", CovertListToWechatTouserFormat(toUserList));
        param.put("msgtype", "text");
        param.put("agentid", agentId);

        Map<String, String> text = new HashMap<>(16);
        text.put("content", message);
        param.put("text", text);

        String url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+wechatApiService.GetAccessToken();

        //param参数需要直接使用对象，而不能转换成json字符串，否则推送到企业微信中文消息乱码。
        resultMap =HttpUtils.sendPostWithRest(url,param);
        return resultMap;
    }

    /**
     * 推送文本卡片消息到企业微信用户
     * description参数说明：支持使用br标签或者空格来进行换行处理，也支持使用div标签来使用不同的字体颜色，目前内置了3种文字颜色：灰色(gray)、高亮(highlight)、默认黑色(normal)，将其作为div标签的class属性即可
     * 示例："description" : "<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>"
     *
     * @param toUserList  发送的用户列表
     * @param title       标题
     * @param description 内容描述
     * @param detailUrl         点击详情的Url地址
     * @return 消息发送结果
     */
    @Override
    public Map<String, String> SendTextCardMessageToWechatUser(List<String> toUserList, String title, String description, String detailUrl) {
        Map<String,String> resultMap;
        Map<String, Object> param = new HashMap<>(16);
        param.put("touser", CovertListToWechatTouserFormat(toUserList));
        param.put("msgtype", "textcard");
        param.put("agentid", agentId);

        Map<String, Object> textcard=new HashMap<>();
        textcard.put("title",title);
        textcard.put("description",description);
        textcard.put("url",detailUrl);
        param.put("textcard",textcard);

        String url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+wechatApiService.GetAccessToken();

        //param参数需要直接使用对象，而不能转换成json字符串，否则推送到企业微信中文消息乱码。
        resultMap =HttpUtils.sendPostWithRest(url,param);
        return resultMap;
    }

}
