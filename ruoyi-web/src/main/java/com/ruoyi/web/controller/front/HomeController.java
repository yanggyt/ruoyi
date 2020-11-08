package com.ruoyi.web.controller.front;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.domain.ClasssicCases;
import com.ruoyi.web.service.IClasssicCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IClasssicCasesService classsicCasesService;
    /**
     * 进入网站首页
     */
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        List<ClasssicCases> classsicCasesList = classsicCasesService.selectClasssicCasesList(null);
        mmap.put("classsicCasesList", classsicCasesList);
        return  "index";
    }
}
