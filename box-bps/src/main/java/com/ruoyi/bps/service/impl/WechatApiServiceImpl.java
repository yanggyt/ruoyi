package com.ruoyi.bps.service.impl;

import com.google.gson.Gson;
import com.ruoyi.bps.domain.WechatAccessToken;
import com.ruoyi.bps.mapper.WechatAccessTokenMapper;
import com.ruoyi.bps.service.IWechatApiService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
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

    /**
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

    /**
     *
     * @param minuendDate  被减数日期
     * @param subtractionDate  减数日期
     * @return 相差秒数
     */
    public  int differenceSecond(Date minuendDate, Date subtractionDate ) {
        return  (int)((minuendDate.getTime() - subtractionDate.getTime()) / 1000);
    }
}
