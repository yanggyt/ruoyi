package com.ruoyi.project.system.user.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.utils.TableDataInfo;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.core.controller.BaseController;
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test()
    {
        return prefix + "/test";
    }


}