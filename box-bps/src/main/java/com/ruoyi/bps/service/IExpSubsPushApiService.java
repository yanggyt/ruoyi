package com.ruoyi.bps.service;

import com.ruoyi.bps.domain.ExpSubscribe;
import com.kuaidi100.sdk.response.SubscribeResp;

import javax.servlet.http.HttpServletRequest;

public interface IExpSubsPushApiService {
    public SubscribeResp ExpressSubscribe(ExpSubscribe expSubscribe);

    /**
     * 快递100订阅推送处理
     *
     * 回调接口支持自定义参数,比如订阅时回调地址填写的是 http://www.xxx.com?orderId=1233333
     * 可以通过下面这种方式获取到orderId： String orderId = request.getParameter("orderId");
     *
     * 返回值必须是下面这样的格式，否则快递100将认为该推送失败，快递100将会重试3次该推送，时间间隔35分钟；
     * 成功结果返回例子： {"result":true,"returnCode":"200","message":"提交成功"}
     *
     */
    public SubscribeResp ExpressSubscribeCallBackUrl(HttpServletRequest request);
}
