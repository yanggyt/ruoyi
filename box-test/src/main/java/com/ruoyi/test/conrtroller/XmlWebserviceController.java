package com.ruoyi.test.conrtroller;

import com.ruoyi.common.utils.XmlUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class XmlWebserviceController {
    //private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    @PostMapping("/anon/sendXml")
    public String SendXml()  {
        Map<String, Object> map = new HashMap<>();
        map.put("responseInfo", "此处为测试消息");
        String param = XmlUtils.GetTopgpRequestXml("express_testRequest", map);
        String url = "http://192.168.2.81:85/web/ws/r/aws_ttsrv2_toptest";
        String returnXml = HttpUtils.sendXmlPost(url,param);
        return XmlUtils.GetStatusFromTopgpResponse(returnXml).toString();

    }
}
