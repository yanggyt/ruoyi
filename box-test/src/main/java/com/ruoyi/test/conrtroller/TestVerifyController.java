package com.ruoyi.test.conrtroller;

import com.ruoyi.test.service.TestVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestVerifyController {
    @Autowired
    private TestVerifyService testVerifyService;

    @RequestMapping("/test/testVerify")
    public String testVerify(){
        return "/test/testVerify";
    }




    /**
     * 校验用户名
     */

    @PostMapping("/test/testVerifyName")
    @ResponseBody
    public int testVerifyName(String name)
    {
        return testVerifyService.isNameUnique(name);
    }

}
