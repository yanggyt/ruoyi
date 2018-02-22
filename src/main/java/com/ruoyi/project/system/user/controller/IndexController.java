package com.ruoyi.project.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 首页 业务处理
 * 
 * @author yangzz
 */
@Controller
public class IndexController extends BaseController
{

    @Autowired
    private IUserService userService;

    // 系统首页
    @RequestMapping("/index")
    public String index() throws Exception
    {
        // 取身份信息
        User currentUser = getUser();

        // 根据用户id取出菜单
        //List<Permission> permissions = userService.findPermsListByUserId(currentUser.getUserName());

        // 通过model传到页面
        //session.setAttribute("permissions", TreeUtil.getChildPerms(permissions, 0));
        return "index";
    }
    
    // 系统介绍
    @RequestMapping("/main")
    public String main() throws Exception
    {
        // 取身份信息
        User currentUser = getUser();

        // 根据用户id取出菜单
        //List<Permission> permissions = userService.findPermsListByUserId(currentUser.getUserName());

        // 通过model传到页面
        //session.setAttribute("permissions", TreeUtil.getChildPerms(permissions, 0));
        return "main";
    }

}
