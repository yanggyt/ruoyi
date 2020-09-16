package com.ruoyi.dfm.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * DOE分析控制器
 *
 * @author wangwu
 */
@Controller
@RequestMapping("/doeAnalysis.do")
public class DoeAnalysisController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(DoeAnalysisController.class);

    @Value("${api.doe.GetDataByKeyParam}")
    private String getDataByKeyParamUrl;

    /**
     * 获取分析页面
     *
     * @param mmap
     * @return
     */
    @GetMapping("")
    public String index(ModelMap mmap) {
        return "dfm/doeAnalysis";
    }

    /**
     * 获取分析页面
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam("productname") String productname, @RequestParam("version") String version, @RequestParam("dataType") String dataType) {
        String apiUrl = apiRootUrl + getDataByKeyParamUrl;
        String param = "productname=" + productname +"&version=" + version;
        log.info("request remote api, url={}, param={}", apiUrl, param);
        String result = HttpUtils.sendGet(apiUrl, param);
        log.info("response remote api, url={}, param={}, result={}", apiUrl, param, result);
        return getDataTable(null);
    }

}
