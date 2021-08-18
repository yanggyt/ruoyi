package com.ruoyi.test.conrtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SendJsonController {
    @RequestMapping("/test/sendjson")
    public String ajaxSend(){
        return ("test/sendJson");

    }

}
