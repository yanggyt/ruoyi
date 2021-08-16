package com.ruoyi.test.conrtroller;

import com.ruoyi.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BoxTestController {
    @Autowired
    private TestService testService;
    @RequestMapping("test")
    public String test(){
        testService.test();
        return  testService.test();
    }
}
