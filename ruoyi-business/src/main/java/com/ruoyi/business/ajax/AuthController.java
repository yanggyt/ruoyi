package com.ruoyi.business.ajax;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.business.model.Member;
import com.ruoyi.common.core.controller.BaseController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class AuthController extends BaseController {

    @Resource
    private HttpServletRequest request;

    //获取前端登录用户ID
    public Long getUserID() {
        return getMember().getId();
    }

    public Member getMember() {
        String jsonString = (String) request.getAttribute("member");
        return JSONObject.parseObject(jsonString, Member.class);
    }
}
