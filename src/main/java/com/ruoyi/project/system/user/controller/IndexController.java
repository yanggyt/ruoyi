package com.ruoyi.project.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
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

    @Autowired
    private IMenuService menuService;

    // 系统首页
    @RequestMapping("/index")
    public String index(Model model) throws Exception
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.findMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        return "index";
    }

    // 系统介绍
    @RequestMapping("/main")
    public String main() throws Exception
    {
        return "main";
    }

}
