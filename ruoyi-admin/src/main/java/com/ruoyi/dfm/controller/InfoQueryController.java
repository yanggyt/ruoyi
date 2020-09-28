package com.ruoyi.dfm.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 信息查询控制器
 */
@Controller
@RequestMapping("/infoQuery.do")
public class InfoQueryController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(InfoQueryController.class);

    /**
     * 获取信息查询页面
     *
     * @param mmap
     * @return
     */
    @GetMapping("")
    public String index(ModelMap mmap) {
        return "dfm/infoQuery";
    }
}
