package com.ruoyi.bps.service;

import com.ruoyi.bps.domain.ExpSubscribe;
import com.kuaidi100.sdk.response.SubscribeResp;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IExpSubsPushApiService {

    /**
     * 向快递100推送订阅请求
     * @param expSubscribe
     * @return
     */
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
    public SubscribeResp ExpressSubscribeCallBackUrl(HttpServletRequest request,String salt);

    /**
     * 获取Topgp推送的快递信息，向快递100推送订阅请求
     * @param request
     * @return
     */
    public String ExpressSubscribeFromTopgp(HttpServletRequest request) throws IOException;

    /**
     * Topgp将出货单转为签收单后的信息推送处理
     * @param request
     * @return
     */
    public String TopgpDeliverySigned(HttpServletRequest request) throws IOException;
}
