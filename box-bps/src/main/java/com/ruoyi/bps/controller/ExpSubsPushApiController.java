package com.ruoyi.bps.controller;

import com.kuaidi100.sdk.response.SubscribeResp;
import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.service.IExpSubsPushApiService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 接受快递推送信息的API接口Controller
 *
 * @author box
 * @date 2021-05-13
 */
@Api(value = "快递信息订阅推送",tags = "快递订阅接口")
@RestController
/*@RequestMapping("/anon")*/
public class ExpSubsPushApiController extends BaseController {
    @Autowired
    IExpSubsPushApiService expSubsPushApiService;

    //快递100推送
    @CrossOrigin
    @PostMapping("anon/subscribeCallBackUrl/{salt}")
    @ApiOperation("快递信息订阅推送接受")
    public SubscribeResp SubscribeCallBackUrl(@PathVariable("salt") String salt, HttpServletRequest request) {
        return expSubsPushApiService.ExpressSubscribeCallBackUrl(request,salt);
    }


    //订阅
    @CrossOrigin
    @PostMapping("anon/subscribe")
    public SubscribeResp Subscribe(ExpSubscribe expSubscribe){
        return expSubsPushApiService.ExpressSubscribe(expSubscribe);
    }

    //接受topgp订阅，
    @Log(title = "快递订阅", businessType = BusinessType.OTHER)
    @CrossOrigin
    @ApiOperation(value="topgp订阅快递",notes = "request body格式： {\"requestId\":\"1628584040740\",\"deliveryNo\":\"S301-2108020001\",\"expressNo\":\"300444235610\",\"company\":\"annengwuliu\",\"phone\":\"13800138000\"}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "requestJson", value = "请求json",required = true, paramType = "body", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("api/express/topgpSubscribe")
    public String topgpSubscribe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return expSubsPushApiService.ExpressSubscribeFromTopgp(request);
    }

    //接受topgp转签收单完成后的消息推送
    @Log(title = "TOPGP出货已转签收", businessType = BusinessType.OTHER)
    @CrossOrigin
    @ApiOperation(value="接受TOPGP已转签收消息推送",notes = "request body格式： {\"requestId\":\"topgpSign1628584040740\"," +
                    "\"signedInfoList\":[{\"deliveryNo\":\"S301-2108020001\",\"signNo\":\"S501-2108020001\"},{\"deliveryNo\":\"S301-2108020002\",\"signNo\":\"S501-2108020002\"}]," +
                    "\"expressNo\":\"300444235610\",\"company\":\"annengwuliu\",\"phone\":\"13800138000\",\"status\":\"0\"}"
                )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "header", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "requestJson", value = "请求json",required = true, paramType = "body", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("api/express/topgpSigned")
    public String topgpSigned(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // return expSubsPushApiService.ExpressSubscribeFromTopgp(request);
        return expSubsPushApiService.TopgpDeliverySigned(request);
    }



}

