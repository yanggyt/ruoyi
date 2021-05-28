package com.ruoyi.bps.controller;


//import com.ruoyi.bps.express.contant.CompanyConstant;
//import com.ruoyi.bps.express.request.QueryTrackParam;
//import com.ruoyi.bps.express.response.QueryTrackResp;

import com.ruoyi.bps.service.IExpressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.google.gson.Gson;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.response.QueryTrackResp;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class QueryExpressController extends BaseController {
    @Autowired
    private IExpressService expressService;
    @RequiresPermissions("bps:express:view")
    @RequestMapping("/bps/express/queryExpress")
    public String queryExpress()
    {
        //return "express/queryExpress";
        //说明：此处不能用绝对路径，否则，当application.yml中，设定context-path: /it_war后，打Jar包时，会找不到thymeleaf对应的文件。
        return "express/queryExpress";
    }

    @CrossOrigin
    @RequestMapping("/bps/express/queryExpress/list")
    @ResponseBody
    public Map<String, String> queryExpressList(@RequestBody QueryTrackParam queryTrackParam){
        Map<String,String> result = null;
        if (queryTrackParam != null) {
            System.out.println("运单号码：" + queryTrackParam.getNum());
            System.out.println("快递公司：" + queryTrackParam.getCom());
            System.out.println("电话：" + queryTrackParam.getPhone());
            result = new HashMap<>();
            result.put("code", "1");
            result.put("msg", "ok");
            result.put("info",expressService.QueryTrackExpress(queryTrackParam));
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping("/bps/express/queryExpress/list1")
    @ResponseBody
    public Map<String, Object> queryExpressList1(@RequestBody QueryTrackParam queryTrackParam){
        Map<String, Object> result = null;
        if (queryTrackParam != null) {
            System.out.println("运单号码：" + queryTrackParam.getNum());
            System.out.println("快递公司：" + queryTrackParam.getCom());
            System.out.println("电话：" + queryTrackParam.getPhone());
            String info=expressService.QueryTrackExpress(queryTrackParam);
            QueryTrackResp queryTrackResp = new Gson().fromJson(info,QueryTrackResp.class);
            result = new HashMap<>();
            result.put("code", "1");
            result.put("msg", "ok");
            result.put("nu",queryTrackResp.getNu());
            result.put("com",queryTrackResp.getCom());
            result.put("state",queryTrackResp.getState());
            result.put("info",queryTrackResp.getData());
        }
        return result;
    }

    @RequestMapping("/anon/bps/express/queryTrackMultiList")
    public  TableDataInfo  QueryTrackMultiList(@RequestBody QueryTrackParam queryTrackParam){

        return getDataTable(expressService.QueryTrackExpressMultiList(expressService.GetTestQueryTrackParam()));
    }

    @RequestMapping("/anon/bps/express/queryTrackMulti")
    public  String QueryTrackMulti(){
        return  expressService.QueryTrackExpressMulti(expressService.GetTestQueryTrackParam());
    }

    @RequestMapping("/anon/bps/express/queryTrack")
    public String  QueryTrack(){
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom("annengwuliu");
        queryTrackParam.setNum("3004459671351");
        queryTrackParam.setPhone("17725390266");

        return  expressService.QueryTrackExpress(queryTrackParam);


    }
    @RequestMapping("/anon/bps/express/subscribe")
    public String Subscribe(){
        return expressService.SubscribeExpress();
    }
}
