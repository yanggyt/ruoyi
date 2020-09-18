package com.ruoyi.dfm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


/**
 * DOE分析控制器
 *
 * @author wangwu
 */
@Controller
@RequestMapping("/doeAnalysis.do")
public class DoeAnalysisController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(DoeAnalysisController.class);

    private static final String DATA_TYPE_KEY_PARAM = "keyParam";
    private static final String DATA_TYPE_REWORK = "rework";

    private static final Integer SUCCESS_CODE = new Integer(20);

    @Value("${api.doe.GetDataByKeyParam}")
    private String getDataByKeyParamUrl;

    @Value("${api.doe.GetDataByRework}")
    private String getDataByReworkUrl;

    @Value("${api.doe.Calculate}")
    private String calculateUrl;

    @Value("${api.doe.Save}")
    private String saveUrl;

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
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam("productname") String productname, @RequestParam("version") String version, @RequestParam("dataType") String dataType) {
        String apiUrl = apiRootUrl;
        String param = "productname=" + productname +"&version=" + version;
        if(DATA_TYPE_KEY_PARAM.equals(dataType)) {
            apiUrl += getDataByKeyParamUrl;
        } else if(DATA_TYPE_REWORK.equals(dataType)) {
            apiUrl += getDataByReworkUrl;
        }
        log.info("request remote api, url={}, param={}", apiUrl, param);
        String result = HttpUtils.sendGet(apiUrl, param);
        log.info("response remote api, url={}, param={}, result={}", apiUrl, param, result);
        JSONObject resultObj = JSON.parseObject(result);
        if(null != resultObj && SUCCESS_CODE.equals(resultObj.getInteger("code"))) {
            JSONArray data = resultObj.getJSONArray("data");
            return getDataTable(data);
        } else {
            log.error("reponse result failed. url={}, param={}, result={}", apiUrl, param, result);
        }
        return getDataTable(Collections.emptyList());
    }

    @RequestMapping("/calculate")
    @ResponseBody
    public TableDataInfo calculate(@RequestParam("productname") String productname, @RequestParam("version") String version, @RequestParam("dataType") String dataType) {
        String apiUrl = apiRootUrl + calculateUrl;
        String param = "";
        log.info("request remote api, apiUrl={}, param={}", apiUrl, param);
        String result = HttpUtils.sendPost(apiUrl, param);
        log.info("response remote api, apiUrl={}, param={}, result={}", apiUrl, param, result);
        JSONObject resultObj = JSON.parseObject(result);
        if(null != resultObj && SUCCESS_CODE.equals(resultObj.getInteger("code"))) {
            JSONArray data = resultObj.getJSONArray("data");
            return getDataTable(data);
        } else {
            log.error("reponse result failed. url={}, param={}, result={}", apiUrl, param, result);
        }
        return getDataTable(Collections.emptyList());
    }


    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(@RequestParam("productname") String productname, @RequestParam("version") String version, @RequestParam("dataType") String dataType) {
        String apiUrl = apiRootUrl + saveUrl;
        String param = "";
        log.info("request remote api, apiUrl={}, param={}", apiUrl, param);
        String result = HttpUtils.sendPost(apiUrl, param);
        log.info("response remote api, apiUrl={}, param={}, result={}", apiUrl, param, result);
        JSONObject resultObj = JSON.parseObject(result);
        if(null != resultObj && SUCCESS_CODE.equals(resultObj.getInteger("code"))) {
            return AjaxResult.success();
        } else {
            log.error("reponse result failed. url={}, param={}, result={}", apiUrl, param, result);
        }
        return AjaxResult.error();
    }

}
