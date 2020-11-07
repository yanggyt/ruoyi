package com.ruoyi.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class WebController {
    private String prefix = "home";
    /**
     * 进入网站首页
     */
    @GetMapping("/index")
    public String index()
    { return  "index";
    }
}
