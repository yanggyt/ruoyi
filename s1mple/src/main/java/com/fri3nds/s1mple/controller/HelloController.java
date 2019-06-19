package com.fri3nds.s1mple.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends BaseController {
    @RequiresPermissions("system:hello:view")
    @RequestMapping("/system/hello")
    public String hello(){

        return "Hello!Fri3nds!";
    }
}
