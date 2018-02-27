package com.ruoyi.project.system.user.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.framework.page.TableDataInfo;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 用户信息
 * 
 * @author yangzz
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @GetMapping("/getUserlist")
    @ResponseBody
    public TableDataInfo queryUserlist(@RequestParam Map<String, Object> params)
    {
        List<User> list = userService.pageInfoQuery(null);
        TableDataInfo tableDataInfo = new TableDataInfo(list, 12);
        return tableDataInfo;
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/userList")
    public String userList()
    {
        return prefix + "/test";
    }

}