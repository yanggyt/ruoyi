package com.ruoyi.content.controller;

import com.ruoyi.content.domain.LdComParty;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.LdComService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author qin.hx
 * @ClassName: ArticleAdController
 * @Description: TODO(广告处理控制层)
 * @date 2018年4月26日
 */
@Controller
@RequestMapping("/ldcom")
public class LdComController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdComController.class);

    @Autowired
    private LdComService ldComService;

    /**
     * 查询所有广告列表（本程序调用）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryldcomList")
    @ResponseBody
    public Message queryldcomList(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        LOGGER.info("获取渠道支公司列表控制层方法开始");
        Message msg = new Message();
        Map<String, Object> adMap = new HashMap<String, Object>();
        // 广告列表信息
        List<LdComParty> ldcomList = ldComService.queryldcomList();
        adMap.put("ldcomList", ldcomList);
        msg.setInfo("成功");
        msg.setObject(adMap);
        msg.setResult(true);
        LOGGER.info("获取渠道支公司列表控制层方法结束");
        return msg;
    }

}
