package com.ruoyi.bps.controller;

import com.kuaidi100.sdk.response.SubscribeResp;
import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.service.IExpSubsPushApiService;
import com.ruoyi.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 接受快递推送信息的API接口Controller
 *
 * @author box
 * @date 2021-05-13
 */
@Api("快递信息订阅推送")
@RestController
@RequestMapping("/anon")
public class ExpSubsPushApiController extends BaseController {
    @Autowired
    IExpSubsPushApiService expSubsPushApiService;

    //推送
    @CrossOrigin
    @PostMapping("/subscribeCallBackUrl")
    @ApiOperation("快递信息订阅推送接受")
    public SubscribeResp SubscribeCallBackUrl(HttpServletRequest request) {
        return expSubsPushApiService.ExpressSubscribeCallBackUrl(request);
    }

    //订阅
    @CrossOrigin
    @PostMapping("/subscribe")
    public SubscribeResp Subscribe(ExpSubscribe expSubscribe){
        return expSubsPushApiService.ExpressSubscribe(expSubscribe);
    }

}

