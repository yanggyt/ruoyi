package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
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
                "                        &lt;Record>\n");
        for(String key:mapInfo.keySet()){
            stringBuffer.append("&lt;Field name='"+key+"' value='" +mapInfo.get(key).toString().replaceAll("&","&amp")+"' />\n");
        }
        stringBuffer.append("                        &lt;/Record>\n" +
                "                    &lt;/Parameter>\n" +
                "                    &lt;Document/>\n" +
                "                &lt;/RequestContent>\n" +
                "            &lt;/Request>\n" +
                "        </tip:request>\n" +
                "    </tip:"+tip+">\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        log.info("=======生成xml结束======");
        return stringBuffer.toString();
    }

    /**
     * 将TOPGP返回的XML转化为Json
     * @param  topgpResponseXml
     * @return Status JsonObject
     */
    public static JSONObject TopgpResponseXmlToJson(String topgpResponseXml) {
        //String xmlString= TopgpResponseTestXml();  //开发测试用xml
        String xmlString =XML.toJSONObject(topgpResponseXml).toString();
        JSONObject xmlJson = JSONObject.parseObject(xmlString);
        JSONObject response = xmlJson.getJSONObject("SOAP-ENV:Envelope")
                .getJSONObject("SOAP-ENV:Body").getJSONObject("fjs1:express_testResponse")
                .getJSONObject("fjs1:response").getJSONObject("Response");

        JSONObject returnJsonObject=new JSONObject();

        if(StringUtils.isNotEmpty(response.getString("ResponseContent"))){
            JSONObject responseContent = response.getJSONObject("ResponseContent");
            if(StringUtils.isNotEmpty(responseContent.getString("Parameter"))){
                JSONObject parameter=responseContent.getJSONObject("Parameter");
                if(StringUtils.isNotEmpty(parameter.getString("Record"))){
                    JSONObject record= parameter.getJSONObject("Record");
                    if(StringUtils.isNotEmpty(record.getString("Field"))){
                        returnJsonObject.put("Parameter",parameter.getJSONObject("Record").getJSONObject("Field"));  //回传参数资料
                    }
                }
            }else {
                returnJsonObject.put("Parameter","");  //回传参数资料
            }
            if(StringUtils.isNotEmpty(responseContent.getString("Document"))){
                JSONObject document= responseContent.getJSONObject("Document");
                if(StringUtils.isNotEmpty(document)){
                    returnJsonObject.put("document",document.getJSONObject("RecordSet")); //回传单据的单头单身资料
                }
            }else {
                returnJsonObject.put("document","");
            }
        }
        if(StringUtils.isNotEmpty(response.getJSONObject("Execution"))){
            if(response.getJSONObject("Execution").containsKey("Status")){
                returnJsonObject.put("execution",response.getJSONObject("Execution").getJSONObject("Status"));  //服务执行结果
            }else {
                returnJsonObject.put("execution","");
            }
        }
        return  returnJsonObject;
    }

    //TOPGP返回XML的标准格式，开发时可使用该XML进行测试。
    private static String TopgpResponseTestXml(){
       return  "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Body>\n" +
                "      <fjs1:express_testResponse xmlns:fjs1=\"http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay\">\n" +
                "         <fjs1:response>\n" +
                "<Response>\n" +
                "  <Execution>\t<!-- 服务执行结果 -->\n" +
                "    <Status code=\"0\" sqlcode=\"0\" description=\"\"/>\n" +
                "  </Execution> \n" +
                "  <ResponseContent>\n" +
                "    <Parameter>\t<!-- 参数资料回传 -->\n" +
                "      <Record>\n" +
                "        <Field name=\"my_parameter\" value=\"my_value\"/>\n" +
                "      </Record>\n" +
                "    </Parameter>     \n" +
                "    <Document>\t<!—单据资料回传（单头单身） -->\n" +
                "      <RecordSet id=\"1\">\n" +
                "        <Master name=\"my_name\"> \n" +
                "          <Record>\n" +
                "            <Field name=\"my_column\" value=\"my_value\"/>\n" +
                "          </Record>\n" +
                "        </Master>\n" +
                "        <Detail name=\"my_name\">\n" +
                "          <Record>\n" +
                "            <Field name=\"my_column\" value=\"my_value\"/>\n" +
                "            <Field name=\"my_column1\" value=\"my_value1\"/>\n" +
                "          </Record>\n" +
                "        </Detail>\n" +
                "      </RecordSet> \n" +
                "      <RecordSet id=\"2\">\n" +
                "      <Master name=”my_name”> \n" +
                "          <Record>\n" +
                "            <Field name=\"my_column\" value=\"my_value\"/>\n" +
                "          </Record>\n" +
                "        </Master>\n" +
                "        <Detail name=”my_name”>\n" +
                "          <Record>\n" +
                "            <Field name=\"my_column\" value=\"my_value\"/>\n" +
                "            <Field name=\"my_column1\" value=\"my_value1\"/>\n" +
                "          </Record>\n" +
                "        </Detail>\n" +
                "      </RecordSet>\n" +
                "    </Document>\n" +
                "  </ResponseContent>\n" +
                "</Response>\n" +
                "</fjs1:response>\n" +
                "      </fjs1:express_testResponse>\n" +
                "   </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";
    }


}
