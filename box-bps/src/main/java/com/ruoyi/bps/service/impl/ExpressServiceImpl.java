package com.ruoyi.bps.service.impl;


import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.api.Subscribe;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.*;
import com.kuaidi100.sdk.response.QueryTrackResp;
import com.kuaidi100.sdk.utils.PropertiesReader;
import com.kuaidi100.sdk.utils.SignUtils;
import com.ruoyi.bps.service.IExpressService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpressServiceImpl implements IExpressService {
    /*
    String key = "Jydbrxsm2311";
    String customer = "2DD48B3469B82F2B7700569093AB792B";
    String secret = "8781ed9b35a7438499eb02fee915915a";
    String userid = "2a62da2192c24d17a943ff78ee64f8c6";
     */
    String key = PropertiesReader.get("key");
    String customer = PropertiesReader.get("customer");
    String secret = PropertiesReader.get("secret");
    String siid = PropertiesReader.get("siid");
    String userid = PropertiesReader.get("userid");
    String tid = PropertiesReader.get("tid");
    String secret_key = PropertiesReader.get("secret_key");
    String secret_secret = PropertiesReader.get("secret_secret");
    String msg="";
    @Autowired
    IExpressService expressService;

    @Override
    public List<QueryTrackResp> QueryTrackExpressMultiList(List<QueryTrackParam> list) {
        List<QueryTrackResp> qtList=new ArrayList<>();

        for(QueryTrackParam queryTrackParam:list)
        {
            QueryTrackResp queryTrackResp = new Gson().fromJson(expressService.QueryTrackExpress(queryTrackParam),QueryTrackResp.class);
            qtList.add(queryTrackResp);
        }
        return qtList;
    }

    /**
     *查询多个物流轨迹
     */
    @Override
    public String QueryTrackExpressMulti(List<QueryTrackParam> list) {
        String str="";
        for(QueryTrackParam qt:list){
            str += QueryTrackExpress(qt);
        }
        return str;
    }

    /**
     * 查询单个物流轨迹
     */
    @Override
    public String QueryTrackExpress(QueryTrackParam queryTrackParam) {
        String str="";
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));
        HttpResult httpResult=new HttpResult();
        IBaseClient baseClient = new QueryTrack();
        try {
            httpResult = baseClient.execute(queryTrackReq);
            msg=httpResult.getBody();
        }
        catch (Exception e) {
            msg=e.toString();
        }

        JSONObject jsonObject = new JSONObject(msg);
        if (jsonObject.has("returnCode")){
            QueryTrackResp queryTrackResp=  new Gson().fromJson(msg,QueryTrackResp.class);
            queryTrackResp.setStatus(queryTrackResp.getReturnCode());
            queryTrackResp.setNu(queryTrackParam.getNum());
            msg= new Gson().toJson(queryTrackResp);
        }
       /* else {
            QueryTrackResp queryTrackResp=new Gson().fromJson(msg,QueryTrackResp.class);
            for(int i=0;i<queryTrackResp.getData().size();i++) {
                QueryTrackData queryTrackData = queryTrackResp.getData().get(i);
                str += "时间：" + queryTrackData.getTime();
                str += "  物流信息:" + queryTrackData.getContext();
                str += "  格式化后时间+" + queryTrackData.getFtime();
            } *
        }*/

        // System.out.println(msg);
        return msg;
    }

    /**
     * 查询物流转迹
     */
    @Override
    public String SubscribeExpress() {
        SubscribeParameters subscribeParameters = new SubscribeParameters();
        subscribeParameters.setCallbackurl("http://www.baidu.com");  //回调接口的地址，必须
        subscribeParameters.setPhone("17725390266");  //收、寄件人电话号码，非必须
        SubscribeParam subscribeParam = new SubscribeParam();
        subscribeParam.setParameters(subscribeParameters);
        subscribeParam.setCompany("annengwuliu");  //快递公司编码，小写。必须
        subscribeParam.setNumber("300445967949"); //快递单号, 必须
        subscribeParam.setKey(key);  //授权码，必须

        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA); //返回的数据格式，必须
        subscribeReq.setParam(new Gson().toJson(subscribeParam));

        IBaseClient subscribe = new Subscribe();
        try{
            msg=subscribe.execute(subscribeReq).toString();
        }
        catch (Exception e) {
            msg=e.toString();
        }
        System.out.println(msg);
        return msg;
    }

    @Override
    public List<QueryTrackParam> GetTestQueryTrackParam() {
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        List<QueryTrackParam> list=new ArrayList<QueryTrackParam>();
        queryTrackParam.setCom("annengwuliu");
        queryTrackParam.setNum("300445967949");
        queryTrackParam.setPhone("17725390266");
        list.add(queryTrackParam);

        QueryTrackParam queryTrackParam1 = new QueryTrackParam();
        queryTrackParam1.setCom("annengwuliu");
        queryTrackParam1.setNum("300445967135");
        queryTrackParam1.setPhone("17725390266");
        list.add(queryTrackParam1);


        QueryTrackParam queryTrackParam2 = new QueryTrackParam();
        queryTrackParam2.setCom("annengwuliu");
        queryTrackParam2.setNum("3004459670971");
        queryTrackParam2.setPhone("17725390266");
        list.add(queryTrackParam2);
/*
        QueryTrackParam queryTrackParam3 = new QueryTrackParam();
        queryTrackParam3.setCom(CompanyConstant.AN);
        queryTrackParam3.setNum("300445967045");
        queryTrackParam3.setPhone("17725390266");
        list.add(queryTrackParam3);

        QueryTrackParam queryTrackParam4 = new QueryTrackParam();
        queryTrackParam4.setCom(CompanyConstant.AN);
        queryTrackParam4.setNum("300443569920");
        queryTrackParam4.setPhone("17725390266");
        list.add(queryTrackParam4);

        QueryTrackParam queryTrackParam5 = new QueryTrackParam();
        queryTrackParam5.setCom(CompanyConstant.AN);
        queryTrackParam5.setNum("300443569878");
        queryTrackParam5.setPhone("17725390266");
        list.add(queryTrackParam5);

        QueryTrackParam queryTrackParam6 = new QueryTrackParam();
        queryTrackParam6.setCom(CompanyConstant.AN);
        queryTrackParam6.setNum("300443569880");
        queryTrackParam6.setPhone("17725390266");
        list.add(queryTrackParam6);
        */
        return list;
    }


}
