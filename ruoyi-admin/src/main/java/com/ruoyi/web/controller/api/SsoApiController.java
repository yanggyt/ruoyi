package com.ruoyi.web.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DESUtil;
import com.ruoyi.sso.domain.SsoApplication;
import com.ruoyi.sso.service.ISsoApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api("单点登录")
@Controller
@RequestMapping("/api/sso")
public class SsoApiController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SsoApiController.class);

    @Autowired
    private ISsoApplicationService iSsoApplicationService;

    private final String JXSR_CLIENT_ID = "4802e571a8c94cd9921bc77cd8bf6084";
    private final String JXSR_SSO_API_KEY = "sr_sso_manager_2020020211000011";

    @ApiOperation("重定向至省网登录")
    @GetMapping(value = "/redirectUrl")
    public String redirectUrl(@ApiParam(value = "应用标识", type = "String", required = true) String appKey,
                              @ApiParam(value = "应用密钥", type = "String", required = true) String appSecret) throws Exception {
        logger.info("appKey = {} ----- appSecret = {}", appKey, appSecret);
        SsoApplication queryObject = new SsoApplication();
        queryObject.setAppKey(appKey);
        queryObject.setAppSecret(appSecret);
        List<SsoApplication> ssoApplications = iSsoApplicationService.querySsoApplicationByAppKeyAndSecret(queryObject);
        if (!CollectionUtils.isEmpty(ssoApplications)) {
            SsoApplication ssoApplication = ssoApplications.get(0);
            JSONObject queryParamObject = new JSONObject();
            queryParamObject.put("appKey", ssoApplication.getAppKey());
            String s = queryParamObject.toString();
            String resultQueryStr = DESUtil.encrypt(s, JXSR_SSO_API_KEY);
            return "redirect:https://login.jxzwfww.gov.cn/auth2/authorize.do?redirect_uri=" + resultQueryStr + "&client_id=" + JXSR_CLIENT_ID;
        }
        throw new BusinessException("应用不存在！");
    }

    @ApiOperation("重定向至各个应用回调地址")
    @GetMapping(value = "/ssoCallBack")
    public String ssoCallBack(@ApiParam(value = "回调应用加密信息", type = "String") String redirect_uri,
                              @ApiParam(value = "用于搜索数据的ticket", type = "String") String ticket,
                              @ApiParam(value = "登录类型", type = "String") String logintype) throws Exception {
        logger.info("redirect_uri = {} ----- ticket = {} ----- loginType = {}", redirect_uri, ticket, logintype);
        String resultJsonStr = DESUtil.decrypt(redirect_uri, JXSR_SSO_API_KEY);
        JSONObject jsonObject = JSONObject.parseObject(resultJsonStr);
        String appKey = jsonObject.getString("appKey");
        SsoApplication queryObject = new SsoApplication();
        queryObject.setAppKey(appKey);
        List<SsoApplication> ssoApplications = iSsoApplicationService.querySsoApplicationByAppKeyAndSecret(queryObject);
        if (!CollectionUtils.isEmpty(ssoApplications)) {
            SsoApplication ssoApplication = ssoApplications.get(0);
            return "redirect:" + ssoApplication.getAppCallBackUrl() + "?ticket=" + ticket + "&loginType=" + logintype;
        }
        throw new BusinessException("应用不存在！");
    }

    @ApiOperation("根据ticket获取用户信息")
    @GetMapping(value = "/validateTicket", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String validateTicket(@ApiParam(value = "回调中获取的ticket值", type = "String", required = true) String ticket) {
        logger.info("ticket = {}", ticket);
        HttpResponse response = HttpRequest.post("https://login.jxzwfww.gov.cn/auth2/validationTicket.do")
                .form("ticket", ticket)
                .form("clientId", JXSR_CLIENT_ID)
                .send();
        return response.bodyText();
    }
}