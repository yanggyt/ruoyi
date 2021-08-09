package com.ruoyi.common.utils;

import com.ruoyi.common.utils.http.HttpUtils;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TopgpXmlUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 组合TOPGP所需的XML格式
     * @param tip 调用TOPGP的Webservice的方法名 如：express_testRequest
     * @param mapInfo XML中Filed对应的键值对Map
     * @return xml字符串
     */
    public static String GetTopgpRequestXml(String tip, Map<String,Object> mapInfo){
        log.info("=======生成xml======");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tip=\"http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "    <tip:"+tip+">\n" +
                "        <tip:request>\n" +
                "            &lt;Request>\n" +
                "                &lt;Access>\n" +
                "                    &lt;Authentication user='topgui' password='' />\n" +
                "                    &lt;Connection application='bps' source='www.bpsemi.com' />\n" +
                "                    &lt;Organization name='SYSTEM' />\n" +
                "                    &lt;Locale language='zh_cn' />\n" +
                "                &lt;/Access>\n" +
                "                &lt;RequestContent>\n" +
                "                    &lt;Parameter>\n" +
                "                        &lt;Record>\n" +
                "                            &lt;Field name=");
        for(String key:mapInfo.keySet()){
            stringBuffer.append("'"+key+"' value='" +mapInfo.get(key).toString().replaceAll("&","&amp")+"' />\n");
        }
        stringBuffer.append("                        &lt;/Record>\n" +
                "                    &lt;/Parameter>\n" +
                "                    &lt;Document/>\n" +
                "                &lt;/RequestContent>\n" +
                "            &lt;/Request>\n" +
                "        </tip:request>\n" +
                "    </tip:express_testRequest>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        log.info("=======生成xml结束======");
        return stringBuffer.toString();
    }

    /**
     * 将TOPGP返回的XML转化为Json,并提出返回Status
     * @param  TopgpResonseXml 调用TOPGP的Webservice的方法名 如：express_testRequest
     * @return Status JsonObject
     */
    public static JSONObject GetStatusFromTopgpResponse(String TopgpResonseXml) {
        JSONObject jsonObject = XML.toJSONObject(TopgpResonseXml);

        JSONObject envelope = jsonObject.getJSONObject("SOAP-ENV:Envelope");
        JSONObject body = envelope.getJSONObject("SOAP-ENV:Body");
        JSONObject express_testResponse = body.getJSONObject("fjs1:express_testResponse");
        JSONObject fjs1Response = express_testResponse.getJSONObject("fjs1:response");
        JSONObject response = fjs1Response.getJSONObject("Response");
        JSONObject execution = response.getJSONObject("Execution");
        return  execution.getJSONObject("Status");
    }


}
