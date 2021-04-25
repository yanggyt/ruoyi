package com.ruoyi.content.controller;

import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: ruoyi->CommonController
 * @description:
 * @author: LiuShenlu
 * @create: 2021-04-09 17:26
 **/
@RestController
public class ContentCommonController {

    @Autowired
    private CommonService commonService;

    @PostMapping("oss")
    public Message upload(@RequestParam("file") MultipartFile file) {
        Message msg = commonService.upload(file);
        return msg;
    }

}
