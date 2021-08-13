package com.ruoyi.test.conrtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class TestIndexController {
    @RequestMapping("/test")
    public String index(ModelMap modelMap){
        modelMap.put("date", LocalDateTime.now());
        return ("test/index");
    }
}
