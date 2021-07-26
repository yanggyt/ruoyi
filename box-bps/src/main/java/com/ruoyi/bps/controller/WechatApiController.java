package com.ruoyi.bps.controller;

import com.ruoyi.bps.service.IWechatApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatApiController {
    @Autowired
    IWechatApiService wechatApiService;

    @RequestMapping("anon/getAccessToken")
    public String getAccessToken() {
        return wechatApiService.GetAccessToken();
    }
}
