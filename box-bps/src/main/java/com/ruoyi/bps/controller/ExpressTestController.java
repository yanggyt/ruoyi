package com.ruoyi.bps.controller;


//import com.ruoyi.bps.express.contant.CompanyConstant;
//import com.ruoyi.bps.express.request.QueryTrackParam;

import com.ruoyi.bps.service.IExpressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.kuaidi100.sdk.request.QueryTrackParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpressTestController extends BaseController {
    @Autowired
    private IExpressService expressService;

    @RequestMapping("/anon/bps/express/test/queryTrackMultiList")
    public  TableDataInfo  QueryTrackMultiList(){
        //return expressService.QueryTrackExpressMultiList(expressService.GetTestQueryTrackParam()).toString();
        return getDataTable(expressService.QueryTrackExpressMultiList(expressService.GetTestQueryTrackParam()));
    }

    @RequestMapping("/anon/bps/express/test/queryTrackMulti")
    public  String QueryTrackMulti(){
        return  expressService.QueryTrackExpressMulti(expressService.GetTestQueryTrackParam());
    }

    @RequestMapping("/anon/bps/express/test/queryTrack")
    public String  QueryTrack(){
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom("annengwuliu");
        queryTrackParam.setNum("3004459671351");
        queryTrackParam.setPhone("17725390266");

        return  expressService.QueryTrackExpress(queryTrackParam);


    }
    @RequestMapping("/anon/bps/express/test/subscribe")
    public String Subscribe(){
        return expressService.SubscribeExpress();
    }




}
