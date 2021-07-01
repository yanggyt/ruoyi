package com.ruoyi.bps.service.impl;

import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.service.IExpSubsPushApiService;
import com.ruoyi.bps.service.IExpSubsPushRespService;
import com.ruoyi.bps.service.IExpSubscribeService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.Subscribe;
import com.kuaidi100.sdk.contant.ApiInfoConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.SubscribeParam;
import com.kuaidi100.sdk.request.SubscribeParameters;
import com.kuaidi100.sdk.request.SubscribeReq;
import com.kuaidi100.sdk.response.SubscribePushData;
import com.kuaidi100.sdk.response.SubscribePushParamResp;
import com.kuaidi100.sdk.response.SubscribePushResult;
import com.kuaidi100.sdk.response.SubscribeResp;
import com.kuaidi100.sdk.utils.PropertiesReader;
import com.kuaidi100.sdk.utils.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ExpSubsPushApiServiceImpl implements IExpSubsPushApiService {
    String key = PropertiesReader.get("key");
    String customer = PropertiesReader.get("customer");
    String secret = PropertiesReader.get("secret");
    String siid = PropertiesReader.get("siid");
    String userid = PropertiesReader.get("userid");
    String tid = PropertiesReader.get("tid");
    String secret_key = PropertiesReader.get("secret_key");
    String secret_secret = PropertiesReader.get("secret_secret");

    @Autowired
    private IExpSubsPushRespService expSubsPushRespService;

    @Autowired
    IExpSubscribeService expSubscribeService;

    /**
     * 订阅快递
     * @throws Exception
     */
    @Override
    public SubscribeResp ExpressSubscribe(ExpSubscribe expSubscribe) {
        SubscribeParameters subscribeParameters = new SubscribeParameters();
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeParameters.setCallbackurl("http://report.bpsemi.cn:8081/it_war/anon/subscribeCallBackUrl");
        subscribeParameters.setPhone(expSubscribe.getPhone());
        subscribeParameters.setSalt("bpsemi");
        SubscribeParam subscribeParam = new SubscribeParam();
        subscribeParam.setParameters(subscribeParameters);
        subscribeParam.setCompany(expSubscribe.getCompany());
        subscribeParam.setNumber(expSubscribe.getNumber());
        subscribeParam.setKey(key);

        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA);
        subscribeReq.setParam(new Gson().toJson(subscribeParam));

        IBaseClient subscribe = new Subscribe();
        try{
            HttpResult httpResult= subscribe.execute(subscribeReq);
            System.out.println(httpResult);
            subscribeResp= new Gson().fromJson(httpResult.getBody(),SubscribeResp.class);
        }catch (Exception e)
        {
            return subscribeResp;
        }
        //如果快递公司或快递单号为空，则直接返回订阅结果
        if(StringUtils.isEmpty(expSubscribe.getCompany()) || StringUtils.isEmpty(expSubscribe.getNumber()))
        {
            return subscribeResp;
        }

        //订阅记录写入数据库
        ExpSubscribe newExpSubscribe = new ExpSubscribe();
        newExpSubscribe.setCompany(expSubscribe.getCompany());
        newExpSubscribe.setNumber(expSubscribe.getNumber());
        newExpSubscribe.setPhone(expSubscribe.getPhone());
        newExpSubscribe.setSalt("bpsemi");
        newExpSubscribe.setSubscribeTime(DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"));
        newExpSubscribe.setResult((subscribeResp.isResult())?"true":"false");
        newExpSubscribe.setReturnCode(subscribeResp.getReturnCode());
        newExpSubscribe.setMessage(subscribeResp.getMessage());

        ExpSubscribe queryExpSubscribe = new ExpSubscribe();
        queryExpSubscribe.setCompany(expSubscribe.getCompany());
        queryExpSubscribe.setNumber(expSubscribe.getNumber());
        queryExpSubscribe.setResult(expSubscribe.getResult());
        queryExpSubscribe.setReturnCode(expSubscribe.getReturnCode());
        List<ExpSubscribe> list=expSubscribeService.selectExpSubscribeList(queryExpSubscribe);
        if(list.size()>0){
            //如果数据库中存在快递单号+快递公司编码+结果+返回码相同，则更新记录
            for(ExpSubscribe es:list){
                queryExpSubscribe= newExpSubscribe;
                queryExpSubscribe.setSid(es.getSid());
                expSubscribeService.updateExpSubscribe(queryExpSubscribe);
            }
        }else {
            //如果数据库中没有快递单号+快递公司编码，则更插入新记录
            expSubscribeService.insertExpSubscribe(newExpSubscribe);
        }

        //返回订阅结果
        return subscribeResp;
    }

    /**
     * 获取从快递100订阅的快递推送信息，并返回响应结果
     *
     * @param request 快递100推送的订阅信息
     * @return 结果
     */
    @Override
    public SubscribeResp ExpressSubscribeCallBackUrl(HttpServletRequest request) {
        //如果推送信息中没有包含
        if(StringUtils.isEmpty(request.getParameter("param"))
                || StringUtils.isEmpty(request.getParameter("sign"))) {
            SubscribeResp subscribeResp= new SubscribeResp();
            subscribeResp.setResult(Boolean.FALSE);
            subscribeResp.setReturnCode("701");
            subscribeResp.setMessage("推送的信息不合法！");
            return subscribeResp;
        }

        String param= request.getParameter("param");
        String sign = request.getParameter("sign");
        //log.debug("快递100订阅推送回调结果|{}|{}",param,sign);
        //订阅时传的salt
        String salt = "bpsemi";
        String ourSign = SignUtils.sign(param + salt);
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeResp.setResult(Boolean.TRUE);
        subscribeResp.setReturnCode("200");
        //加密如果不等，则不属于快递100推送，忽略掉当前请求
        if (!ourSign.equals(sign)){
            subscribeResp.setMessage("接受成功!但加密验证不通过！【sign】"+sign+"【ourSign】"+ourSign);
            return subscribeResp;
        }
        //加密相等，继续处理业务逻辑
        subscribeResp.setMessage("接受成功!加密验证通过！【sign】"+sign+"【ourSign】"+ourSign);

        SubscribePushParamResp subscribePushParamResp = new Gson().fromJson(param, SubscribePushParamResp.class);
        SubscribePushResult subscribePushResult = subscribePushParamResp.getLastResult();
        //快递单号
        String nu = subscribePushResult.getNu();
        //监控状态 (polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。其中当快递单为已签收时status=shutdown)
        String status= subscribePushParamResp.getStatus();
        if(status.equals("abort")){
            //todo
            //当message为“3天查询无记录”或“60天无变化时”status= abort ，对于status=abort的状态的处理逻辑
            //将Abort信息存档。然后预警

        }
        //快递单当前状态 （0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转单，10待清关，11清关中，12已清关，13清关异常，14收件人拒签)
        String state = subscribePushResult.getState();
        //if(state.equals("3")){
            //处理签收逻辑
            //将快递流转状态存入数据库
            ExpSubsPushResp expSubsPushResp=new ExpSubsPushResp();
            expSubsPushResp.setLastResultNu(subscribePushResult.getNu());
            expSubsPushResp.setLastResultCom(subscribePushResult.getCom());
            List<ExpSubsPushResp> list=expSubsPushRespService.selectExpSubsPushRespList(expSubsPushResp);
            if(list.size()>0){
                //如果数据库中存在快递单号+快递公司编码，则更新记录
                ExpSubsPushResp newExpSubsPushResp= ToExpSubsPushResp(subscribePushParamResp);
                for(ExpSubsPushResp expr:list){
                     newExpSubsPushResp.setSid(expr.getSid());
                     expSubsPushRespService.updateExpSubsPushResp(newExpSubsPushResp);
                     newExpSubsPushResp.setSid(null);
                }
            }else {
                //如果数据库中没有快递单号+快递公司编码，则更插入新记录
                expSubsPushRespService.insertExpSubsPushResp(ToExpSubsPushResp(subscribePushParamResp));
            }
       // }

        return subscribeResp;
    }

    /**
     * 将快递100推送的信息转换为ExpSubsPushResp
     * @param subscribePushParamResp
     * @return ExpSubsPushResp
     */
    private ExpSubsPushResp ToExpSubsPushResp(SubscribePushParamResp subscribePushParamResp){
        ExpSubsPushResp expSubsPushResp=new ExpSubsPushResp();

        SubscribePushResult subscribePushLastResult = subscribePushParamResp.getLastResult();
        SubscribePushResult subscribePushDestResult = subscribePushParamResp.getDestResult();

        expSubsPushResp.setStatus(subscribePushParamResp.getStatus());
        expSubsPushResp.setBillStatus(subscribePushParamResp.getBillstatus());
        expSubsPushResp.setMessage(subscribePushParamResp.getMessage());
        expSubsPushResp.setAutoCheck(subscribePushParamResp.getAutoCheck());
        expSubsPushResp.setComOld(subscribePushParamResp.getComOld());
        expSubsPushResp.setComNew(subscribePushParamResp.getComNew());

        expSubsPushResp.setLastResultMessage(subscribePushLastResult.getMessage());
        expSubsPushResp.setLastResultState(subscribePushLastResult.getState());
        expSubsPushResp.setLastResulStatus(subscribePushLastResult.getStatus());
        expSubsPushResp.setLastResultCondition(subscribePushLastResult.getCondition());
        expSubsPushResp.setLastResultIsCheck(subscribePushLastResult.getIscheck());
        expSubsPushResp.setLastResultCom(subscribePushLastResult.getCom());
        expSubsPushResp.setLastResultNu(subscribePushLastResult.getNu());
        expSubsPushResp.setLastResultData(SubscribePushDataToString(subscribePushLastResult.getData()));

        if(subscribePushDestResult != null) {
            //只有邮政国外的快递推送才会有DestResult信息
            expSubsPushResp.setDestResultMessage(subscribePushDestResult.getMessage());
            expSubsPushResp.setDestResultState(subscribePushDestResult.getState());
            expSubsPushResp.setDestResultStatus(subscribePushDestResult.getStatus());
            expSubsPushResp.setDestResultCondition(subscribePushDestResult.getCondition());
            expSubsPushResp.setDestResultIsCheck(subscribePushDestResult.getIscheck());
            expSubsPushResp.setDestResultCom(subscribePushDestResult.getCom());
            expSubsPushResp.setDestResultNu(subscribePushDestResult.getNu());
            expSubsPushResp.setDestResultData(SubscribePushDataToString(subscribePushDestResult.getData()));
        }

        return expSubsPushResp;
    }

    /**
     *
     * @param list 将List<SubscribePushData>转化为字符串
     * @return
     */
    private String SubscribePushDataToString(List<SubscribePushData> list){
        String str="";
        for(SubscribePushData subscribePushData:list){
            str+="【"+subscribePushData.getTime()+"】 ";
            if(StringUtils.isNotEmpty(subscribePushData.getAreaName()))
            {
                str+=subscribePushData.getAreaName()+"/";   //某些快递没有AreaName信息
            }
            str+=subscribePushData.getContext();
            if(list.size()-1>list.indexOf(subscribePushData)){
                str+="\r\n";
            }
        }
        System.out.println(str);
       return str;
    }




}
