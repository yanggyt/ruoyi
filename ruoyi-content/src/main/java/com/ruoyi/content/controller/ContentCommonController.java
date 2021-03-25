package com.ruoyi.content.controller;

import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsRoleAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * 说明：公共controller
 *
 * @author wang.q
 * @date 2017年8月25日
 */
@Controller
@RequestMapping("/common")
public class ContentCommonController {
    private static Logger logger = LoggerFactory.getLogger(ContentCommonController.class);
    @Autowired
    private CmsRoleAuthorityService authorityService;

    /**
     * 查询访问人的所有菜单权限
     *
     * @return
     */
    @RequestMapping(value = "/menus")
    @ResponseBody
    public Message menus(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("----------menus------------menus-------------menus------menus----------");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            //获取每个请求头名称
            String headerName = headerNames.nextElement();
            //跟距请求头获取请求值
            String value = req.getHeader(headerName);
            System.out.println(headerName);
            System.out.println(value);

        }
        System.out.println("--------menus----------menus---------menus----------menus------menus--------");


        Message msg = new Message();
        try {
            List<?> list = authorityService.queryUserRole();
            if (list != null && list.size() > 0) {
                msg.setObject(list);
                msg.setResult(true);
            } else {
                msg.setResult(false);
                msg.setInfo("未获取菜单，或您已离线。请重新登录！");
            }
        } catch (Exception e) {
            // TODO: handle exception
            msg.setResult(false);
            msg.setInfo(e.getMessage());
            logger.info("获取菜单失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }
}
