package com.ruoyi.system.service;

import com.ruoyi.system.domain.WechatSendMessage;
import com.ruoyi.system.domain.WechatUserInfo;

import java.util.List;
import java.util.Map;

public interface IWechatApiService {
    //获取Access Token
    public String GetAccessToken();

    //根据企业微信登录身份获取本地LoginName
    public String GetLoginNameWithWechatCode(String code);

    /**
     * 根据企业微信登录链接的code用户获取userid;
     * @param code
     * @return userid
     */
    public String GetUseridByWechatLogin(String code);

    /**
     * 根据企业微信userid获取用户详细信息
     * @param userId
     * @return 用户详细信息
     */
    public WechatUserInfo GetWechatUserInfoDetailByUserId(String userId);

    /**
     * 推送text消息到企业微信用户
     * @param  toUserList 发送的用户列表
     * @param  message 发送的消息内容
     * @return 消息发送结果
     */
    public Map<String,String> SendTextMessageToWechatUser(List<String> toUserList, String message);

    /**
     * 推送文本卡片消息到企业微信用户
     * description参数说明：支持使用br标签或者空格来进行换行处理，也支持使用div标签来使用不同的字体颜色，目前内置了3种文字颜色：灰色(gray)、高亮(highlight)、默认黑色(normal)，将其作为div标签的class属性即可
     * 示例："description" : "<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>"
     * @param  toUserList 发送的用户列表
     * @param title 标题
     * @param description 内容描述
     * @param detailUrl 点击详情的Url地址
     * @return 消息发送结果
     */
    public Map<String,String> SendTextCardMessageToWechatUser(List<String> toUserList, String title, String description, String detailUrl);
}
