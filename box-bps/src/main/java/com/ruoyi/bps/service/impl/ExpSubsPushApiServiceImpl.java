package com.ruoyi.bps.service.impl;

import com.alibaba.fastjson.JSONObject;
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
import com.kuaidi100.sdk.utils.SignUtils;
import com.ruoyi.bps.domain.ExpSubsPushResp;
import com.ruoyi.bps.domain.ExpSubscribe;
import com.ruoyi.bps.domain.ExpTopgpLog;
import com.ruoyi.bps.service.IExpSubsPushApiService;
import com.ruoyi.bps.service.IExpSubsPushRespService;
import com.ruoyi.bps.service.IExpSubscribeService;
import com.ruoyi.bps.service.IExpTopgpLogService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.TopgpXmlUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpSubsPushApiServiceImpl implements IExpSubsPushApiService {
    /*String key = PropertiesReader.get("key");
    String customer = PropertiesReader.get("customer");
    String secret = PropertiesReader.get("secret");
    String siid = PropertiesReader.get("siid");
    String userid = PropertiesReader.get("userid");
    String tid = PropertiesReader.get("tid");
    String secret_key = PropertiesReader.get("secret_key");
    String secret_secret = PropertiesReader.get("secret_secret"); */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    @Value("${express.key}")
    private String key;

    @Value("${topgp.webservice.toptest}")
    private String webserviceUrl;

    @Autowired
    private IExpSubsPushRespService expSubsPushRespService;

    @Autowired
    IExpSubscribeService expSubscribeService;

    @Autowired
    IExpTopgpLogService expTopgpLogService;

    /**
     * 订阅快递
     * @throws Exception
     */
    @Override
    public SubscribeResp ExpressSubscribe(ExpSubscribe expSubscribe) {

         //如果订阅来源是topgp，则来源为topgp,否则为local
         /*String loginFrom= expSubscribe.getSalt();
         String subscribeFrom= StringUtils.isNotEmpty(loginFrom)?loginFrom.equals("topgp")?"topgp":"local":"local";*/
        if(StringUtils.isEmpty(expSubscribe.getRequestFrom())){
            expSubscribe.setRequestFrom("local");
        }

         if( StringUtils.isEmpty(expSubscribe.getRequestId())) {
             //expSubscribe.setRequestId("local"+System.currentTimeMillis());  //获取时间戳,生成本地请求的requestId
             expSubscribe.setRequestId("local"+ LocalDateTime.now());
         }
         if(StringUtils.isEmpty(expSubscribe.getSubscribeTime())){
             expSubscribe.setSubscribeTime(DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"));
        }
         String salt="bpsemi"; //定义salt字符串

         //组合订阅参数
         SubscribeParameters subscribeParameters = new SubscribeParameters();
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeParameters.setCallbackurl("http://report.bpsemi.cn:8081/it_war/anon/subscribeCallBackUrl");
        subscribeParameters.setPhone(expSubscribe.getPhone());
        subscribeParameters.setSalt(salt);
        SubscribeParam subscribeParam = new SubscribeParam();
        subscribeParam.setParameters(subscribeParameters);
        subscribeParam.setCompany(expSubscribe.getCompany());
        subscribeParam.setNumber(expSubscribe.getNumber());
        subscribeParam.setKey(key);

        SubscribeReq subscribeReq = new SubscribeReq();
        subscribeReq.setSchema(ApiInfoConstant.SUBSCRIBE_SCHEMA);
        subscribeReq.setParam(JSONObject.toJSONString(subscribeParam));

        IBaseClient subscribe = new Subscribe();
        try{
            //推送订阅，并获得快递100响应结果
            HttpResult httpResult= subscribe.execute(subscribeReq);
            subscribeResp = JSONObject.parseObject(httpResult.getBody(),SubscribeResp.class);
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
        newExpSubscribe.setSid(expSubscribe.getSid());   //将时间戳设为Sid  210810 yangbo
        newExpSubscribe.setCompany(expSubscribe.getCompany());
        newExpSubscribe.setNumber(expSubscribe.getNumber());
        newExpSubscribe.setPhone(expSubscribe.getPhone());
        newExpSubscribe.setSalt(salt);
        newExpSubscribe.setSubscribeTime(expSubscribe.getSubscribeTime());
        newExpSubscribe.setResult((subscribeResp.isResult())?"true":"false");
        newExpSubscribe.setReturnCode(subscribeResp.getReturnCode());
        newExpSubscribe.setMessage(subscribeResp.getMessage());
        newExpSubscribe.setRequestFrom(expSubscribe.getRequestFrom());
        newExpSubscribe.setRequestId(expSubscribe.getRequestId());

        /*ExpSubscribe queryExpSubscribe = new ExpSubscribe();
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
        }*/
        //20210802 无论系统里有没有记录，都会记录本次推送。
        expSubscribeService.insertExpSubscribe(newExpSubscribe);

        //返回订阅结果
        return subscribeResp;
    }


    /**
     * 处理Topgp推送的快递订阅请求，向快递100推送订阅请求，并将结果返回给TOPGP
     *
     * @param request
     * @return
     */
    @Override
    public String ExpressSubscribeFromTopgp(HttpServletRequest request) throws IOException {
        //定义Return变量
        String retrunStr;

        //获取httpServletRequest传过来的Json字符串，并进行解析
        JSONObject contentJson= JSONObject.parseObject(ServletUtils.getRequestContent(request));
        if(StringUtils.isEmpty(contentJson)){
            return "貌似没有接受到任何参数！";
        }
        String requestId=contentJson.getString("requestId");     //TOPGP请求ID，年月日时分稍毫秒
        String deliveryNo= contentJson.getString("deliveryNo"); //TOPGP出货单号
        String expressNo = contentJson.getString("expressNo");   //TOPGP快递单号
        String company = contentJson.getString("company");       //TOPGP物流公司编号
        String phone = contentJson.getString("phone");           //TOPGP出货单号
        //Long timeStamp = System.currentTimeMillis();  //获取时间戳
        String subscribeTime= DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"); //获取订阅时间
        SubscribeResp subscribeResp=new SubscribeResp();
        //如果请求ID、出货单号或者快递单号为空,则不向快递100请求订阅，自己组合返回信息。
        if(StringUtils.isEmpty(deliveryNo) || StringUtils.isEmpty(expressNo) || StringUtils.isEmpty(requestId)){
            subscribeResp.setMessage("请求ID、快递单号或出货单号不可为空");
            subscribeResp.setResult(false);
            subscribeResp.setReturnCode("700");
        }else {
            //组合向快递100推送订阅请求的参数
            ExpSubscribe expSubscribe=new ExpSubscribe();
            expSubscribe.setSid(Long.getLong(requestId));  //时间戳
            expSubscribe.setNumber(expressNo);
            expSubscribe.setCompany(company);
            expSubscribe.setPhone(phone);
            expSubscribe.setSubscribeTime(subscribeTime); //订阅时间
            expSubscribe.setSalt("topgp"); //

            //向快递100推送订阅请求，取得订阅返回结果
            subscribeResp= ExpressSubscribe(expSubscribe);

        }
        //根据快递100的订阅返回结果，组合返回Topgp的JSON字符串
        Map<String,Object> map= new HashMap<>();
        map.put("requestId",requestId);         //从TOPGP传过来的requestId， 时间戳
        map.put("deliveryNo",deliveryNo);       //出货单号
        map.put("expressNo",expressNo);         //快递单号
        map.put("responseStr",subscribeResp.getMessage());     //返回消息
        map.put("responseCode",subscribeResp.getReturnCode()); //返回码
        map.put("result",subscribeResp.isResult());   //订阅结果


        //返回Json字符串给TOPGP
        retrunStr= JSONObject.toJSONString(map);


        //记录本次TOPGP订阅请求的Log
        ExpTopgpLog expTopgpLog=new ExpTopgpLog();
        expTopgpLog.setRequestId(requestId);
        expTopgpLog.setRequestType("fromTopgp");
        expTopgpLog.setExpressNumber(expressNo);
        expTopgpLog.setDeliveryNumber(deliveryNo);
        expTopgpLog.setRequestStr(contentJson.toString());
        expTopgpLog.setRequestTime(subscribeTime);
        expTopgpLog.setResponseCode(subscribeResp.getReturnCode());
        expTopgpLog.setResponseStr(retrunStr);

        expTopgpLogService.insertExpTopgpLog(expTopgpLog);

        //返回TOPGP json字符串
        return  retrunStr;
    }

    public JSONObject SendRequestToTopgp( String url,String tip,Map<String,Object> map) {
        String param = TopgpXmlUtils.GetTopgpRequestXml(tip, map);
        String returnXml = HttpUtils.sendXmlPost(url, param);
        return  TopgpXmlUtils.GetStatusFromTopgpResponse(returnXml);
    }



    /**
     * 处理快递100订阅的快递推送信息，并返回响应结果
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

        SubscribePushParamResp subscribePushParamResp=JSONObject.parseObject(param,SubscribePushParamResp.class);
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

            //处理签收逻辑
            //如果是快递100推送的快递单状态为签收（state=3）,并且TOPGP未反馈该快递单已被签收
          if(state.equals("3")) {
              //如果该快递信息没有推送给TOPGP且TOPGP已反馈生成签收单成功记录，则推送给TOPGP
              ExpTopgpLog expTopgpLog=new ExpTopgpLog();
              expTopgpLog.setExpressNumber(subscribePushResult.getNu());
              expTopgpLog.setRequestType("toTopgp");
              expTopgpLog.setResponseCode("200");
              List<ExpTopgpLog> expTopgpLogList= expTopgpLogService.selectExpTopgpLogList(expTopgpLog);
              if(null==expTopgpLogList || expTopgpLogList.size()<1){
                  Map<String, Object> requestMap = new HashMap<>();
                  requestMap.put("expressNum", subscribePushResult.getNu());
                  requestMap.put("expressCom", subscribePushResult.getCom());
                  requestMap.put("expressState", subscribePushResult.getState());

                  //一个快递单号，对应多个出货单请求？


                  //调用topgp Webservice接口
                  //topgp返回的？
                  JSONObject jsonObject= SendRequestToTopgp(webserviceUrl, "express_testRequest",requestMap);
                  log.info(jsonObject.toJSONString());
                  if(jsonObject.getString("returnCode").equals("200")){
                      //一个快递单号对应多个出货单号怎么处理？如果有多个出货单号，部分已签收，部分未签收又怎么处理？  如果推送到ERP时，ERP已经人工生成签收单了，又该怎么处理？


                  }

              }






          }

            //将快递流转状态存入数据库
             expSubsPushRespService.insertExpSubsPushResp(ToExpSubsPushResp(subscribePushParamResp));  //无论数据库中存在快递单号+快递公司编码，都更新数据库 210809 yangbo 修正

            /*ExpSubsPushResp expSubsPushResp=new ExpSubsPushResp();
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
            }*/


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
        expSubsPushResp.setLastResponseTime(DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"));

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
        //System.out.println(str);
       return str;
    }




}
