package com.ruoyi.bps.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.bps.domain.ExpressInfo;
import com.ruoyi.bps.mapper.ExpressInfoMapper;
import com.ruoyi.bps.service.IExpressInfoService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
//import com.google.gson.Gson;
import com.kuaidi100.sdk.api.AutoNum;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.AutoNumReq;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.response.AutoNumResp;
import com.kuaidi100.sdk.response.QueryTrackData;
import com.kuaidi100.sdk.response.QueryTrackResp;
import com.kuaidi100.sdk.utils.PropertiesReader;
import com.kuaidi100.sdk.utils.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快递信息Service业务层处理
 * 
 * @author box
 * @date 2021-05-06
 */
@Service
public class ExpressInfoServiceImpl implements IExpressInfoService
{
    /*
    String key = PropertiesReader.get("key");
    String customer = PropertiesReader.get("customer");
    String secret = PropertiesReader.get("secret");
    String siid = PropertiesReader.get("siid");
    String userid = PropertiesReader.get("userid");
    String tid = PropertiesReader.get("tid");
    String secret_key = PropertiesReader.get("secret_key");
    String secret_secret = PropertiesReader.get("secret_secret");
     */
    @Value("${express.key}")
    private String key;

    @Value("${express.customer}")
    private String customer;




    String msg="";

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    /**
     * 查询快递信息
     * 
     * @param message 快递信息ID
     * @return 快递信息
     */
    @Override
    public ExpressInfo selectExpressInfoById(String message)
    {
        return expressInfoMapper.selectExpressInfoById(message);
    }

    /**
     * 查询本地快递信息列表
     *
     * @param expressInfo 快递信息
     * @return 快递信息集合
     */
    @Override
    public List<ExpressInfo> selectLocalExpressInfoList(ExpressInfo expressInfo) {
        return expressInfoMapper.selectExpressInfoList(expressInfo);
    }

    /**
     * 查询快递信息列表
     * 
     * @param expressInfo 快递信息
     * @return 快递信息列表
     */
    @Override
    public List<ExpressInfo> selectExpressInfoList(ExpressInfo expressInfo)
    {
        List<ExpressInfo> expressInfoList=new ArrayList<>();
        //如果没有输入订单号，则返回空信息
        String nuStr=expressInfo.getNu();
        if(StringUtils.isEmpty(nuStr)){
            expressInfo.setData("请输入订单号进行查询！");
            expressInfoList.add(expressInfo);
            return expressInfoList;
        }
        //如果是顺丰，则必须要输入电话号码
        if( StringUtils.isEmpty(expressInfo.getPhone())
                && (expressInfo.getCom().equals("nsf") || expressInfo.getCom().contains("shunfeng"))){
            expressInfo.setData("查询顺丰快递信息，必须要提供收/寄人电话号码");
            expressInfoList.add(expressInfo);
            return expressInfoList;
        }

        List<String> stringList= Arrays.asList(nuStr.split(","));
        ExpressInfo  newExpressInfo= expressInfo;
        for(String str:stringList){
            newExpressInfo.setNu(str);
            expressInfoList.add(SelectExpressInfo(newExpressInfo));
        }
        return expressInfoList;
        //return expressInfoMapper.selectExpressInfoList(expressInfo);
    }

    @Override
    public ExpressInfo SelectExpressInfo(ExpressInfo expressInfo){
       String nu=expressInfo.getNu();  //快递单号
       String com=expressInfo.getCom(); //快递公司
       String phone=expressInfo.getPhone(); //收、寄件人电话号码
       String deliveryNum= expressInfo.getDeliveryNum();
       ExpressInfo callbackExpressInfo=new ExpressInfo();

       callbackExpressInfo.setNu(nu);
       callbackExpressInfo.setPhone(phone);
       callbackExpressInfo.setDeliveryNum(deliveryNum);
       //如果没有输入快递公司编号，则查询快递公司编号
       if(StringUtils.isEmpty(com)){
           List<AutoNumResp> list= AutoGetExpressCom(nu);
            if(null==list || list.size()<1){
                callbackExpressInfo.setData("请提供要查询的快递所属物流公司编号！，且根据快递单号没有查询到物流公司编号！");
                return callbackExpressInfo;
            }
            if (list.size()>1)
            {
                callbackExpressInfo.setData("您没有提供要查询的快递所属物流公司编号，且根据快递单号查询到多个物流公司编号");
                return callbackExpressInfo;
            }
            com=list.get(0).getComCode();
        }
        callbackExpressInfo.setCom(com);

        //return callbackExpressInfo;
        return QueryExpressInfo(callbackExpressInfo);
    }

