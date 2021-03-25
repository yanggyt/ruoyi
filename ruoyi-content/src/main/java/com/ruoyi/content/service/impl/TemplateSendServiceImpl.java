package com.ruoyi.content.service.impl;

import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.TemplateSendService;
import com.ruoyi.content.utils.JsonUtil;
import com.ruoyi.content.utils.Notify;
import com.ruoyi.content.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TemplateSendServiceImpl implements TemplateSendService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateSendServiceImpl.class);

    //测试
/*	private static final String UID = "pyqzs";
	private static final String KEY = "a12dadf_12asdinktn0091d1fzxpyqzs1da249kk";
	private static final String URL = "http://uat-notify.ihxlife.com/notify/api/v1/qyh/img/send";
	private static final String AGENTID = "1000024";*/
    //生产
    private static final String UID = PropertiesConstants.NOTIFY_UID;
    private static final String KEY = PropertiesConstants.NOTIFY_KEY;
    private static final String URL = PropertiesConstants.NOTIFY_URL;
    private static final String AGENTID = PropertiesConstants.NOTIFY_AGENTID;//朋友圈助手外勤118

    @SuppressWarnings("unchecked")
    @Override
    public Message newArticleSend(String articleName, String url, String picurl, String shareDes, String agentCode, String partyId) throws Exception {
        String nonce = RandomUtils.generateRandomCharAndNumber(10);   //10位随机字符串
        Message msg = new Message();
        //封装推送数据
        String description = shareDes + "\n快来转发朋友圈>>";
        description = description.replaceAll("\\\\x0a", "\n");

        //生产
//    	String toparty = "8|18121";
//		String touser = "110013545";
        //测试
//		String toparty = "858";
//		String touser = "150012282";
        String toparty = null;
        String touser = null;
        if (StringUtils.isNotBlank(agentCode)) {
            touser = agentCode;
        } else {
            toparty = partyId;
        }
        String msgtype = "news";//消息类型
        String agentid = AGENTID;//企业应用的id
        String msg_id = UID + nonce;
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", articleName);
        map.put("picurl", picurl);
        map.put("description", description);
        map.put("url", url);
//		map.put("toparty", toparty);
//		map.put("touser", touser);
        if (StringUtils.isNotBlank(touser)) {
            map.put("touser", touser);
        } else {
            map.put("toparty", toparty);
        }
        map.put("msgtype", msgtype);
        map.put("agentid", agentid);
        map.put("msg_id", msg_id);
        String data = buidMailReq(map);//请求报文
        long start = System.currentTimeMillis();
        String respStr = "";
        try {
            respStr = Notify.sendMessage(URL, UID, KEY, data);
        } catch (Exception e) {
            logger.info("文章推送，调用中台接口异常，请求参数【{}】,异常信息【{}】", data, e.getMessage());
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        logger.info("文章推送，根据请求地址【{}】，请求参数【{}】，返回信息【{}】，请求耗时【{}】", URL, data, respStr, end - start);
        Map<String, String> result = JsonUtil.jackson2Map(respStr);
        String info = result.get("result");
        if ("succ".equals(info)) {
            msg.setResult(true);
            msg.setInfo("文章推送成功");

        } else {
            msg.setResult(false);
            msg.setInfo("文章推送失败");
        }
        return msg;
    }

    //组装接口请求报文
    private static String buidMailReq(Map<String, String> map) {
        String title = map.get("title");
        String description = map.get("description");
        String url = map.get("url");
        String toparty = map.get("toparty");
        String touser = map.get("touser");
        String msgtype = map.get("msgtype");//消息类型
        String agentid = map.get("agentid");//企业应用的id
        String msg_id = map.get("msg_id");
        String picurl = map.get("picurl");//

        Map<String, String> articlesMap = new HashMap<>();//图文消息内容
        articlesMap.put("title", title);
        articlesMap.put("description", description);
        articlesMap.put("url", url);
        articlesMap.put("picurl", picurl);

        List<Map<String, String>> articlesList = new ArrayList<Map<String, String>>();
        articlesList.add(articlesMap);

        Map<String, List<Map<String, String>>> newsMap = new HashMap<String, List<Map<String, String>>>();
        newsMap.put("articles", articlesList);

        //data放在Object的map里
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //模板信息
        Map<String, Object> busiDataMap = new HashMap<String, Object>();
//		busiDataMap.put("toparty", toparty);
//		busiDataMap.put("touser", touser);
        if (StringUtils.isNotBlank(touser)) {
            busiDataMap.put("touser", touser);
        } else {
            busiDataMap.put("toparty", toparty);
        }
        busiDataMap.put("msgtype", msgtype);
        busiDataMap.put("news", newsMap);
        busiDataMap.put("agentid", agentid);

        dataMap.put("msg_id", msg_id);
        dataMap.put("busi_data", busiDataMap);
        //将map转化成json
        String data = JsonUtil.objectToJackson(dataMap);
        System.out.println("读取到的请求报文为：" + data);
        return data;
    }

}