    private ExpressInfo QueryExpressInfo(ExpressInfo expressInfo){
        //从expressInfo中获取快递单号、物流信息、电话,生成快递请求参数
        QueryTrackParam queryTrackParam= new QueryTrackParam();
        queryTrackParam.setNum(expressInfo.getNu());
        queryTrackParam.setCom(expressInfo.getCom());
        queryTrackParam.setPhone(expressInfo.getPhone());

        //获取快递信息
        //String param = new Gson().toJson(queryTrackParam);
        String param= JSONObject.toJSONString(queryTrackParam);
        QueryTrackReq queryTrackReq=new QueryTrackReq();
        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));
        HttpResult httpResult =new HttpResult();
        IBaseClient baseClient = new QueryTrack();
        try {
            httpResult = baseClient.execute(queryTrackReq);
            msg=httpResult.getBody();
        }
        catch (Exception e) {
            msg=e.toString();
        }

        //将快递信息转化为QueryTrackResp对象
        //QueryTrackResp queryTrackResp = new Gson().fromJson(msg,QueryTrackResp.class);
        QueryTrackResp queryTrackResp= JSONObject.parseObject(msg,QueryTrackResp.class);

        //如果没有查到物流信息，则返回错误信息
        if(StringUtils.isEmpty(queryTrackResp.getStatus()) || !queryTrackResp.getStatus().equals("200")){
            expressInfo.setData(queryTrackResp.getMessage());
            return expressInfo;
        }

        //获取签收时间
        String signedTime=null;
        if(queryTrackResp.getState().equals("3")) {
            signedTime=queryTrackResp.getData().get(0).getFtime();
        }

        //获取最后更新时间
        String lastUpdateTime=queryTrackResp.getData().get(0).getFtime();

        //获取揽收时间
        String collectTime= queryTrackResp.getData().get(queryTrackResp.getData().size()-1).getTime();

        //获取查询时间
        String queryTime= StringUtils.isNotEmpty(expressInfo.getQueryTime())?expressInfo.getQueryTime():DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss");

        //获取查询人（登录用户）
        String queryUserName= ShiroUtils.getLoginName();

        //将快递信息中的Context转化为字符
        String dataStr="";
        for(QueryTrackData queryTrackData :queryTrackResp.getData()){
            dataStr+="【"+queryTrackData.getTime()+"】 ";
            dataStr+=queryTrackData.getContext();
            if(queryTrackResp.getData().size()-1>queryTrackResp.getData().indexOf(queryTrackData)){
                dataStr+="\r\n";
            }
        }
        String a= queryTrackResp.getCondition();
        ExpressInfo callbackExpressInfo=new ExpressInfo();
        callbackExpressInfo.setMessage(queryTrackResp.getMessage());
        callbackExpressInfo.setNu(queryTrackResp.getNu());
        callbackExpressInfo.setIscheck(queryTrackResp.getIscheck());
        callbackExpressInfo.setCom(queryTrackResp.getCom());
        callbackExpressInfo.setStatus(queryTrackResp.getStatus());
        callbackExpressInfo.setData(dataStr);
        callbackExpressInfo.setState(queryTrackResp.getState());
        callbackExpressInfo.setCondition(queryTrackResp.getCondition());
        callbackExpressInfo.setRouteInfo(""); //出发位置，当前位置，到达位置，暂无信息
        callbackExpressInfo.setReturnCode(queryTrackResp.getReturnCode());
        callbackExpressInfo.setResult(queryTrackResp.isResult()?"Y":"N");
        callbackExpressInfo.setPhone(expressInfo.getPhone());
        callbackExpressInfo.setSingedTime(signedTime);
        callbackExpressInfo.setCollectTime(collectTime);
        callbackExpressInfo.setLastUpdateTime(lastUpdateTime);
        callbackExpressInfo.setQueryTime(queryTime);
        callbackExpressInfo.setQueryUserName(queryUserName);
        callbackExpressInfo.setDeliveryNum(expressInfo.getDeliveryNum());

        return callbackExpressInfo;
    }

    /**
     * 根据快递单号，查询快递公司编码
     * @param num 快递单号
     * @return 快递公司编码
     */

    private List<AutoNumResp> AutoGetExpressCom(String num){
        AutoNumReq autoNumReq = new AutoNumReq();
        autoNumReq.setKey(key);
        autoNumReq.setNum(num.trim());

        IBaseClient baseClient = new AutoNum();
        //AutoNumResp autoNumResp=new AutoNumResp();
        List<AutoNumResp> autoNumRespList=new ArrayList<>();
        try {
            JSONArray jsonArray= JSONArray.parseArray(baseClient.execute(autoNumReq).getBody());
            if(StringUtils.isEmpty(jsonArray))
            {
                return null;
            }
            for (Object object:jsonArray)
            {
                autoNumRespList.add(JSONObject.parseObject(JSONObject.toJSONString(object),AutoNumResp.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return autoNumResp;
        return autoNumRespList;
    }

    /**
     * 新增快递信息
     * 
     * @param expressInfo 快递信息
     * @return 结果
     */
    @Override
    public int insertExpressInfo(ExpressInfo expressInfo)
    {
        return expressInfoMapper.insertExpressInfo(expressInfo);
    }

    /**
     * 修改快递信息
     * 
     * @param expressInfo 快递信息
     * @return 结果
     */
    @Override
    public int updateExpressInfo(ExpressInfo expressInfo)
    {
        return expressInfoMapper.updateExpressInfo(expressInfo);
    }

    /**
     * 删除快递信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpressInfoByIds(String ids)
    {
        return expressInfoMapper.deleteExpressInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除快递信息信息
     * 
     * @param message 快递信息ID
     * @return 结果
     */
    @Override
    public int deleteExpressInfoById(String message)
    {
        return expressInfoMapper.deleteExpressInfoById(message);
    }

}
